package com.twoxplusone.rshardware.rs_hardware_glass_and_electrical.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse {
    private String message;
    private boolean success;
}

