package com.home.stocktaking.repository;

import com.home.stocktaking.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends CrudRepository<User, UUID> {
    @Query
    List<User> findDistinctByLocationIsNotNull();

    Optional<User> findByLogin(String login);
}
