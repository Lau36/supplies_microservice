package com.example.transaction_microservice.infrastructure.configuration.feignclient;

import com.example.transaction_microservice.domain.utils.Shop;
import com.example.transaction_microservice.infrastructure.configuration.feignclient.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "report", url="http://localhost:8081/Shop", configuration = FeignConfig.class)
public interface ReportClient {

    @PostMapping
    ResponseEntity<String> createSell(@RequestBody Shop shop);

}
