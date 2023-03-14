package com.home.stocktaking.controller;

import com.home.stocktaking.service.EquipmentService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class EquipmentController {
    private final EquipmentService service;

    public EquipmentController(EquipmentService service) {
        this.service = service;
    }

    /**
     * Получить инфо об оборудовании
     *
     * @param token           ключ
     * @param inventoryNumber инвентарный или серийный номер
     * @return инфо об оборудовании
     */
    @GetMapping("/api/equipment/get")
    public Object getEquipment(@RequestHeader("x-auth-token") String token,
                               @RequestParam String inventoryNumber) {
        return service.getEquipment(inventoryNumber);
    }
}
