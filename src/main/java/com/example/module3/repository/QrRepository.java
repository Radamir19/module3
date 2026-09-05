package com.example.module3.repository;

import com.example.module3.entity.Qr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface QrRepository extends JpaRepository<Qr, Long> {
    @Query("SELECT qr FROM Qr qr LEFT JOIN FETCH qr.client WHERE qr.code = :code")
    Optional<Qr> findByCode(UUID code);

    @Query("SELECT code FROM Qr code LEFT JOIN FETCH code.client WHERE code.id = :id")
    Optional<Qr> findWithClientById(@Param("id") Long id);
}
