package com.home.stocktaking.controller;

import com.home.stocktaking.model.User;
import com.home.stocktaking.service.LoginService;
import com.home.stocktaking.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class UserController {
    private final LoginService loginService;
    private final UserService userService;

    public UserController(LoginService loginService, UserService userService) {
        this.loginService = loginService;
        this.userService = userService;
    }

    /**
     * Авторизация сервиса
     *
     * @param authKey authKey
     * @return token
     */
    @PostMapping("api/login")
    public Object login(@RequestHeader("Authorization") String authKey) {
        return loginService.auth(authKey);
    }

    /**
     * Получить пользователей
     *
     * @param token ключ авторизации для inventory сервиса
     * @return список пользователей
     */
    @GetMapping("api/user/list")
    public List<User> getAllUser(@RequestHeader("x-auth-token") String token) {
        return userService.getAllUser();
    }
}
