package com.home.stocktaking.controller;

import com.home.stocktaking.model.Place;
import com.home.stocktaking.service.LocationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class PlaceController {
    private final LocationService locationService;

    public PlaceController(LocationService locationService) {
        this.locationService = locationService;
    }

    /**
     * Получить все локации
     *
     * @param token ключ
     * @return список локаций
     */
    @GetMapping("/api/location/list")
    public List<Place> getAllLocation(@RequestHeader("x-auth-token") String token) {
        return locationService.getAllLocation();
    }
}
