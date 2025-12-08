package com.chronosx.bikeshop.service.serviceImpl;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.chronosx.bikeshop.dto.OrderNotificationDto;
import com.chronosx.bikeshop.dto.response.NotificationResponse;
import com.chronosx.bikeshop.entity.Notification;
import com.chronosx.bikeshop.entity.Orders;
import com.chronosx.bikeshop.entity.Role;
import com.chronosx.bikeshop.enums.NotificationStatus;
import com.chronosx.bikeshop.exception.AppException;
import com.chronosx.bikeshop.exception.ErrorCode;
import com.chronosx.bikeshop.mapper.NotificationMapper;
import com.chronosx.bikeshop.repository.NotificationRepository;
import com.chronosx.bikeshop.repository.RoleRepository;
import com.chronosx.bikeshop.repository.UserRepository;
import com.chronosx.bikeshop.service.NotificationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    @Value("${spring.mail.username}")
    private String senderMail;

    private final NotificationRepository notificationRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    private final NotificationMapper notificationMapper;

    private final JavaMailSender mailSender;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public void sendOrderMail(String to, String subject, String htmlContent, Long billId) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(senderMail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(String.format(htmlContent, billId, billId), true);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new AppException(ErrorCode.CANT_SEND_MAIL);
        }
    }

    public void createNewNotificationWhenCreateNewOrder(Orders order) {
        Set<String> roleName = Set.of("ADMIN", "MODERATOR", "SALE");
        Set<Role> roles = roleRepository.findAll().stream()
                .filter(role -> roleName.contains(role.getRoleName()))
                .collect(Collectors.toSet());

        Notification notification = Notification.builder()
                .createdAt(LocalDateTime.now())
                .orderStatus(order.getStatus())
                .orders(order)
                .roles(roles)
                .notificationStatus(NotificationStatus.NEW)
                .build();

        notificationRepository.save(notification);
    }

    public void sendNotificationByWebSocket(Orders order) {
        OrderNotificationDto orderNotificationDto = OrderNotificationDto.builder()
                .orderId(order.getId())
                .orderStatus(order.getStatus().name())
                .build();
        simpMessagingTemplate.convertAndSend("/topic/notifications", orderNotificationDto);
    }

    @Override
    public void setReadNotificationById(Long id) {
        var notification = notificationRepository
                .findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOTIFICATION_ID_NOT_FOUND));
        notification.setNotificationStatus(NotificationStatus.READ);
        notificationRepository.save(notification);
    }

    @Override
    public Page<NotificationResponse> findAllNotificationByUserId(Long userId, int page, int size) {
        var user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_ID_NOT_FOUND));
        Pageable pageable = PageRequest.of(page, size);

        Set<Role> roles = user.getRoles();

        Set<Notification> notificationSet = new HashSet<>();
        roles.forEach(role -> notificationSet.addAll(role.getNotifications()));

        List<Notification> notifications = new ArrayList<>(notificationSet);

        notifications.sort(Comparator.comparing(Notification::getCreatedAt).reversed());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), notifications.size());
        List<NotificationResponse> subList = notifications.subList(start, end).stream()
                .map(this::toNotificationResponse)
                .toList();

        return new PageImpl<>(subList, pageable, notifications.size());
    }

    NotificationResponse toNotificationResponse(Notification notification) {
        NotificationResponse notiResponse = notificationMapper.toNotificationResponse(notification);
        notiResponse.setOrderStatus(notification.getOrderStatus().name());
        notiResponse.setNotiStatus(notification.getNotificationStatus().name());
        notiResponse.setOrderId(notification.getOrders().getId());

        return notiResponse;
    }
}
