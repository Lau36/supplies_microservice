package com.example.transaction_microservice.application.services;

import com.example.transaction_microservice.domain.models.Supply;

public interface SupplyService {
    Supply addSupply(Supply supply);
}
