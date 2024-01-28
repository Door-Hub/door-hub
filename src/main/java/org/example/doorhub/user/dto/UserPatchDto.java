package org.example.doorhub.user.dto;

import jakarta.validation.constraints.NotBlank;
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
    private String phoneNumber;

    @NotBlank
    private String email;

    @NotBlank
    private String gender;

    private LocalDate birthDate;
}
