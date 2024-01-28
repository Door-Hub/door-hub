package org.example.doorhub.address.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDto  {

    @NotNull
    private Integer id;
    private String name;
    private String home;
    private String locationName;
    private Integer userId;

}
