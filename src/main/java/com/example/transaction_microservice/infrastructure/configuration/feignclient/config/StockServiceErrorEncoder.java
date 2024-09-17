package com.example.transaction_microservice.infrastructure.configuration.feignclient.config;

import com.example.transaction_microservice.domain.exceptions.SupplyUpdateException;
import com.example.transaction_microservice.infrastructure.configuration.feignclient.FeingConstans;
import com.example.transaction_microservice.utils.DomainConstants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class StockServiceErrorEncoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new ErrorDecoder.Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if(response != null){
            String errorMessage = parseErrorResponse(response);
            return switch (response.status()) {
                case 400 -> new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
                case 404 -> new ResponseStatusException(HttpStatus.NOT_FOUND,  errorMessage);
                case 500 ->
                        new SupplyUpdateException(DomainConstants.ERROR_WITH_MICROSERVICE);
                default -> defaultErrorDecoder.decode(methodKey, response);
            };
        }
        else{
            return new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, DomainConstants.ERROR_WITH_MICROSERVICE);
        }
    }

    private String parseErrorResponse(Response response) {
        try {
            String body = IOUtils.toString(response.body().asInputStream(), StandardCharsets.UTF_8);
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> errorResponse = mapper.readValue(body, new TypeReference<Map<String, Object>>() {});
            return (String) errorResponse.get(FeingConstans.MESSAGE);

        } catch (IOException e) {
            return FeingConstans.ERROR;
        }
    }

}
