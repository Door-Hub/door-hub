package org.example.doorhub.discount.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.example.doorhub.category.parent.entity.ParentCategory;
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

    @ManyToOne()
    @JsonProperty("parentCategoryId")
    @JoinColumn(name = "parentCategoryId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ParentCategory parentCategory;

}
