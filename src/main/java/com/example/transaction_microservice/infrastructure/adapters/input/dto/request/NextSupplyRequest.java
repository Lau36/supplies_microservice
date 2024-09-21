package com.example.transaction_microservice.infrastructure.adapters.input.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NextSupplyRequest {
    private Long itemId;
}
