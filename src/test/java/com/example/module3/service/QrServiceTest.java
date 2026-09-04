package com.example.module3.service;

import com.example.module3.entity.Client;
import com.example.module3.entity.DTO.QrDto;
import com.example.module3.entity.Qr;
import com.example.module3.exception.NotFoundException;
import com.example.module3.repository.ClientRepository;
import com.example.module3.repository.QrRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QrServiceTest {
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private QrRepository qrRepository;
    @InjectMocks
    private QrService service;

    @Test
    public void createTest() {
        Client client = new Client();
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        service.createQr(1L);
        verify(clientRepository).save(client);
    }

    @Test
    public void readTest() {
        Qr qr = new Qr();
        Client client = new Client();
        client.setFullName("Иванов Иван Иванович");
        when(qrRepository.findById(1L)).thenReturn(Optional.of(qr));
        UUID code = UUID.randomUUID();
        qr.setCode(code);
        qr.setClient(client);
        QrDto dto = service.getById(1L);
        Assertions.assertEquals(code, dto.code());
    }

    @Test
    public void updateTest() {
        Client client = new Client();
        client.setFullName("Иванов Иван Иванович");
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        service.updateQr(1L);
        verify(clientRepository).save(client);
    }

    @Test
    public void deleteTest() {
        Qr qr = new Qr();
        when(qrRepository.findById(1L)).thenReturn(Optional.of(qr));
        service.deleteQr(1L);
        Assertions.assertEquals(null, qr.getCode());
        verify(qrRepository).save(qr);
    }

    @Test
    public void login() {
        Qr qr = new Qr();
        UUID code = UUID.randomUUID();
        Client client = new Client();
        when(qrRepository.findByCode(code)).thenReturn(Optional.of(qr));
        qr.setCode(code);
        qr.setClient(client);
        String input = "Иванов Иван Иванович";
        client.setFullName(input);
        String result = service.login(code);
        Assertions.assertEquals(input, result);
    }

    @Test
    public void loginFail() {
        UUID code = UUID.randomUUID();
        when(qrRepository.findByCode(code)).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> service.login(code));
    }
}
