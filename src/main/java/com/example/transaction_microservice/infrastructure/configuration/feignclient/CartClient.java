package com.example.transaction_microservice.infrastructure.configuration.feignclient;

import com.example.transaction_microservice.domain.utils.BuyResponse;
import com.example.transaction_microservice.domain.utils.Cart;
import com.example.transaction_microservice.infrastructure.configuration.feignclient.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "cart", url="http://localhost:5051/Cart", configuration = FeignConfig.class)
public interface CartClient {

    @PostMapping
    ResponseEntity<Cart> addCart(@RequestParam Long itemId, @RequestParam Integer quantity);

    @PostMapping("/Buy")
    ResponseEntity<BuyResponse> buyCart();

    @DeleteMapping
    ResponseEntity<String> deleteItemInCart(@RequestParam Long itemId);

}

