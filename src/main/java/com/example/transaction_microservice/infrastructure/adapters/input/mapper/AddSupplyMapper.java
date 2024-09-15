package com.example.transaction_microservice.infrastructure.adapters.input.mapper;

import com.example.transaction_microservice.domain.models.Supply;
import com.example.transaction_microservice.infrastructure.adapters.input.dto.request.AddSupplyRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddSupplyMapper {
    Supply addSupplyRequestToSupply(AddSupplyRequest addSupplyRequest);
}
