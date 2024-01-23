package org.example.doorhub.notification.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EskizRefreshResponseDto {

    @NotBlank
    private HashMap<String , String >data;
}
