package com.example.transaction_microservice.infrastructure.configuration;

import com.example.transaction_microservice.application.services.SupplyServiceImpl;
import com.example.transaction_microservice.application.services.SupplyService;
import com.example.transaction_microservice.domain.ports.input.ISupplyUseCase;
import com.example.transaction_microservice.domain.ports.output.IFeignClientPort;
import com.example.transaction_microservice.domain.ports.output.ISupplyPersistencePort;
import com.example.transaction_microservice.domain.usecases.SupplyUseCaseImpl;
import com.example.transaction_microservice.infrastructure.adapters.output.persistence.FeignClientPortImpl;
import com.example.transaction_microservice.infrastructure.adapters.output.persistence.SupplyPersistenceAdapter;
import com.example.transaction_microservice.infrastructure.adapters.output.persistence.mapper.SupplyMapper;
import com.example.transaction_microservice.infrastructure.adapters.output.persistence.repository.SupplyRepository;
import com.example.transaction_microservice.infrastructure.configuration.feignclient.StockClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {


    @Bean
    public ISupplyPersistencePort supplyPersistencePort(final SupplyRepository supplyRepository, final SupplyMapper supplyMapper) {
        return new SupplyPersistenceAdapter(supplyRepository, supplyMapper);
    }

    @Bean
    public IFeignClientPort feignClientPort(final StockClient stockClient) {
        return new FeignClientPortImpl(stockClient);
    }

    @Bean
    public SupplyService supplyService(final ISupplyUseCase supplyUseCase){
        return new SupplyServiceImpl(supplyUseCase);
    }

    @Bean
    public ISupplyUseCase supplyUseCase(final ISupplyPersistencePort supplyPersistencePort, final IFeignClientPort feignClientPort) {
        return new SupplyUseCaseImpl(supplyPersistencePort, feignClientPort);
    }

}
