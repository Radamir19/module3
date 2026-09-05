package com.example.module3.service;

import com.example.module3.entity.Client;
import com.example.module3.entity.dto.QrDto;
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

    private final QrMapper qrMapper;

    public QrService(QrRepository qrRepository, ClientRepository clientRepository, QrMapper qrMapper) {
        this.qrRepository = qrRepository;
        this.clientRepository = clientRepository;
        this.qrMapper = qrMapper;
    }

    public QrDto getById(Long id) {
        Qr qr = qrRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Qr с id = " + id + " не найден."));
        return qrMapper.toDto(qr);
    }
    public QrDto createQr(Long id) {
        Client client = clientRepository.findWithCodeById(id)
                .orElseThrow(() -> new NotFoundException("Клиент с id = " + id + " не найден."));
        Qr qr = client.getCode();
        if (qr == null) {
            qr = new Qr();
            qr.setClient(client);
        } else {
            qr.regenerate();
        }
        qrRepository.saveAndFlush(qr);
        return qrMapper.toDto(qr);
    }

    public QrDto updateQr(Long qrId) {
        Qr qr = qrRepository.findById(qrId)
                .orElseThrow(() -> new NotFoundException("Qr с id = " + qrId + " не найден."));
        qr.regenerate();
        qrRepository.saveAndFlush(qr);
        return qrMapper.toDto(qr);
    }
    public void deleteQr(Long id) {
        Qr qr = qrRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Qr с id = " + id + " не найден."));
        qrRepository.delete(qr);
    }

    public String login(UUID qr) {
        Qr findQr = qrRepository.findByCode(qr)
                .orElseThrow(() -> new NotFoundException("Такой qr код не существует."));
        Client client = findQr.getClient();
        findQr.regenerate();
        qrRepository.saveAndFlush(findQr);
        return client.getFullName();
    }
}
