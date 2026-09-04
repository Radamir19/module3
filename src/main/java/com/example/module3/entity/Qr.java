package com.example.module3.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

import java.util.UUID;

@Entity
public class Qr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(name = "qr_code", unique = true, nullable = false, insertable = false)
    @Generated(event = EventType.INSERT)
    private UUID code;


    public Long getId() {
        return id;
    }
    public UUID getCode() {
        return code;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }

    public void setCode(UUID qr) {
        this.code = qr;
    }
}
