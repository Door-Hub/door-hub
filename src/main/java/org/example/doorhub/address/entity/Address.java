package org.example.doorhub.address.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.doorhub.location.dto.LocationResponse;
import org.example.doorhub.user.entity.User;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String locationName;
    private Double longitude;
    private Double latitude;

    @ManyToMany(mappedBy = "addresses")
    List<User> users;

}
