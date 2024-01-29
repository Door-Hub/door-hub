package org.example.doorhub.review.entity;


import jakarta.persistence.*;
import lombok.*;
import org.example.doorhub.category.parent.entity.ParentCategory;
import org.example.doorhub.user.entity.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer stars;
    private Integer seenUsers;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne()
    @JoinColumn(name = "users_id", nullable = false)
    private User user;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne()
    @JoinColumn(name = "parent_category_id")
    private ParentCategory parentCategory;

}

