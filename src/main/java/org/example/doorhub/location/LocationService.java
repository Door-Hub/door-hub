package org.example.doorhub.location;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.doorhub.location.dto.LocationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationAPIFeign locationAPIFeign;

    @Value("${location.api.key}")
    private String LOCATION_KEY;

    @Transactional
    public String getLocationName(Double latitude, Double longitude) {
        LocationResponse location = locationAPIFeign.getLocation(LOCATION_KEY,
                latitude.toString() + "," + longitude.toString());
        return location.getResults().get(0).toString();

    }


}
