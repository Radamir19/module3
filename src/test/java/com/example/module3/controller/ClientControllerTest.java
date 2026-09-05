package com.example.module3.controller;

import com.example.module3.contoller.ClientController;
import com.example.module3.entity.dto.ClientDto;
import com.example.module3.exception.NotFoundException;
import com.example.module3.service.ClientService;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {
    @MockitoBean
    private ClientService service;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    private List<ClientDto> example() {
        return List.of(new ClientDto(1L, "Иван", "Иванов", "Иванович"));
    }

    @Test
    void findAllClientsTest() throws Exception {
        when(service.getAll()).thenReturn(example());

        mockMvc.perform(get("/api/v1/client/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Иван"))
                .andExpect(jsonPath("$[0].surname").value("Иванов"))
                .andExpect(jsonPath("$[0].patronymic").value("Иванович"));
    }

    @Test
    void findClientTest() throws Exception {
        when(service.getById(1L)).thenReturn(example().get(0));
        mockMvc.perform(get("/api/v1/client/get/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void deleteClientTest() throws Exception {
        mockMvc.perform(delete("/api/v1/client/delete/1"))
                .andExpect(status().isNoContent());
        verify(service).deleteById(1L);
    }

    @Test
    void updateClientTest() throws Exception {
        ClientDto client = new ClientDto(1L, "Иван", "Иванов", "Иванович");
        when(service.updateById(1L, new ClientDto(1L, "Иван", "Иванов", "Иванович"))).thenReturn(client);

        mockMvc.perform(put("/api/v1/client/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(client)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void registerTest() throws Exception {
        ClientDto client = new ClientDto(1L, "Иван", "Иванов", "Иванович");
        when(service.create("Иван", "Иванов", "Иванович")).thenReturn(client);

        mockMvc.perform(post("/api/v1/client/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(client)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Иван"))
                .andExpect(jsonPath("$.surname").value("Иванов"))
                .andExpect(jsonPath("$.patronymic").value("Иванович"));
    }

    @Test
    void findClientExceptionTest() throws Exception {
        when(service.getById(1L)).thenThrow(new NotFoundException("Клиент с таким айди не найден."));
        mockMvc.perform(get("/api/v1/client/get/1"))
                .andExpect(status().isNotFound());
    }
}
