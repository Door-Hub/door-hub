package org.example.doorhub.discount.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.doorhub.category.entity.Category;
import org.example.doorhub.listeners.DiscountStartDate;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EntityListeners(DiscountStartDate.class)
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int percentage;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Category category;
}
