package org.example.doorhub.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.example.doorhub.address.entity.Address;
import org.example.doorhub.attachment.entity.Attachment;
import org.example.doorhub.book.entity.Book;
import org.example.doorhub.category.entity.Category;
import org.example.doorhub.listeners.UserCreatedUpdated;
import org.example.doorhub.review.entity.Review;
import org.example.doorhub.user.permission.UserPermissions;
import org.example.doorhub.user.role.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    private String phoneNumber;
    private String email;
    private String gender;
    private LocalDate brithDate;
    private LocalDateTime created;
    private LocalDateTime updated;

    @OneToMany(mappedBy = "user")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Address> addresses;

    @OneToMany(mappedBy = "user")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Category> categories;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role"))
    private List<Role> roles;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_permission", joinColumns = @JoinColumn(name = "user_id"))
    private List<UserPermissions> permissions;


    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne( cascade = CascadeType.ALL)
    private Attachment attachment;


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id" , referencedColumnName = "id")
    private Book book;


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<SimpleGrantedAuthority> collect = roles.stream()
                .map(role -> role.getPermissions().stream())
                .map(permissions -> new SimpleGrantedAuthority(permissions.toString()))
                .collect(Collectors.toSet());

        Set<SimpleGrantedAuthority> permission =
                permissions.stream().map(permissions -> new SimpleGrantedAuthority(permissions.toString())).collect(Collectors.toSet());

        collect.addAll(permission);

        return collect;
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
