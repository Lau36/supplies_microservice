package com.example.transaction_microservice.infrastructure.adapters.input.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddSupplyRequest {
    private Long itemId;
    private Integer quantity;

}
