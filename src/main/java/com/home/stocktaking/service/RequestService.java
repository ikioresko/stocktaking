package com.home.stocktaking.service;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RequestService {
    private final RestTemplate restTemplate;

    public RequestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Генерирует сущность запроса
     *
     * @param token для аутентификации в inventory
     * @param dto   данные для накладной
     * @return сущность запроса
     */
    private HttpEntity<String> getHttpEntity(String token, Object dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("x-auth-token", token);
        JSONObject json = new JSONObject(dto);
        return new HttpEntity<>(json.toString(), headers);
    }

    /**
     * Отправить запрос в сервис
     *
     * @param token  для аутентификации
     * @param dto    данные для накладной
     * @param url    путь api
     * @param object объект данных
     * @return результат запроса
     * @throws Exception
     */
    public Object sendRequest(String token, Object dto, String url, Object object) throws Exception {
        HttpEntity<String> request = getHttpEntity(token, dto);
        object = restTemplate.postForObject(url, request, object.getClass());
        return object;
    }
}
