package com.example.module3.repository;

import com.example.module3.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT c FROM Client c LEFT JOIN FETCH c.code WHERE c.id = :id")
    Optional<Client> findWithCodeById(@Param("id") Long id);
}
