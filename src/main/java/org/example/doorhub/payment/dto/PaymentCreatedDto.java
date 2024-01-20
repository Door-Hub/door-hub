package org.example.doorhub.payment.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentCreatedDto {

    @NotNull
    @Min(value = 5000)
    private BigDecimal amount;
    @NotBlank
    @Size(min = 3)
    private String description;
    @NotNull
    private Integer userId;
}
