package org.example.doorhub.book.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.doorhub.user.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double hourlyRate;
    private LocalDate startDate;
    private LocalDateTime startTime;
    private TypeOfProperty typeOfProperty;
    private String description;
    private boolean Accepted;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "book")
    private User worker;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "book")
    private User booker;

}
