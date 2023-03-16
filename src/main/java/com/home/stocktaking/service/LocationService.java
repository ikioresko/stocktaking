package com.home.stocktaking.service;

import com.home.stocktaking.model.Place;
import com.home.stocktaking.repository.PlaceRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class LocationService {
    private final PlaceRepo repo;

    public LocationService(PlaceRepo repo) {
        this.repo = repo;
    }

    /**
     * Получить все локации
     *
     * @return список локаций
     */
    @Transactional(readOnly = true)
    public List<Place> getAllLocation() {
        return (List<Place>) repo.findAll();
    }
}
