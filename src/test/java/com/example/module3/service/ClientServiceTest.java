package com.example.module3.service;

import com.example.module3.entity.Client;
import com.example.module3.entity.DTO.ClientDto;
import com.example.module3.repository.ClientRepository;
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

    @InjectMocks
    private ClientService service;

    @Test
    public void createTest() {
        ClientDto dto = service.create("Иванов Иван Иванович");

        Assertions.assertEquals("Иванов Иван Иванович", dto.full_name());
        Assertions.assertEquals(1, dto.codes().size());

        verify(repository).save(any(Client.class));
    }

    @Test
    public void readTest() {
        Client client = new Client();
        client.setFullName("Иванов Иван Иванович");

        when(repository.findById(1L)).thenReturn(Optional.of(client));
        ClientDto dto = service.getById(1L);
        Assertions.assertEquals("Иванов Иван Иванович", dto.full_name());
    }

    @Test
    public void updateTest() {
        Client client = new Client();
        String newName = "Петров Петр Петрович";
        when(repository.findById(1L)).thenReturn(Optional.of(client));
        ClientDto dto = service.updateById(1L, newName);
        Assertions.assertEquals("Петров Петр Петрович", dto.full_name());
    }

    @Test
    public void deleteTest() {
        Client client = new Client();
        when(repository.findById(1L)).thenReturn(Optional.of(client));
        service.deleteById(1L);
        verify(repository).delete(client);
    }
}
