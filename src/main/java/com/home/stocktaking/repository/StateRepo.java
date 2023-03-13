package com.home.stocktaking.repository;

import com.home.stocktaking.model.State;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StateRepo extends CrudRepository<State, UUID> {
}
