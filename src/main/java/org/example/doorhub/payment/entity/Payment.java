package org.example.doorhub.payment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.doorhub.user.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "`payment`")
public class Payment {

    @Id
    private Long id;
    private Long userId;
    private BigDecimal amount;  // qancha pull otishi
    private LocalDateTime timestamp; // qaysi vohtda otishi
    private String description;
    private String status; // status success ili disabled , processing

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;
}
