package com.example.module3.repository;

import com.example.module3.entity.Client;
import com.example.module3.entity.DTO.ClientDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByFullName(String fullName);
}
