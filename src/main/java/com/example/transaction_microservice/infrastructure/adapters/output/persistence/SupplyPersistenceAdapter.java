package com.example.transaction_microservice.infrastructure.adapters.output.persistence;

import com.example.transaction_microservice.domain.exceptions.ItemNotFoundInStockException;
import com.example.transaction_microservice.domain.exceptions.StockUpdateException;
import com.example.transaction_microservice.domain.models.Supply;
import com.example.transaction_microservice.domain.ports.output.ISupplyPersistencePort;
import com.example.transaction_microservice.infrastructure.adapters.input.dto.request.AddStockRequest;
import com.example.transaction_microservice.infrastructure.adapters.output.persistence.entity.SupplyEntity;
import com.example.transaction_microservice.infrastructure.adapters.output.persistence.mapper.SupplyMapper;
import com.example.transaction_microservice.infrastructure.adapters.output.persistence.repository.SupplyRepository;
import com.example.transaction_microservice.infrastructure.configuration.feignclient.StockClient;
import com.example.transaction_microservice.utils.Constants;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;

@RequiredArgsConstructor
public class SupplyPersistenceAdapter implements ISupplyPersistencePort {

    private final SupplyRepository supplyRepository;
    private final StockClient stockClient;
    private final SupplyMapper supplyMapper;

    @Override
    public Supply addSupply(Supply supply) {
        AddStockRequest request = new AddStockRequest(supply.getItemId(), supply.getQuantity());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = auth.getDetails().toString();
        SupplyEntity supplyEntity = new SupplyEntity(null, Long.valueOf(userId), supply.getItemId(),supply.getQuantity(), LocalDate.now());
        try{
            stockClient.updateItem(request);
            SupplyEntity supplySaved = supplyRepository.save(supplyEntity);
            return supplyMapper.toSupply(supplySaved);
        }
        catch (FeignException.NotFound e) {
            throw new ItemNotFoundInStockException(Constants.NOT_FOUND);
        }
        catch (FeignException e){
            throw new StockUpdateException(Constants.ERROR_WITH_MICROSERVICE);
        }
    }
}
