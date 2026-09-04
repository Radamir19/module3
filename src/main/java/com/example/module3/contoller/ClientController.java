package com.example.module3.contoller;

import com.example.module3.entity.DTO.ClientDto;
import com.example.module3.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/client")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping("/getAllClients")
    public List<ClientDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    public ClientDto findClient(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/update/{id}")
    public ClientDto updateClient(@PathVariable Long id, @RequestParam String fullName) {
        return service.updateById(id, fullName);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteClient(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PostMapping("/register")
    public ClientDto register(@RequestBody ClientDto registerClient) {
        return service.create(registerClient.full_name());
    }
}
