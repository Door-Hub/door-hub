package org.example.doorhub.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookResponseDto {
    private Integer id;
    private Integer booker;
    private Integer worker;
    private Double hourlyRate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean Accepted;
}
