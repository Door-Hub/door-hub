package org.example.doorhub.location.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationResponse {

    @JsonProperty("results")
    private GeocodingResult[] results;

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class GeocodingResult {

        @JsonProperty("formatted_address")
        private String formattedAddress;


    }




}
