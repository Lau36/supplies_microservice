package com.example.transaction_microservice.infrastructure.adapters.input.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddSupplyResponse {
    private String message;
    private Long id;
}
