package com.chronosx.bikeshop.entity;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.*;

import com.chronosx.bikeshop.enums.NotificationStatus;
import com.chronosx.bikeshop.enums.OrderStatus;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orders;

    @ManyToMany
    @JoinTable(
            name = "notification_roles",
            joinColumns = @JoinColumn(name = "notification_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Enumerated(EnumType.STRING)
    private NotificationStatus notificationStatus;
}
