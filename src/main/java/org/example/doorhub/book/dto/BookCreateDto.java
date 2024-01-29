package org.example.doorhub.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.doorhub.book.entity.TypeOfProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookCreateDto {

    private Integer worker;
    private Double hourlyRate;
    private LocalDate startDate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private TypeOfProperty typeOfProperty;
    private String description;
    private boolean Accepted;
}
