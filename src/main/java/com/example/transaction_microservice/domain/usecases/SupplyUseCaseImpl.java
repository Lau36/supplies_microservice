package com.example.transaction_microservice.domain.usecases;

import com.example.transaction_microservice.domain.exceptions.*;
import com.example.transaction_microservice.domain.models.Supply;
import com.example.transaction_microservice.domain.ports.input.ISupplyUseCase;
import com.example.transaction_microservice.domain.ports.output.IFeignClientPort;
import com.example.transaction_microservice.domain.ports.output.ISupplyPersistencePort;
import com.example.transaction_microservice.domain.utils.DomainConstans;
import com.example.transaction_microservice.utils.DomainConstants;

public class SupplyUseCaseImpl implements ISupplyUseCase {
    private final ISupplyPersistencePort supplyPersistencePort;
    private final IFeignClientPort feignClientPort;

    public SupplyUseCaseImpl(ISupplyPersistencePort supplyPersistencePort, IFeignClientPort feignClientPort) {
        this.supplyPersistencePort = supplyPersistencePort;
        this.feignClientPort = feignClientPort;
    }

    @Override
    public Supply addSupply(Supply supply) {
        if(supply.getQuantity() <  0){
            throw new NotNegativeException(DomainConstants.Field.QUANTITY.toString());
        }
        if(feignClientPort.addStock(supply).equals(DomainConstans.CONNECTION_ERROR)){
            throw new ConnectionErrorException(DomainConstants.ERROR_WITH_MICROSERVICE);
        }
        if(feignClientPort.addStock(supply).equals(DomainConstans.OK_MESSAGE)){
            return supplyPersistencePort.addSupply(supply);
        }
        throw new SupplyUpdateException(DomainConstants.ERROR_WITH_STOCK);
    }


}
