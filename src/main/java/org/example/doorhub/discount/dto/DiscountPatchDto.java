package org.example.doorhub.discount.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DiscountPatchDto {

    @NotNull
    private Integer parentCategoryId;
    private int percentage;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
