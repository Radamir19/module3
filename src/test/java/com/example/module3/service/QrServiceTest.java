package com.example.module3.service;

import com.example.module3.entity.Client;
import com.example.module3.entity.DTO.QrDto;
import com.example.module3.entity.Qr;
import com.example.module3.exception.NotFoundException;
import com.example.module3.repository.ClientRepository;
import com.example.module3.repository.QrRepository;
import com.example.module3.service.mapper.QrMapper;
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
    @Mock
    private QrMapper qrMapper;
    @InjectMocks
    private QrService qrService;

    @Test
    public void createTest() {
        Client client = new Client();
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        qrService.createQr(1L);
        verify(clientRepository).save(client);
    }

    @Test
    public void readTest() {
        Qr qr = new Qr();
        QrDto expected = new QrDto(1L, UUID.randomUUID());
        when(qrRepository.findById(1L)).thenReturn(Optional.of(qr));
        when(qrMapper.toDto(qr)).thenReturn(expected);
        QrDto dto = qrService.getById(1L);
        Assertions.assertEquals(expected, dto);
    }

    @Test
    public void updateTest() {
        Client client = new Client();
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        qrService.updateQr(1L);
        verify(clientRepository).save(client);
    }

    @Test
    public void deleteTest() {
        Qr qr = new Qr();
        when(qrRepository.findById(1L)).thenReturn(Optional.of(qr));
        qrService.deleteQr(1L);
        verify(qrRepository).delete(qr);
    }

    @Test
    public void loginTest() {
        UUID code = UUID.randomUUID();
        Client client = new Client();
        client.setSurname("Иванов");
        client.setName("Иван");
        client.setPatronymic("Иванович");
        Qr qr = new Qr();
        qr.setClient(client);
        when(qrRepository.findByCode(code)).thenReturn(Optional.of(qr));
        String result = qrService.login(code);
        Assertions.assertEquals("Иванов Иван Иванович", result);
        verify(clientRepository).save(client);
    }

    @Test
    public void loginFail() {
        UUID code = UUID.randomUUID();
        when(qrRepository.findByCode(code)).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> qrService.login(code));
    }
}
