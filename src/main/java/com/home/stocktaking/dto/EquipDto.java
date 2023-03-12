package com.home.stocktaking.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class EquipDto {
    private UUID stateId;
    private List<UUID> equipmentsId = new ArrayList<>();
    private String comment;

    public EquipDto() {
    }

    public EquipDto(UUID stateId) {
        this.stateId = stateId;
    }

    public EquipDto(UUID stateId, List<UUID> equipmentsId) {
        this.stateId = stateId;
        this.equipmentsId = equipmentsId;
        this.comment = "";
    }

    public UUID getStateId() {
        return stateId;
    }

    public void setStateId(UUID stateId) {
        this.stateId = stateId;
    }

    public List<UUID> getEquipmentsId() {
        return equipmentsId;
    }

    public void setEquipmentsId(List<UUID> equipmentsId) {
        this.equipmentsId = equipmentsId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EquipDto that = (EquipDto) o;
        return Objects.equals(stateId, that.stateId)
                && Objects.equals(equipmentsId, that.equipmentsId)
                && Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stateId, equipmentsId, comment);
    }
}
