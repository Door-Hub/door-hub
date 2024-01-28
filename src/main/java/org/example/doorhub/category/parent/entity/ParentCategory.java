package org.example.doorhub.category.parent.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.doorhub.category.entity.Category;
import org.example.doorhub.discount.entity.Discount;
import org.example.doorhub.review.entity.Review;
import org.example.doorhub.user.entity.User;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "parent")
public class ParentCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String avatar;


    @ManyToOne
    @JoinColumn(name = "category_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Category category;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "parentCategory")
    private List<Review> views = new ArrayList<>();
}
