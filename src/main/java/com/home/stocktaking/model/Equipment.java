package com.home.stocktaking.model;


import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "equipment", schema = "stock")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Model model;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Place location;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private State state;
    private String inventoryNumber;
    private String serialNumber;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User accountablePerson;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Model getModel() {
        return model;
    }

    public void setModelId(Model model) {
        this.model = model;
    }

    public Place getLocation() {
        return location;
    }

    public void setLocation(Place location) {
        this.location = location;
    }

    public State getState() {
        return state;
    }

    public void setState(State stateId) {
        this.state = stateId;
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public User getAccountablePerson() {
        return accountablePerson;
    }

    public void setAccountablePerson(User accountablePerson) {
        this.accountablePerson = accountablePerson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Equipment equipment = (Equipment) o;
        return Objects.equals(id, equipment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
