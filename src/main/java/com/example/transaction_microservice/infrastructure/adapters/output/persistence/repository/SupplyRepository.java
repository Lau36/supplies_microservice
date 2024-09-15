package com.example.transaction_microservice.infrastructure.adapters.output.persistence.repository;

import com.example.transaction_microservice.infrastructure.adapters.output.persistence.entity.SupplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplyRepository extends JpaRepository<SupplyEntity, Long>{
}
