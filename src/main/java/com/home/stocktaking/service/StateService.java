package com.home.stocktaking.service;

import com.home.stocktaking.model.State;
import com.home.stocktaking.repository.StateRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StateService {
    private final StateRepo repo;

    public StateService(StateRepo repo) {
        this.repo = repo;
    }

    /**
     * Получить все статусы
     *
     * @return список статусов
     */
    @Transactional(readOnly = true)
    public List<State> getAllState() {
        return (List<State>) repo.findAll();
    }
}
