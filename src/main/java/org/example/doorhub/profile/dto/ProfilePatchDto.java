package org.example.doorhub.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfilePatchDto {
    private String lastname;
    private String avatar;
    private String username;
    private String phoneNumber;


}
