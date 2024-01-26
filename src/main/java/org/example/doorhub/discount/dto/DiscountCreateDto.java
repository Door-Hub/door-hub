package org.example.doorhub.discount.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DiscountCreateDto {

    private Integer categoryId;
    private int percentage;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
