package org.example.doorhub.discount.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DiscountCreateDto {

    @NotNull
    private Integer parentCategoryId;
    private int percentage;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
