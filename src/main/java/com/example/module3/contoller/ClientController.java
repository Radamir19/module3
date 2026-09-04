package com.example.module3.contoller;

import com.example.module3.entity.DTO.ClientDto;
import com.example.module3.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<ClientDto> findClient(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable Long id, @RequestBody ClientDto clientDto) {
        return ResponseEntity.ok(clientService.updateById(id, clientDto.name(), clientDto.surname(), clientDto.patronymic()));
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
