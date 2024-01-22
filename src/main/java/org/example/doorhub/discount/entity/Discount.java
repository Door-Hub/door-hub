package org.example.doorhub.discount.entity;

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
public class Discount {

    @Id
    private Integer id;
    private int percentage;
    private Integer categoryId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
