package org.example.doorhub.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfileResponseDto
{
    private Integer id;
    private String lastname;
    private String avatar;
    private String username;
    private String phoneNumber;
    private String email;
    private String gender;
    private LocalDate brithDate;
}
