package org.example.doorhub.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCreateDto {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String email;
}
