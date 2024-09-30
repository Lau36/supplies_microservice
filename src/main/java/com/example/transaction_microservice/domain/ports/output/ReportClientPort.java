package com.example.transaction_microservice.domain.ports.output;

import com.example.transaction_microservice.domain.utils.Shop;

public interface ReportClientPort {
    String addShop(Shop shop);
}
