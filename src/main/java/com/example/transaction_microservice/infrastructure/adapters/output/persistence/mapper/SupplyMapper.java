package com.example.transaction_microservice.infrastructure.adapters.output.persistence.mapper;


import com.example.transaction_microservice.domain.models.Supply;
import com.example.transaction_microservice.infrastructure.adapters.output.persistence.entity.SupplyEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface SupplyMapper {
    Supply toSupply(SupplyEntity entity);
}
