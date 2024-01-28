package org.example.doorhub.discount.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DiscountResponseDto {

    private Integer id;
    private Integer parentCategoryId;
    private int percentage;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
