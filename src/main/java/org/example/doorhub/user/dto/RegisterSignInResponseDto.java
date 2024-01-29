package org.example.doorhub.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterSignInResponseDto {

    private String firstname;

    private String lastname;

    private String phoneNumber;

    private String email;
}
