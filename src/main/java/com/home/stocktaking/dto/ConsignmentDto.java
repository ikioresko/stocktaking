package com.home.stocktaking.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ConsignmentDto {
    private UUID consignmentId;
    private UUID senderId;
    private UUID recipientId;
    private UUID sourceLocationId;
    private UUID destinationLocationId;
    private String courier;
    private String number;
    private UUID stateId;
    private List<UUID> equipmentsId = new ArrayList<>();

    public ConsignmentDto() {
    }

    public UUID getSenderId() {
        return senderId;
    }

    public void setSenderId(UUID senderId) {
        this.senderId = senderId;
    }

    public UUID getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(UUID recipientId) {
        this.recipientId = recipientId;
    }

    public UUID getSourceLocationId() {
        return sourceLocationId;
    }

    public void setSourceLocationId(UUID sourceLocationId) {
        this.sourceLocationId = sourceLocationId;
    }

    public UUID getDestinationLocationId() {
        return destinationLocationId;
    }

    public void setDestinationLocationId(UUID destinationLocationId) {
        this.destinationLocationId = destinationLocationId;
    }

    public String getCourier() {
        return courier;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }

    public UUID getConsignmentId() {
        return consignmentId;
    }

    public void setConsignmentId(UUID consignmentId) {
        this.consignmentId = consignmentId;
    }

    public List<UUID> getEquipmentsId() {
        return equipmentsId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setEquipmentsId(List<UUID> equipmentsId) {
        this.equipmentsId = equipmentsId;
    }

    public UUID getStateId() {
        return stateId;
    }

    public void setStateId(UUID stateId) {
        this.stateId = stateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConsignmentDto that = (ConsignmentDto) o;
        return Objects.equals(consignmentId, that.consignmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consignmentId);
    }
}
