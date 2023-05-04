package com.example.kkproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "urltable")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "originalUrl")
    private String originalUrl;

    @Column(name = "shortedUrl")
    private String shortedUrl;

    @Column(name = "created")
    private LocalDateTime created;

}
