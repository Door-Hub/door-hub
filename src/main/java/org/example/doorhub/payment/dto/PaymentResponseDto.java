package org.example.doorhub.payment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentResponseDto {

    @NotNull
    private Integer id;
    @NotNull
    private Integer userId;

    @NotBlank
    private BigDecimal amount;

    @NotNull
    private LocalDateTime timestamp;

    private String description;

    @NotBlank
    private String status;
}
