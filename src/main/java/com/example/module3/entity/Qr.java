package com.example.module3.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "qr")
public class Qr {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "id")
    private Client client;

    @Column(name = "qr_code", unique = true)
    private UUID code = UUID.randomUUID();

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
    public void regenerate() {
        this.code = UUID.randomUUID();
    }
}