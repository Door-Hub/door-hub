package org.example.doorhub.notification.sms.eskiz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class EskizSmsSentRequestDto {

    @JsonProperty("mobile_phone")
    private String phoneNumber;

    private String message;

    private final String from="4546";

    public EskizSmsSentRequestDto(String phoneNumber, String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }
}
