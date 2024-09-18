package com.example.transaction_microservice.infrastructure.configuration.feignclient.config;

import feign.Client;
import feign.codec.ErrorDecoder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import feign.httpclient.ApacheHttpClient;

@Configuration
public class FeignConfig {
    @Bean
    public Client feignClient() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        return new ApacheHttpClient(httpClient);
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new StockServiceErrorEncoder();
    }
}
