package org.example.doorhub.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserUpdateDto {

    @NotBlank
    private String firstname;
    private String lastname;
    private String username;
    @NotBlank
    @Pattern(regexp = "^9989[0-9]{8}$", message = "Invalid phone number format")
    private String phoneNumber;

    @Email
    private String email;
    @NotBlank
    private String gender;
    private LocalDate birthDate;
    private String avatar;

}
