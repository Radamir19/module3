package com.example.module3.service;

import com.example.module3.entity.Client;
import com.example.module3.entity.Qr;
import com.example.module3.entity.dto.ClientDto;
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


    public ClientDto create(ClientDto clientDto) {
        Client client = new Client();
        client.setName(clientDto.name());
        client.setSurname(clientDto.surname());
        client.setPatronymic(clientDto.patronymic());
        Qr qr = new Qr(client);
        client.setCode(qr);
        clientRepository.save(client);
        return clientMapper.toDto(client);
    }

    public ClientDto updateById(Long id, ClientDto clientDto) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException("Клиент с id = " + id + " не найден."));
        client.setName(clientDto.name());
        client.setName(clientDto.surname());
        client.setPatronymic(clientDto.patronymic());
        clientRepository.save(client);
        return clientMapper.toDto(client);
    }

    public void deleteById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException("Клиент с id = " + id + " не найден."));
        clientRepository.delete(client);
    }
}
