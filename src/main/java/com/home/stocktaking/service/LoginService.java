package com.home.stocktaking.service;

import com.home.stocktaking.model.Group;
import com.home.stocktaking.model.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
public class LoginService {
    private final UserService userService;
    private final RestTemplate restTemplate;
    @Value("${login}")
    private String serviceURL;

    public LoginService(UserService userService, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.userService = userService;
    }

    /**
     * Авторизация
     *
     * @param authKey ключ авторизации
     * @return обьект авторизации
     */
    public Object auth(String authKey) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", authKey);
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<Object> response;
        try {
            response = restTemplate.postForEntity(serviceURL, request, Object.class);
            String login = getLoginFromResponse(response);
            if (!checkGroup(login)) {
                throw new IllegalAccessException("Нет прав доступа. Обратитесь к администратору");
            }
        } catch (IllegalAccessException e) {
            response = ResponseEntity.status(403).body(e.getMessage());
        } catch (Exception e) {
            response = "401 : [no body]".equals(e.getMessage()) ? ResponseEntity.status(401).build() : ResponseEntity.status(500).build();
        }
        return response;
    }

    /**
     * Парсит логин пользователя
     *
     * @param response от основного inventory после авторизации
     * @return логин пользователя
     */
    private String getLoginFromResponse(ResponseEntity<Object> response) {
        String body = Objects.requireNonNull(response.getBody()).toString().replaceAll("=", ":");
        JSONObject jsonObject = new JSONObject(body);
        return jsonObject.getJSONObject("details").getString("login");
    }

    /**
     * Проверка пользователя на наличие группы
     *
     * @param login авторизованного пользователя
     * @return boolean
     */
    private boolean checkGroup(String login) {
        boolean result = false;
        List<String> groups = List.of("GroupTest");
        User userByLogin = userService.getUserByLogin(login);
        for (Group group : userByLogin.getGroup()) {
            if (groups.contains(group.getName())) {
                result = true;
                break;
            }
        }
        return result;
    }
}
