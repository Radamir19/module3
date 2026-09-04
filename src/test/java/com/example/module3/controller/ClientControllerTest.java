package com.example.module3.controller;

import com.example.module3.contoller.ClientController;
import com.example.module3.entity.DTO.ClientDto;
import com.example.module3.exception.NotFoundException;
import com.example.module3.service.ClientService;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
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
        return List.of(new ClientDto(1L, "Иванов Иван Иванович", List.of()));
    }

    @Test
    void findAllClientsTest() throws Exception {
        when(service.getAll()).thenReturn(example());

        mockMvc.perform(get("/api/client/getAllClients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].full_name").value("Иванов Иван Иванович"))
                .andExpect(jsonPath("$[0].codes", hasSize(0)));
    }

    @Test
    void findClientTest() throws Exception {
        when(service.getById(1L)).thenReturn(example().get(0));
        mockMvc.perform(get("/api/client/get/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void deleteClientTest() throws Exception {
        mockMvc.perform(delete("/api/client/delete/1"))
                .andExpect(status().isOk());
        verify(service).deleteById(1L);
    }

    @Test
    void updateClientTest() throws Exception {
        when(service.updateById(eq(1L), anyString())).thenReturn(example().get(0));

        mockMvc.perform(put("/api/client/update/1")
                        .param("fullName", "Петров Пётр Петрович"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void registerTest() throws Exception {
        ClientDto client = new ClientDto(1L, "Иванов Иван Иванович", List.of());
        when(service.create("Иванов Иван Иванович")).thenReturn(client);

        mockMvc.perform(post("/api/client/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(client)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.full_name").value("Иванов Иван Иванович"));
    }

    @Test
    void findClientExceptionTest() throws Exception {
        when(service.getById(1L)).thenThrow(new NotFoundException("Клиент с таким айди не найден."));
        mockMvc.perform(get("/api/client/get/1"))
                .andExpect(status().isNotFound());
    }
}
