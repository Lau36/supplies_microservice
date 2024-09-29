package com.example.transaction_microservice.domain.ports.output;

import com.example.transaction_microservice.domain.utils.BuyResponse;

public interface CartClientPort {
    BuyResponse buy();
}
