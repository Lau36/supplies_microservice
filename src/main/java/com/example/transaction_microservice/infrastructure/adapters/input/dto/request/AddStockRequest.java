package com.example.transaction_microservice.infrastructure.adapters.input.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddStockRequest {
    private Long id;
    private Integer quantity;
}
