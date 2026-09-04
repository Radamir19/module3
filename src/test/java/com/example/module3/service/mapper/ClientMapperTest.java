package com.example.module3.service.mapper;

import com.example.module3.entity.Client;
import com.example.module3.entity.DTO.ClientDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClientMapperTest {
    private final ClientMapper clientMapper = new ClientMapper();

    @Test
    void testToDto() {
        Client client = new Client();
        client.setName("Иван");
        client.setSurname("Иванов");
        client.setPatronymic("Иванович");
        ClientDto clientDto = clientMapper.toDto(client);
        String result = clientDto.name() + " " + clientDto.surname() + " " + clientDto.patronymic();
        Assertions.assertEquals(client.getFullName(), result);
    }

    @Test
    void testToEntity() {
        ClientDto clientDto = new ClientDto(1L, "Ivan", "Ivanov", "Ivanovich");
        Client client = clientMapper.toEntity(clientDto);
        String result = clientDto.name() + " " + clientDto.surname() + " " + clientDto.patronymic();
        Assertions.assertEquals(result, client.getFullName());
    }
}
