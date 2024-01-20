package org.example.doorhub.payment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Payment {

    @Id
    private Long id;
    private Long userId;
    private BigDecimal amount;  // qancha pull otishi
    private LocalDateTime timestamp; // qaysi vohtda otishi
    private String description;
    private String status; // status success ili disabled , processing
}
