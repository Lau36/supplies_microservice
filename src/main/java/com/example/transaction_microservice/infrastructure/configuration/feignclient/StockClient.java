package com.example.transaction_microservice.infrastructure.configuration.feignclient;

import com.example.transaction_microservice.domain.utils.AddStockRequest;
import com.example.transaction_microservice.domain.utils.SubtractStockRequest;
import com.example.transaction_microservice.infrastructure.adapters.input.dto.response.AddStockResponse;
import com.example.transaction_microservice.infrastructure.configuration.feignclient.config.FeignConfig;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "stock", url="http://localhost:9090/Item", configuration = FeignConfig.class)
public interface StockClient {
    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AddStockResponse> updateItem(@RequestBody AddStockRequest addStockRequest);

    @GetMapping(value = "/InStock", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Boolean> isInStock(@RequestParam int itemId, @RequestParam int quantity);

    @PostMapping("/SubtractStock")
    ResponseEntity<String> subtractStock(@RequestBody SubtractStockRequest request);
}
