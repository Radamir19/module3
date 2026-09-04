package com.example.module3.service.mapper;

import com.example.module3.entity.Client;
import com.example.module3.entity.DTO.QrDto;
import com.example.module3.entity.Qr;

public class QrMapper {

    public static QrDto toDto(Qr qr) {
        return new QrDto(
                qr.getId(),
                qr.getCode()
        );
    }

    public static Qr toEntity(QrDto dto) {
        Qr qr = new Qr();
        Client client = new Client();
        qr.setClient(client);
        return qr;
    }
}
