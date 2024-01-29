package org.example.doorhub.category.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.doorhub.category.parent.entity.ParentCategory;
import org.example.doorhub.discount.entity.Discount;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String avatar;



    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "category_parents",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_id")
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<ParentCategory> parents;


}
