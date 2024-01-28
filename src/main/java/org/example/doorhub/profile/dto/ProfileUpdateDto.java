package org.example.doorhub.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfileUpdateDto {
    private String lastname;
    private String avatar;
    private String username;
    private String phoneNumber;


}
