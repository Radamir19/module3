package com.example.module3.entity;

import jakarta.persistence.*;
import jakarta.websocket.ClientEndpoint;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "surname", nullable = false)
    private String surname;
    @Column(name = "patronymic")
    private String patronymic;

    @OneToOne(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Qr code;

    public Client() {

    }

    public Long getId() {
        return id;
    }


    public Qr getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getFullName() {
        return name + " " + surname + " " + patronymic;
    }

    public Qr changeCode() {
        this.code = null;
        Qr qr = new Qr();
        qr.setClient(this);
        this.code = qr;
        return code;
    }

}
