package com.example.transaction_microservice.domain.usecases;

import com.example.transaction_microservice.domain.exceptions.*;
import com.example.transaction_microservice.domain.models.Supply;
import com.example.transaction_microservice.domain.ports.input.ISupplyUseCase;
import com.example.transaction_microservice.domain.ports.output.ISupplyPersistencePort;
import com.example.transaction_microservice.utils.Constants;

public class SupplyUseCaseImpl implements ISupplyUseCase {
    private final ISupplyPersistencePort supplyPersistencePort;

    public SupplyUseCaseImpl(ISupplyPersistencePort supplyPersistencePort) {
        this.supplyPersistencePort = supplyPersistencePort;
    }

    @Override
    public Supply addSupply(Supply supply) {
        if(supply.getQuantity() < 0){
            throw new NotNegativeException(Constants.Field.QUANTITY.toString());
        }
        try{
            return supplyPersistencePort.addSupply(supply);
        }
        catch(ItemNotFoundInStockException e){
            throw new NotFoundException(Constants.NOT_FOUND);
        }
        catch (StockUpdateException e) {
            throw new SupplyUpdateException(Constants.ERROR_WITH_MICROSERVICE);
        }
    }
}
