package com.example.transaction_microservice.infrastructure.adapters.output.persistence;

import com.example.transaction_microservice.domain.ports.output.ReportClientPort;
import com.example.transaction_microservice.domain.utils.Shop;
import com.example.transaction_microservice.infrastructure.configuration.feignclient.ReportClient;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReportClientPortImpl implements ReportClientPort {
    private final ReportClient reportClient;

    @Override
    public String addShop(Shop shop) {
        return reportClient.createSell(shop).getBody();
    }
}
