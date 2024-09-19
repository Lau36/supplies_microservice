package com.example.transaction_microservice.infrastructure.adapters.output.persistence;

import com.example.transaction_microservice.domain.models.Supply;
import com.example.transaction_microservice.domain.ports.output.ISupplyPersistencePort;
import com.example.transaction_microservice.infrastructure.adapters.output.persistence.entity.SupplyEntity;
import com.example.transaction_microservice.infrastructure.adapters.output.persistence.mapper.SupplyMapper;
import com.example.transaction_microservice.infrastructure.adapters.output.persistence.repository.SupplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.time.LocalDate;

@RequiredArgsConstructor
public class SupplyPersistenceAdapter implements ISupplyPersistencePort {

    private final SupplyRepository supplyRepository;
    private final SupplyMapper supplyMapper;

    @Override
    public Supply addSupply(Supply supply) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = auth.getDetails().toString();
        LocalDate nextSupplyDate = LocalDate.now().plusDays(30);
        SupplyEntity supplyEntity = new SupplyEntity(null, Long.valueOf(userId), supply.getItemId(),supply.getQuantity(), LocalDate.now(), nextSupplyDate);
        SupplyEntity supplySaved = supplyRepository.save(supplyEntity);
        return supplyMapper.toSupply(supplySaved);
    }
}
