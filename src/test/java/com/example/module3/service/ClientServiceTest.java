package com.example.module3.service;

import com.example.module3.entity.Client;
import com.example.module3.entity.dto.ClientDto;
import com.example.module3.repository.ClientRepository;
import com.example.module3.service.mapper.ClientMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
    @Mock
    private ClientRepository repository;
    @Mock
    private ClientMapper clientMapper;
    @InjectMocks
    private ClientService service;

    @Test
    public void createTest() {
        ClientDto expected = new ClientDto(1L, "Иван", "Иванов", "Иванович");
        when(clientMapper.toDto(any(Client.class))).thenReturn(expected);
        ClientDto dto = service.create("Иван", "Иванов", "Иванович");
        Assertions.assertEquals(expected, dto);
        verify(repository).save(any(Client.class));
    }

    @Test
    public void readTest() {
        Client client = new Client();
        ClientDto expected = new ClientDto(1L, "Иван", "Иванов", "Иванович");
        when(repository.findById(1L)).thenReturn(Optional.of(client));
        when(clientMapper.toDto(client)).thenReturn(expected);
        ClientDto dto = service.getById(1L);
        Assertions.assertEquals(expected, dto);
    }

    @Test
    public void updateTest() {
        Client client = new Client();
        ClientDto expected = new ClientDto(1L, "Пётр", "Петров", "Петрович");
        when(repository.findById(1L)).thenReturn(Optional.of(client));
        when(clientMapper.toDto(client)).thenReturn(expected);

        ClientDto dto = service.updateById(1L, expected);

        Assertions.assertEquals(expected, dto);
        Assertions.assertEquals("Петров", expected.surname());
        verify(repository).save(client);
    }

    @Test
    public void deleteTest() {
        Client client = new Client();
        when(repository.findById(1L)).thenReturn(Optional.of(client));
        service.deleteById(1L);
        verify(repository).delete(client);
    }
}
