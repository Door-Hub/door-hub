package org.example.doorhub.location;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.doorhub.location.dto.LocationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationAPIFeign locationAPIFeign;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${location.api.key}")
    private String LOCATION_KEY;

    public LocationResponse getLocationName(Double latitude, Double longitude)  {
        String location = locationAPIFeign.getLocation(LOCATION_KEY, latitude.toString() + "," + longitude.toString());

        System.out.println(location);

        try {
            return objectMapper.readValue(location, LocationResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
