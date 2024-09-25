package com.example.transaction_microservice.infrastructure.adapters.input.controller;

import com.example.transaction_microservice.application.services.SupplyService;
import com.example.transaction_microservice.domain.models.Supply;
import com.example.transaction_microservice.infrastructure.adapters.input.dto.request.AddSupplyRequest;
import com.example.transaction_microservice.infrastructure.adapters.input.dto.response.AddSupplyResponse;
import com.example.transaction_microservice.infrastructure.adapters.input.dto.response.ResponseNextSupplyDate;
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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/Supplies")
@RequiredArgsConstructor
public class SupplyController {
    private final SupplyService supplyService;
    private final AddSupplyMapper addSupplyMapper;

    @Operation(summary = SwaggerConstants.ADD_SUPPLY,
            description = SwaggerConstants.ADD_SUPPLY_DESCRIPTION,
            security = @SecurityRequirement(name = SwaggerConstants.BEARER))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = SwaggerConstants.ADDED_SUPPLY),
            @ApiResponse(responseCode = "400", description = SwaggerConstants.BAD_REQUEST)
    })
    @PostMapping
    public ResponseEntity<AddSupplyResponse> createSupply(@RequestBody AddSupplyRequest addSupplyRequest) {
        Supply supply = addSupplyMapper.addSupplyRequestToSupply(addSupplyRequest);
        Supply supplySaved = supplyService.addSupply(supply);
        return ResponseEntity.status(HttpStatus.CREATED).body(new AddSupplyResponse(DomainConstants.SUPPLY_CREATED,supplySaved.getItemId()));
    }

    @Operation(summary = SwaggerConstants.GET_NEXT_SUPPLY_DATE,
            description = SwaggerConstants.GET_NEXT_SUPPLY_DATE_DESCRIPTION,
            security = @SecurityRequirement(name = SwaggerConstants.BEARER))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = SwaggerConstants.OK),
            @ApiResponse(responseCode = "404", description = SwaggerConstants.NOT_FOUND_MESSAGE)
    })
    @GetMapping
    public ResponseEntity<ResponseNextSupplyDate> getNextSupply(@RequestParam int itemId ) {
        LocalDateTime date = supplyService.getNextSupplyDate((long) itemId);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseNextSupplyDate(date));

    }
}
