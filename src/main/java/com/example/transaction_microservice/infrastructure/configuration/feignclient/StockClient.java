package com.example.transaction_microservice.infrastructure.configuration.feignclient;

import com.example.transaction_microservice.infrastructure.adapters.input.dto.request.AddStockRequest;
import com.example.transaction_microservice.infrastructure.adapters.input.dto.response.AddStockResponse;
import com.example.transaction_microservice.infrastructure.configuration.feignclient.config.FeignConfig;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "stock", url="http://localhost:9090/Item", configuration = FeignConfig.class)
public interface StockClient {
    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AddStockResponse> updateItem(@RequestBody AddStockRequest addStockRequest);
}
