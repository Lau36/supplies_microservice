package com.example.transaction_microservice.infrastructure.adapters.output.persistence.repository;

import com.example.transaction_microservice.infrastructure.adapters.output.persistence.entity.SupplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupplyRepository extends JpaRepository<SupplyEntity, Long>{
    Optional<SupplyEntity> findFirstByItemIdOrderBySupplyDateDesc(Long idItem);
}
