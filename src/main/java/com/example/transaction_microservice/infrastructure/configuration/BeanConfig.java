package com.example.transaction_microservice.infrastructure.configuration;

import com.example.transaction_microservice.application.services.SellService;
import com.example.transaction_microservice.application.services.SellServiceImpl;
import com.example.transaction_microservice.application.services.SupplyServiceImpl;
import com.example.transaction_microservice.application.services.SupplyService;
import com.example.transaction_microservice.domain.ports.input.ISellUseCase;
import com.example.transaction_microservice.domain.ports.input.ISupplyUseCase;
import com.example.transaction_microservice.domain.ports.output.*;
import com.example.transaction_microservice.domain.usecases.SellUseCaseImpl;
import com.example.transaction_microservice.domain.usecases.SupplyUseCaseImpl;
import com.example.transaction_microservice.infrastructure.adapters.output.persistence.*;
import com.example.transaction_microservice.infrastructure.adapters.output.persistence.mapper.SupplyMapper;
import com.example.transaction_microservice.infrastructure.adapters.output.persistence.repository.SellRepository;
import com.example.transaction_microservice.infrastructure.adapters.output.persistence.repository.SupplyRepository;
import com.example.transaction_microservice.infrastructure.configuration.feignclient.CartClient;
import com.example.transaction_microservice.infrastructure.configuration.feignclient.ReportClient;
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
    public StockClientPort feignClientPort(final StockClient stockClient) {
        return new StockClientPortImpl(stockClient);
    }

    @Bean
    public SupplyService supplyService(final ISupplyUseCase supplyUseCase){
        return new SupplyServiceImpl(supplyUseCase);
    }

    @Bean
    public ISupplyUseCase supplyUseCase(final ISupplyPersistencePort supplyPersistencePort, final StockClientPort feignClientPort) {
        return new SupplyUseCaseImpl(supplyPersistencePort, feignClientPort);
    }

    @Bean
    public ISellPersistencePort sellPersistencePort(final SellRepository sellRepository) {
        return new SellPersistenceAdapter(sellRepository);
    }

    @Bean
    public SellService sellService(final ISellUseCase sellUseCase) {
        return new SellServiceImpl(sellUseCase);
    }

    @Bean
    public ISellUseCase sellUseCase(final ISellPersistencePort sellPersistencePort,
                                    final CartClientPort cartClientPort,
                                    final StockClientPort stockClientPort,
                                    final ReportClientPort reportClientPort,
                                    final IUserPort userPort
    ) {
        return new SellUseCaseImpl(sellPersistencePort, cartClientPort, stockClientPort, reportClientPort, userPort);
    }

    @Bean
    public ReportClientPort reportClientPort(final ReportClient reportClient) {
        return new ReportClientPortImpl(reportClient);
    }

    @Bean
    public CartClientPort cartClientPort(final CartClient cartClient) {
        return new CartClientPortImpl(cartClient);
    }

    @Bean
    public IUserPort userPort() {
       return new UserPortImpl();
    }


}
