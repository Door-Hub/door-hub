package org.example.doorhub.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.example.doorhub.address.entity.Address;
import org.example.doorhub.attachment.entity.Attachment;
import org.example.doorhub.book.entity.Book;
import org.example.doorhub.category.parent.entity.ParentCategory;
import org.example.doorhub.listeners.UserCreatedUpdated;
import org.example.doorhub.review.entity.Review;
import org.hibernate.annotations.BatchSize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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
    private String avatar;
    private String username;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String gender;
    private String email;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    private LocalDateTime created;
    private LocalDateTime updated;

    @OneToMany
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(name = "user_addresses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id"))
    private List<Address> addresses;

    @OneToMany()
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(name = "user_categories",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "categoriy_id"))
    private List<ParentCategory> categories;

    @Enumerated(EnumType.STRING)
    private List<Role> roles;


    @OneToOne
    @JsonProperty("attachment_id")
    @JoinColumn(name = "attachment_id")
    private Attachment attachment;



    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book book;


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany()
    @JoinTable(name = "user_reviews",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "review_id"))
    private List<Review> reviews;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//        for (Role role : roles) {
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
//            for (Permission permission : role.getPermissions()) {
//                authorities.add(new SimpleGrantedAuthority(permission.toString()));
//            }
//        }
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return "";
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
