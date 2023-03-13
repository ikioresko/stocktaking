package com.home.stocktaking.repository;

import com.home.stocktaking.model.Place;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlaceRepo extends CrudRepository<Place, UUID> {
}
