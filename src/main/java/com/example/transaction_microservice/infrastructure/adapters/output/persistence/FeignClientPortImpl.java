package com.example.transaction_microservice.infrastructure.adapters.output.persistence;

import com.example.transaction_microservice.domain.models.Supply;
import com.example.transaction_microservice.domain.ports.output.IFeignClientPort;
import com.example.transaction_microservice.infrastructure.adapters.input.dto.request.AddStockRequest;
import com.example.transaction_microservice.infrastructure.configuration.feignclient.FeingConstans;
import com.example.transaction_microservice.infrastructure.configuration.feignclient.StockClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;

import java.net.ConnectException;

@RequiredArgsConstructor
public class FeignClientPortImpl implements IFeignClientPort {

    private final StockClient stockClient;

    @Override
    public String addStock(Supply supply) {
        AddStockRequest request = new AddStockRequest(supply.getItemId(), supply.getQuantity());
        try{
            stockClient.updateItem(request);
            return FeingConstans.OK_MESSAGE;
        }
        catch (FeignException e) {
            if(e.getCause() instanceof ConnectException){
                return FeingConstans.CONNECTION_ERROR;
            }
            return FeingConstans.ANOTHER_ERROR;
        }
    }
}
