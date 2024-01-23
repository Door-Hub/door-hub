package org.example.doorhub.notification.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class EskizSmsSentRequestDto {

    @JsonProperty("mobile_phone")
    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String message;

    private final String from="4546";

    public EskizSmsSentRequestDto(String phoneNumber, String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }
}
