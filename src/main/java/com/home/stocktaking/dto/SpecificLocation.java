package com.home.stocktaking.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Сущность генерируется в SpecificLocationRepo
 */
@Entity
public class SpecificLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String userId;
    private String userLocationId;
    private String userName;

    private String path;

    public SpecificLocation() {
    }

    public SpecificLocation(String userId, String userLocationId, String path) {
        this.userId = userId;
        this.userLocationId = userLocationId;
        this.path = path;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserLocationId() {
        return userLocationId;
    }

    public void setUserLocationId(String userLocationId) {
        this.userLocationId = userLocationId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SpecificLocation that = (SpecificLocation) o;
        return Objects.equals(userId, that.userId) && Objects.equals(userLocationId, that.userLocationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userLocationId);
    }
}
