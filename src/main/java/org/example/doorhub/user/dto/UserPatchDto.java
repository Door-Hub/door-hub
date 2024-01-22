package org.example.doorhub.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserPatchDto {

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    private String username;

    @NotBlank
    @Pattern(regexp = "^998\\d{9}$", message = "pattern.phone.number")
    private String phoneNumber;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private String gender;

    private LocalDate brithDate;
}
