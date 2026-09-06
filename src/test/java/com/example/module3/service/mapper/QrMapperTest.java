package com.example.module3.service.mapper;

import com.example.module3.entity.Client;
import com.example.module3.entity.dto.QrDto;
import com.example.module3.entity.Qr;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QrMapperTest {
    private final QrMapper qrMapper = new QrMapper();

    @Test
    void testToDto() {
        Qr qr = new Qr(new Client());
        QrDto qrDto = qrMapper.toDto(qr);
        Assertions.assertEquals(qr.getId(), qrDto.id());
        Assertions.assertEquals(qr.getCode(), qrDto.code());
    }
}
