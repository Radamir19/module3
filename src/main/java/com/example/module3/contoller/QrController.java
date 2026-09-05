package com.example.module3.contoller;

import com.example.module3.entity.DTO.QrDto;
import com.example.module3.service.QrService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/qr")
public class QrController {
    private final QrService qrService;

    public QrController(QrService qrService) {
        this.qrService = qrService;
    }

    @PostMapping("/create_qr_for_client/{client_id}")
    public ResponseEntity<QrDto> create(@PathVariable(name = "client_id") Long id) {
        return ResponseEntity.ok(qrService.createQr(id));
    }

    @GetMapping("/get_qr/{qr_id}")
    public ResponseEntity<QrDto> getById(@PathVariable(name = "qr_id") Long id) {
        return ResponseEntity.ok(qrService.getById(id));
    }

    @PutMapping("/update_qr/{qr_id}")
    public ResponseEntity<QrDto> update(@PathVariable(name = "qr_id") Long id) {
       return ResponseEntity.ok(qrService.updateQr(id));
    }

    @DeleteMapping("/delete_qr/{qr_id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "qr_id") Long id) {
        qrService.deleteQr(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login/{qr}")
    public ResponseEntity<String> login(@PathVariable UUID qr) {
        return ResponseEntity.ok(qrService.login(qr));
    }
}
