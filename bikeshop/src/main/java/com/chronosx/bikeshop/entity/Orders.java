package com.chronosx.bikeshop.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

import com.chronosx.bikeshop.enums.OrderStatus;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String email;
    private String phoneNumber;
    private String address;
    private String note;
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<Notification> notifications;

    @ManyToOne
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethod;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;
}
