package org.example.doorhub.address.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressUpdateDto extends AddressBaseDto {

    @NotNull
    private Double longitude;
    @NotNull
    private Double latitude;
}
