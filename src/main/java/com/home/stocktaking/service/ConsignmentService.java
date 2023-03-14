package com.home.stocktaking.service;

import com.home.stocktaking.dto.InvoiceDto;
import com.home.stocktaking.dto.EquipDto;
import com.home.stocktaking.model.Invoice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ConsignmentService {
    private final RequestService service;
    private final EquipmentService equipmentService;
    @Value("${invoiceCreate}")
    private String invoiceCreate;
    @Value("${invoiceSend}")
    private String invoiceSend;

    public ConsignmentService(RequestService service, EquipmentService equipmentService) {
        this.service = service;
        this.equipmentService = equipmentService;
    }

    /**
     * Создания накладной, добавления оборудования в накладную и ее отправка
     *
     * @param token аутентификации в сервисе
     * @param dto   данные для накладной
     * @return
     */
    public Object stockTaking(String token, InvoiceDto dto) {
        ResponseEntity<Object> response = null;
        Invoice invoice;
        try {
            invoice = consignmentCreate(token, dto);
            if (invoice.getNumber() != null) {
                addEquipmentToConsignment(token, invoice, dto);
                response = ResponseEntity.ok(consignmentSend(token, invoice));
            }
            if (dto.getStateId() != null) {
                changeStatus(token, dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Создать накладную
     *
     * @param token аутентификации в сервисе
     * @param dto   данные для накладной
     * @return накладная
     * @throws Exception
     */
    private Invoice consignmentCreate(String token, InvoiceDto dto) throws Exception {
        return (Invoice) service.sendRequest(token, dto, invoiceCreate, new Invoice());

    }

    /**
     * Добавить оборудование в накладную
     *
     * @param token       аутентификации в сервисе
     * @param consignment созданная накладная
     * @param dto         данные накладной
     * @throws Exception
     */
    private void addEquipmentToConsignment(String token, Invoice consignment, InvoiceDto dto) throws Exception {
        dto.setNumber(consignment.getNumber());
        dto.setConsignmentId(consignment.getId());
        equipmentService.invoiceAdd(token, dto);
    }

    /**
     * Отправить накладную
     *
     * @param token аутентификации в сервисе
     * @param dto   данные накладной
     * @return
     * @throws Exception
     */
    private Object consignmentSend(String token, Invoice dto) throws Exception {
        return service.sendRequest(token, dto, invoiceSend, "");
    }

    /**
     * Изменить статус оборудования
     *
     * @param token аутентификации в сервисе
     * @param dto   данные оборудования
     * @throws Exception
     */
    private void changeStatus(String token, InvoiceDto dto) throws Exception {
        equipmentService.changeStatus(token, new EquipDto(dto.getStateId(), dto.getEquipmentsId()));
    }

}
