package org.example.doorhub.address.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.doorhub.user.entity.User;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(name = "location_name")
    private String locationName;
    private Double longitude;
    private Double latitude;
    private String home;

    @ManyToOne()
    @JoinColumn(name = "user_Id")
    private User user;

}
