package com.example.transaction_microservice.domain.usecases;

import com.example.transaction_microservice.domain.exceptions.*;
import com.example.transaction_microservice.domain.models.Supply;
import com.example.transaction_microservice.domain.ports.input.ISupplyUseCase;
import com.example.transaction_microservice.domain.ports.output.ISupplyPersistencePort;
import com.example.transaction_microservice.utils.DomainConstants;

public class SupplyUseCaseImpl implements ISupplyUseCase {
    private final ISupplyPersistencePort supplyPersistencePort;

    public SupplyUseCaseImpl(ISupplyPersistencePort supplyPersistencePort) {
        this.supplyPersistencePort = supplyPersistencePort;
    }

    @Override
    public Supply addSupply(Supply supply) {
        if(supply.getQuantity() < 0){
            throw new NotNegativeException(DomainConstants.Field.QUANTITY.toString());
        }
        try{
            return supplyPersistencePort.addSupply(supply);
        }
        catch(InvalidFieldsException e){
            throw new BadRequestException(DomainConstants.INVALID_FIELDS);
        }
        catch(ItemNotFoundInStockException e){
            throw new NotFoundException(DomainConstants.NOT_FOUND);
        }
        catch (StockUpdateException e) {
            throw new SupplyUpdateException(DomainConstants.ERROR_WITH_MICROSERVICE);
        }
    }
}
