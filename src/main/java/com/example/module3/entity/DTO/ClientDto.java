package com.example.module3.entity.DTO;

import java.util.List;

public record ClientDto(Long id, String full_name, List<QrDto> codes) {
}
