package org.example.doorhub.address.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDto extends AddressBaseDto {

    @NotNull
    private Integer id;
    private String locationName;
    private String name;
    private String home;

}
