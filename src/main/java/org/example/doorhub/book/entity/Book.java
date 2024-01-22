package org.example.doorhub.book.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Book {

    @Id
    private Integer id;
    private Integer booker;
    private Integer worker;
    private Double hourlyRate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean Accepted;
}
