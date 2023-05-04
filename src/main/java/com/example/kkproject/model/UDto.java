package com.example.kkproject.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UDto {

    private int id;
    private String originalUrl;
    private String shortedUrl;
    private LocalDateTime created;
}
