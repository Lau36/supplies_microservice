package com.example.transaction_microservice.infrastructure.configuration.feignclient;

import com.example.transaction_microservice.infrastructure.configuration.feignclient.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "report", url="http://localhost:6060/Report", configuration = FeignConfig.class)
public interface ReportClient {
}
