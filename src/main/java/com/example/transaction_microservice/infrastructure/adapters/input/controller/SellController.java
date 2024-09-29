package com.example.transaction_microservice.infrastructure.adapters.input.controller;

import com.example.transaction_microservice.application.services.SellService;
import com.example.transaction_microservice.application.services.SupplyService;
import com.example.transaction_microservice.domain.models.Sell;
import com.example.transaction_microservice.domain.models.Supply;
import com.example.transaction_microservice.domain.utils.BuyResponse;
import com.example.transaction_microservice.infrastructure.adapters.input.dto.request.AddSupplyRequest;
import com.example.transaction_microservice.infrastructure.adapters.input.dto.response.AddSupplyResponse;
import com.example.transaction_microservice.infrastructure.adapters.input.mapper.AddSupplyMapper;
import com.example.transaction_microservice.infrastructure.adapters.input.utils.SwaggerConstants;
import com.example.transaction_microservice.utils.DomainConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Sell")
@RequiredArgsConstructor
public class SellController {
    private final SellService sellService;
    private final SupplyService supplyService;

    //    @Operation(summary = SwaggerConstants.ADD_SUPPLY,
//            description = SwaggerConstants.ADD_SUPPLY_DESCRIPTION,
//            security = @SecurityRequirement(name = SwaggerConstants.BEARER))
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201", description = SwaggerConstants.ADDED_SUPPLY),
//            @ApiResponse(responseCode = "400", description = SwaggerConstants.BAD_REQUEST)
//    })
    @PostMapping
    public ResponseEntity<Sell> createSell() {
        return ResponseEntity.status(HttpStatus.OK).body(sellService.addSell());
    }
}
