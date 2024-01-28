package org.example.doorhub.address.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressBaseDto {

    @NotBlank
    private String name;
    private String home;
    private Integer userId;

}
