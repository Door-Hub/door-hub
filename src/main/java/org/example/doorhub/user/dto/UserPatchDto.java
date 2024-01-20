package org.example.doorhub.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserPatchDto {
    private String firstname;
    private String lastname;
    private String username;
    private String phoneNumber;
    private String email;
    private String password;
    private String gender;
    private LocalDate brithDate;
}
