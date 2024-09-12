package com.example.supplies_microservice.infrastructure.adapters.input.controller;

import com.example.supplies_microservice.application.services.SupplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Supplies")
@RequiredArgsConstructor
public class SupplyController {
    private final SupplyService supplyService;

    @PostMapping
    public ResponseEntity<String> createSupply(){
        return ResponseEntity.status(HttpStatus.CREATED).body(supplyService.createSupply());
    }
}
