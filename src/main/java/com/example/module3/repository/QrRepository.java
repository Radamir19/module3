package com.example.module3.repository;

import com.example.module3.entity.Qr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface QrRepository extends JpaRepository<Qr, Long> {
    Optional<Qr> findByCode(UUID code);
}
