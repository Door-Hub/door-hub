package org.example.doorhub.address.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressUpdateDto {
    private String name;
    private Double longitude;
    private Double latitude;
}
