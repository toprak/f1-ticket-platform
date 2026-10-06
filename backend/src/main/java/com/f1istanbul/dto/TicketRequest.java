package com.f1istanbul.dto;

import lombok.Data;

@Data
public class TicketRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String grandstand;
}
