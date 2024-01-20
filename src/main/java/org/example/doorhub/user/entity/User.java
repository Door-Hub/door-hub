package org.example.doorhub.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.doorhub.address.entity.Address;
import org.example.doorhub.category.entity.Category;
import org.example.doorhub.listeners.UserCreatedUpdated;
import org.example.doorhub.payment.entity.Payment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "`user`")
@EntityListeners(UserCreatedUpdated.class)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastname;
    private String username;
    private String phoneNumber;
    private String email;
    private String password;
    private String gender;
    private LocalDate brithDate;
    private boolean phoneNumberVerification;
    private LocalDateTime created;
    private LocalDateTime updated;


    @ManyToMany
    @JoinTable
    (
            name = "users_addresses" , joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "addresses_id")
    )
    private List<Address> addresses;

    

    @OneToMany(mappedBy = "user")
    private List<Payment> payments;


    @OneToMany(mappedBy = "user")
    private List<Category> categories;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }


    @Override
    public String getUsername() {
        return phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
