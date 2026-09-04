package com.example.module3.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

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

    @Column(name = "qr_code", unique = true, nullable = false, insertable = false, updatable = false)
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
}