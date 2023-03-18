package com.home.stocktaking.controller;

import com.home.stocktaking.dto.InvoiceDto;
import com.home.stocktaking.service.ConsignmentService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class InvoiceController {
    private final ConsignmentService service;

    public InvoiceController(ConsignmentService service) {
        this.service = service;
    }

    /**
     * Создать Накладную
     *
     * @param token ключ
     * @param dto   Данные для накладной
     * @return накладная
     */
    @PostMapping("/api/consig-create")
    public Object createConsignment(@RequestHeader("x-auth-token") String token,
                                    @RequestBody InvoiceDto dto) {
        return service.stockTaking(token, dto);
    }
}
