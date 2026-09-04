package com.example.module3.service.mapper;

import com.example.module3.entity.Client;
import com.example.module3.entity.DTO.ClientDto;
import com.example.module3.entity.DTO.QrDto;

import java.util.List;

public class ClientMapper {

    public static ClientDto toDto(Client client) {
        List<QrDto> codes = client.getCodes().stream().map(QrMapper::toDto).toList();
        return new ClientDto(
                client.getId(),
                client.getFullName(),
                codes
        );
    }

    public static Client toEntity(ClientDto dto) {
        Client client = new Client();
        client.setFullName(dto.full_name());
        return client;
    }
}
