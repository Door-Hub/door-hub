package org.example.doorhub.profile.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "`profile`")
public class Profile {

    @Id
    private Integer id;
    private String lastname;
    private String avatar;
    private String username;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String email;
    private String gender;
    private LocalDate brithDate;

}
