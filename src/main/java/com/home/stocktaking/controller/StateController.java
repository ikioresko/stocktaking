package com.home.stocktaking.controller;

import com.home.stocktaking.model.State;
import com.home.stocktaking.service.StateService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class StateController {
    private final StateService stateService;

    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    /**
     * Получить все состояния оборудования
     *
     * @param token ключ
     * @return список состояний
     */
    @GetMapping("/api/state/list")
    public List<State> getAllState(@RequestHeader("x-auth-token") String token) {
        return stateService.getAllState();
    }

}
