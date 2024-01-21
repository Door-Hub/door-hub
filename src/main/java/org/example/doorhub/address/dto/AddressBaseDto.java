package org.example.doorhub.address.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressBaseDto {

    private String name;
    private Double longitude;
    private Double latitude;
}
