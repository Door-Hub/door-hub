package org.example.doorhub.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSignInDto {
    @NotBlank
    @Pattern(regexp = "^9989[0-9]{8}$", message = "Invalid phone number format")
    private String phoneNumber;



}
