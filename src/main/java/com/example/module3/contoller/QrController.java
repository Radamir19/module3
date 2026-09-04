package com.example.module3.contoller;

import com.example.module3.entity.DTO.QrDto;
import com.example.module3.service.QrService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/qr")
public class QrController {
    private final QrService service;

    public QrController(QrService service) {
        this.service = service;
    }

    @PostMapping("create_client/{client_id}")
    public QrDto create(@PathVariable(name = "client_id") Long id) {
        return service.createQr(id);
    }

    @GetMapping("get_qr/{qr_id}")
    public QrDto getById(@PathVariable(name = "qr_id") Long id) {
        return service.getById(id);
    }

    @PutMapping("update_qr/{qr_id}")
    public QrDto update(@PathVariable(name = "qr_id") Long id) {
       return service.updateQr(id);
    }

    @DeleteMapping("delete_qr/{qr_id}")
    public void delete(@PathVariable(name = "qr_id") Long id) {
        service.deleteQr(id);
    }

    @PostMapping("login/{qr}")
    public String login(@PathVariable UUID qr) {
        return service.login(qr);
    }
}
