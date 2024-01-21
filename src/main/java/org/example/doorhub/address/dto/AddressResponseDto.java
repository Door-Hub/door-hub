package org.example.doorhub.address.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.doorhub.location.dto.LocationResponse;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDto {

    private Integer id;
    private String name;
    private String locationName;

}
