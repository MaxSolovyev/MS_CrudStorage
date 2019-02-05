package com.app.crud.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

@Entity
@Table(name="document")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public long Id;
    @Column(name="name")
    public String name;

    public Document() {
    }

    public Document(String name) {
        this.name = name;
    }
}
