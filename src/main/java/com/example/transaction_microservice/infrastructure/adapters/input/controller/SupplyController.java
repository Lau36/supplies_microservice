package com.example.transaction_microservice.infrastructure.adapters.input.controller;

import com.example.transaction_microservice.application.services.SupplyService;
import com.example.transaction_microservice.domain.models.Supply;
import com.example.transaction_microservice.infrastructure.adapters.input.dto.request.AddSupplyRequest;
import com.example.transaction_microservice.infrastructure.adapters.input.dto.response.AddSupplyResponse;
import com.example.transaction_microservice.infrastructure.adapters.input.mapper.AddSupplyMapper;
import com.example.transaction_microservice.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Supplies")
@RequiredArgsConstructor
public class SupplyController {
    private final SupplyService supplyService;
    private final AddSupplyMapper addSupplyMapper;

    @PostMapping
    public ResponseEntity<AddSupplyResponse> createSupply(@RequestBody AddSupplyRequest addSupplyRequest) {
        Supply supply = addSupplyMapper.addSupplyRequestToSupply(addSupplyRequest);
        Supply supplySaved = supplyService.addSupply(supply);
        return ResponseEntity.status(HttpStatus.CREATED).body(new AddSupplyResponse(Constants.SUPPLY_CREATED,supplySaved.getItemId()));
    }
}
