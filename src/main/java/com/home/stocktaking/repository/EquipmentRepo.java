package com.home.stocktaking.repository;

import com.home.stocktaking.model.Equipment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EquipmentRepo extends CrudRepository<Equipment, UUID> {
    @Query
    List<Equipment> findByInventoryNumber(String inventoryNumber);

    @Query
    List<Equipment> findBySerialNumber(String serialNumber);
}
