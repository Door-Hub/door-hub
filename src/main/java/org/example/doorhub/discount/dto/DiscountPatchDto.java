package org.example.doorhub.discount.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DiscountPatchDto {

    private int percentage;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
