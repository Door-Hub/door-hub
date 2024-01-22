package org.example.doorhub.payment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.doorhub.listeners.PaymentTimeStampNow;
import org.example.doorhub.user.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "`payment`")
@EntityListeners(PaymentTimeStampNow.class)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    private BigDecimal amount;
    private LocalDateTime timestamp;
    private String description;
    private String status;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;
}
