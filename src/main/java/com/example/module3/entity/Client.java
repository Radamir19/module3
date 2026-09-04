package com.example.module3.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Qr> codes = new ArrayList<>();

    public Client() {

    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Qr> getCodes() {
        return codes;
    }

    public Qr addCode() {
        Qr qr = new Qr();
        qr.setClient(this);
        for(Qr code : codes) {
            code.setCode(null);
        }
        codes.add(qr);
        return qr;
    }
}
