package com.example.module3.service;

import com.example.module3.entity.Client;
import com.example.module3.entity.DTO.ClientDto;
import com.example.module3.exception.NotFoundException;
import com.example.module3.repository.ClientRepository;
import com.example.module3.service.mapper.ClientMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public List<ClientDto> getAll() {
        return repository.findAll().stream().map(ClientMapper::toDto).toList();
    }
    public ClientDto getById(Long id) {
        Client client = repository.findById(id).orElseThrow(() -> new NotFoundException("Клиент с id = " + id + " не найден"));
        return ClientMapper.toDto(client);
    }


    public ClientDto create(String fullName) {
        Client client = new Client();
        client.setFullName(fullName);
        client.addCode();
        repository.save(client);
        try {
            return ClientMapper.toDto(client);
        } catch (Exception e) {
            repository.delete(client);
            throw e;
        }
    }

    public ClientDto updateById(Long id, String fullName) {
        Client client = repository.findById(id).orElseThrow(() -> new NotFoundException("Клиент с id = " + id + " не найден."));
        client.setFullName(fullName);
        repository.save(client);
        return ClientMapper.toDto(client);
    }

    public void deleteById(Long id) {
        Client client = repository.findById(id).orElseThrow(() -> new NotFoundException("Клиент с id = " + id + " не найден."));
        repository.delete(client);
    }
}
