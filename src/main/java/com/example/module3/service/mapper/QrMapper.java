package com.example.module3.service.mapper;

import com.example.module3.entity.Client;
import com.example.module3.entity.DTO.QrDto;
import com.example.module3.entity.Qr;
import org.springframework.stereotype.Component;

@Component
public class QrMapper {

    public QrDto toDto(Qr qr) {
        return new QrDto(
                qr.getId(),
                qr.getCode()
        );
    }

}
