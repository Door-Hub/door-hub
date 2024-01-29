package org.example.doorhub.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.doorhub.user.entity.Role;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCreateDto {

    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @NotBlank
    @Pattern(regexp = "^9989[0-9]{8}$")
    private String phoneNumber;
    @Email
    private String email;
    private String gender;
    private List<Role> role;


}
