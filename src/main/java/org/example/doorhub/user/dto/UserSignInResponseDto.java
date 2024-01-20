package org.example.doorhub.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSignInResponseDto {
    private String phoneNumber;
    private String token;
}
