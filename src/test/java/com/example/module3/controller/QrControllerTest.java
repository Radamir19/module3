package com.example.module3.controller;

import com.example.module3.contoller.QrController;
import com.example.module3.entity.dto.QrDto;
import com.example.module3.exception.NotFoundException;
import com.example.module3.service.QrService;
import com.example.module3.service.mapper.QrMapper;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(QrController.class)
public class QrControllerTest {
    @MockitoBean
    private QrService service;
    @MockitoBean
    private QrMapper qrMapper;
    @Autowired
    private MockMvc mockMvc;
    private QrDto example() {
        return new QrDto(1L, UUID.randomUUID());
    }

    @Test
    void findQrTest() throws Exception {
        QrDto qr = example();
        when(service.getById(1L)).thenReturn(qr);
        mockMvc.perform(get("/api/v1/qr/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.code").value(qr.code().toString()));
    }

    @Test
    void updateQrReturnsNewCodeTest() throws Exception {
        QrDto oldQr = example();
        QrDto newQr = new QrDto(1L, UUID.randomUUID());
        when(service.updateQr(1L)).thenReturn(newQr);

        mockMvc.perform(put("/api/v1/qr/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(newQr.code().toString()));
    }

    @Test
    void deleteQr() throws Exception {
        mockMvc.perform(delete("/api/v1/qr/1"))
                .andExpect(status().isNoContent());
        verify(service).deleteQr(1L);
    }

    @Test
    void loginTest() throws Exception {
        QrDto qr = example();
        when(service.login(qr.code())).thenReturn("Иванов Иван Иванович");
        mockMvc.perform(post("/api/v1/qr/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("\"" + qr.code() + "\""))
                .andExpect(status().isOk())
                .andExpect(content().string("Иванов Иван Иванович"));
    }

    @Test
    void loginTestNotFoundException() throws Exception {
        UUID code = UUID.randomUUID();
        when(service.login(code)).thenThrow(new NotFoundException("Qr с таким айди не найден."));
        mockMvc.perform(post("/api/v1/qr/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"" + code + "\""))
                .andExpect(status().isNotFound());
    }
}
