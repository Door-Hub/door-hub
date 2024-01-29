package org.example.doorhub.address.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
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
    private String home;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonProperty("userId")
    @JoinColumn(name = "userId")
    private User user;

}
