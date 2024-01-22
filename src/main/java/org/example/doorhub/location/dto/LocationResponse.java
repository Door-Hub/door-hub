package org.example.doorhub.location.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationResponse {


    @JsonProperty("results")
    @NotNull
    private List<Result> results;

    @Data
    public static class Result {
        @JsonProperty("formatted_address")
        private String formattedAddress;

        @Override
        public String toString() {
            return formattedAddress;
        }
    }
}





