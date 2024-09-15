package com.example.transaction_microservice.infrastructure.configuration;

import com.example.transaction_microservice.application.services.SupplyService;
import com.example.transaction_microservice.domain.ports.output.ISupplyPersistencePort;
import com.example.transaction_microservice.domain.usecases.SupplyUseCaseImpl;
import com.example.transaction_microservice.infrastructure.adapters.output.persistence.SupplyPersistenceAdapter;
import com.example.transaction_microservice.infrastructure.adapters.output.persistence.mapper.SupplyMapper;
import com.example.transaction_microservice.infrastructure.adapters.output.persistence.repository.SupplyRepository;
import com.example.transaction_microservice.infrastructure.configuration.feignclient.StockClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public SupplyService supplyService(final ISupplyPersistencePort supplyPersistencePort) {
        return new SupplyService(new SupplyUseCaseImpl(supplyPersistencePort));
    }

    @Bean
    public ISupplyPersistencePort supplyPersistencePort(final SupplyRepository supplyRepository, final StockClient stockClient, final SupplyMapper supplyMapper) {
        return new SupplyPersistenceAdapter(supplyRepository, stockClient, supplyMapper);
    }
}
