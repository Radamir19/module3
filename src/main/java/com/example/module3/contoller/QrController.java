package com.example.module3.contoller;

import com.example.module3.entity.dto.QrDto;
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

    @GetMapping("/{id}")
    public ResponseEntity<QrDto> getById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(qrService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<QrDto> update(@PathVariable(name = "id") Long id) {
       return ResponseEntity.ok(qrService.updateQr(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        qrService.deleteQr(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UUID qr) {
        return ResponseEntity.ok(qrService.login(qr));
    }
}
