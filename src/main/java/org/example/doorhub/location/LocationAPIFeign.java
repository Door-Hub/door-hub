package org.example.doorhub.location;

import org.example.doorhub.location.dto.LocationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "location", url = "https://maps.googleapis.com/maps/api/geocode/json")
public interface LocationAPIFeign {

    @GetMapping
    LocationResponse getLocation(@RequestParam("key") String key,
                                 @RequestParam("latlng") String latLng);

}
