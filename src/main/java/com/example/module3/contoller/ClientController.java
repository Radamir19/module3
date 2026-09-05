package com.example.module3.contoller;

import com.example.module3.entity.dto.ClientDto;
import com.example.module3.entity.dto.QrDto;
import com.example.module3.service.ClientService;
import com.example.module3.service.QrService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/clients")
public class ClientController {

    private final ClientService clientService;

    private final QrService qrService;

    public ClientController(ClientService clientService, QrService qrService) {
        this.clientService = clientService;
        this.qrService = qrService;
    }

    @PostMapping("/{clientId}/qr")
    public ResponseEntity<QrDto> create(@PathVariable Long clientId) {
        return ResponseEntity.ok().body(qrService.createQr(clientId));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ClientDto>> findAllClients() {
        return ResponseEntity.ok(clientService.getAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ClientDto> findClient(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable Long id, @RequestBody ClientDto clientDto) {
        return ResponseEntity.ok(clientService.updateById(id, clientDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/register")
    public ResponseEntity<ClientDto> register(@RequestBody ClientDto registerClient) {
        return ResponseEntity.ok(clientService.create(registerClient.name(), registerClient.surname(), registerClient.patronymic()));
    }
}
