package com.home.stocktaking.service;

import com.home.stocktaking.dto.InvoiceDto;
import com.home.stocktaking.dto.EquipDto;
import com.home.stocktaking.model.Equipment;
import com.home.stocktaking.repository.EquipmentRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EquipmentService {
    private final EquipmentRepo repo;
    private final RequestService service;
    @Value("${consignmentAdd}")
    private String consignmentAdd;
    @Value("${equipmentChangeState}")
    private String equipmentChangeState;

    public EquipmentService(EquipmentRepo repo, RequestService service) {
        this.repo = repo;
        this.service = service;
    }

    /**
     * Получить инфо по оборудованию из БД
     *
     * @param inventoryNumber инвентарный или серийный номер
     * @return инфо по оборудованию
     */
    @Transactional(readOnly = true)
    public Object getEquipment(String inventoryNumber) {
        List<Equipment> byInventoryNumber;
        String answer = "false";
        try {
            byInventoryNumber = repo.findByInventoryNumber(inventoryNumber);
            if (byInventoryNumber.isEmpty()) {
                byInventoryNumber = repo.findBySerialNumber(inventoryNumber);
                if (byInventoryNumber.size() == 0) {
                    throw new NoSuchElementException("false");
                }
            }
            if (byInventoryNumber.size() > 1) {
                throw new IllegalStateException(
                        "Нельзя отобразить данные. Найдено более одного экземпляра оборудования с указанным номером " + inventoryNumber);
            }
        } catch (NoSuchElementException | IllegalStateException e) {
            answer = e.getMessage();
            byInventoryNumber = null;
        }
        return byInventoryNumber == null ? answer : byInventoryNumber.get(0);
    }

    /**
     * Добавить оборудование в накладную
     *
     * @param token для аутентификации в сервисе
     * @param dto   данные для накладной
     */
    public void invoiceAdd(String token, InvoiceDto dto) throws Exception {
        service.sendRequest(token, dto, consignmentAdd, new Object());
    }

    /**
     * Изменить статус оборудования
     */
    public void changeStatus(String token, EquipDto equipmentDto) throws Exception {
        service.sendRequest(token, equipmentDto, equipmentChangeState, "");
    }
}
