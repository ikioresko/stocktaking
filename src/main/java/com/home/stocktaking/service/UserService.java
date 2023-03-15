package com.home.stocktaking.service;

import com.home.stocktaking.model.Place;
import com.home.stocktaking.model.User;
import com.home.stocktaking.repository.SpecificLocationRepo;
import com.home.stocktaking.repository.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserService {
    private final UserRepo repo;
    private final SpecificLocationRepo specificLocationRepo;

    public UserService(UserRepo repo, SpecificLocationRepo specificLocationRepo) {
        this.repo = repo;
        this.specificLocationRepo = specificLocationRepo;
    }

    /**
     * Получить пользователей с локациями
     *
     * @return список пользователей с локациями
     */
    @Transactional(readOnly = true)
    public List<User> getAllUser() {
        return setLocationsFullName();
    }

    /**
     * Формирует полное имя локации для всех пользователей
     * При запросе из БД возвращается список SpecificLocation.
     *
     * @return Список пользователей с полным именем локаций
     */
    private List<User> setLocationsFullName() {
        List<Object[]> allUsersWithAllLocationPaths = specificLocationRepo.findAllUsersWithAllLocationPaths();
        Map<User, User> userMap = new HashMap<>();
        for (Object[] obj : allUsersWithAllLocationPaths) {
            User user = new User();
            user.setId(UUID.fromString((String) obj[0]));
            Place location = new Place(UUID.fromString((String) obj[2]), (String) obj[3]);
            user.getPlace().add(location);
            user.setName((String) obj[1]);
            if (!userMap.containsKey(user)) {
                userMap.put(user, user);
            } else {
                userMap.get(user).getPlace().add(location);
            }
        }
        return new ArrayList<>(userMap.values());
    }

    /**
     * Получить пользователя по логину
     *
     * @param login логин
     * @return пользователь с логином
     */
    @Transactional(readOnly = true)
    public User getUserByLogin(String login) {
        return repo.findByLogin(login).orElseThrow();
    }
}
