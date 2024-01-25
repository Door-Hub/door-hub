package org.example.doorhub.category.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.doorhub.discount.entity.Discount;
import org.example.doorhub.review.entity.Review;
import org.example.doorhub.user.entity.User;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "category")
public class Category {

    @Id
    private Integer id;
    private String name;
    private String avatar;
    private Integer stars;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "category")
    private List<Review> views;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    @OneToMany(mappedBy = "category")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Discount> discounts;

    @OneToMany(mappedBy = "parentCategory")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Category> categoryList;

    @ManyToOne
    @JoinColumn(name = "categorys_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Category parentCategory;

}
