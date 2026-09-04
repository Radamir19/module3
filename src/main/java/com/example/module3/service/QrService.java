package com.example.module3.service;

import com.example.module3.entity.Client;
import com.example.module3.entity.DTO.QrDto;
import com.example.module3.entity.Qr;
import com.example.module3.exception.NotFoundException;
import com.example.module3.repository.ClientRepository;
import com.example.module3.repository.QrRepository;
import com.example.module3.service.mapper.QrMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class QrService {

    private final QrRepository qrRepository;

    private final ClientRepository clientRepository;

    public QrService(QrRepository qrRepository, ClientRepository clientRepository) {
        this.qrRepository = qrRepository;
        this.clientRepository = clientRepository;
    }

    public QrDto getById(Long id) {
        Qr qr = qrRepository.findById(id).orElseThrow(() -> new NotFoundException("Qr с id = " + id + " не найден."));
        return QrMapper.toDto(qr);
    }
    public QrDto createQr(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException("Клиент с id = " + id + " не найден."));
        Qr qr = client.addCode();
        clientRepository.save(client);
        return QrMapper.toDto(qr);
    }

    public QrDto updateQr(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException("Клиент с id = " + id + " не найден."));
        Qr qr = client.addCode();
        clientRepository.save(client);
        return QrMapper.toDto(qr);
    }

    public void deleteQr(Long id) {
        Qr qr = qrRepository.findById(id).orElseThrow(() -> new NotFoundException("Qr с id = " + id + " не найден."));
        qr.setCode(null);
        qrRepository.save(qr);
    }

    public String login(UUID qr) {
        Qr findQr = qrRepository.findByCode(qr).orElseThrow(() -> new NotFoundException("Такой qr код не существует."));
        Client client = findQr.getClient();
        findQr.setCode(null);
        client.addCode();
        clientRepository.save(client);
        return client.getFullName();
    }
}
