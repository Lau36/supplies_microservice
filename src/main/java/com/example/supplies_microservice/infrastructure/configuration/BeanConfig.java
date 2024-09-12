package com.example.supplies_microservice.infrastructure.configuration;

import com.example.supplies_microservice.application.services.SupplyService;
import com.example.supplies_microservice.domain.ports.output.ISupplyPersistencePort;
import com.example.supplies_microservice.domain.usecases.SupplyUseCaseImpl;
import com.example.supplies_microservice.infrastructure.adapters.output.persistence.SupplyPersistenceAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public SupplyService supplyService(final ISupplyPersistencePort supplyPersistencePort) {
        return new SupplyService(new SupplyUseCaseImpl(supplyPersistencePort));
    }

    @Bean
    public ISupplyPersistencePort supplyPersistencePort() {
        return new SupplyPersistenceAdapter();
    }
}
