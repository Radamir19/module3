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
    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public List<ClientDto> getAll() {
        List<ClientDto> allClients = clientRepository.findAll().stream()
                .map(clientMapper::toDto)
                .toList();
        return allClients;
    }

    public ClientDto getById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException("Клиент с id = " + id + " не найден"));
        return clientMapper.toDto(client);
    }


    public ClientDto create(String name, String surname, String patronymic) {
        Client client = new Client();
        client.setName(name);
        client.setName(surname);
        client.setPatronymic(patronymic);
        client.changeCode();
        clientRepository.save(client);
        return clientMapper.toDto(client);
    }

    public ClientDto updateById(Long id, String name, String surname, String patronymic) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException("Клиент с id = " + id + " не найден."));
        client.setName(name);
        client.setName(surname);
        client.setPatronymic(patronymic);
        clientRepository.save(client);
        return clientMapper.toDto(client);
    }

    public void deleteById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException("Клиент с id = " + id + " не найден."));
        clientRepository.delete(client);
    }
}
