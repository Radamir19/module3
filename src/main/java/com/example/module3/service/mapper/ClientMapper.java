package com.example.module3.service.mapper;

import com.example.module3.entity.Client;
import com.example.module3.entity.DTO.ClientDto;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public ClientDto toDto(Client client) {
        return new ClientDto(
                client.getId(),
                client.getName(),
                client.getSurname(),
                client.getPatronymic()
        );
    }

    public Client toEntity(ClientDto dto) {
        Client client = new Client();
        client.setName(dto.name());
        client.setSurname(dto.surname());
        client.setPatronymic(dto.patronymic());
        return client;
    }
}
