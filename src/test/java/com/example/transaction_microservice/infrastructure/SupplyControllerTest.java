package com.example.transaction_microservice.infrastructure;

import com.example.transaction_microservice.application.services.SupplyService;
import com.example.transaction_microservice.domain.models.Supply;
import com.example.transaction_microservice.infrastructure.adapters.input.controller.SupplyController;
import com.example.transaction_microservice.infrastructure.adapters.input.dto.request.AddSupplyRequest;
import com.example.transaction_microservice.infrastructure.adapters.input.dto.response.AddSupplyResponse;
import com.example.transaction_microservice.infrastructure.adapters.input.mapper.AddSupplyMapper;
import com.example.transaction_microservice.utils.DomainConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class SupplyControllerTest{

    @Mock
    AddSupplyMapper addSupplyMapper;

    @Mock
    SupplyService supplyService;

    @InjectMocks
    SupplyController supplyController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(supplyController).build();
    }

    @Test
    void addSupplyTest() throws Exception {
        Supply supply = new Supply(1L, 1L, 1L, 10, LocalDateTime.now(), LocalDateTime.now());
        Supply supplySaved = new Supply(1L, 1L, 1L, 10, LocalDateTime.now(), LocalDateTime.now());
        AddSupplyResponse response = new AddSupplyResponse(DomainConstants.SUPPLY_CREATED, 1L);
        when(addSupplyMapper.addSupplyRequestToSupply(any(AddSupplyRequest.class))).thenReturn(supply);
        when(supplyService.addSupply(any(Supply.class))).thenReturn(supplySaved);
        mockMvc.perform(post("/Supplies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"adminId\": 1, \"itemId\": 1, \"quantity\": 10 }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value(response.getMessage()))
                .andExpect(jsonPath("$.id").value(response.getId()))
                .andDo(print());
    }

    @Test
    void getNextSupplyDate() throws Exception {
        LocalDateTime nextSupplyDate = LocalDateTime.now();
        Supply supply = new Supply(1L, 1L, 1L, 10, LocalDateTime.now(), nextSupplyDate);
        when(supplyService.getNextSupplyDate(anyLong())).thenReturn(supply.getNextSupplyDate());

        mockMvc.perform(get("/Supplies")
                        .param("itemId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nextSupplyDate[0]").value(supply.getNextSupplyDate().getYear()))
                .andExpect(jsonPath("$.nextSupplyDate[1]").value(supply.getNextSupplyDate().getMonthValue()))
                .andExpect(jsonPath("$.nextSupplyDate[2]").value(supply.getNextSupplyDate().getDayOfMonth()))
                .andExpect(jsonPath("$.nextSupplyDate[3]").value(supply.getNextSupplyDate().getHour()))
                .andExpect(jsonPath("$.nextSupplyDate[4]").value(supply.getNextSupplyDate().getMinute()))
                .andExpect(jsonPath("$.nextSupplyDate[5]").value(supply.getNextSupplyDate().getSecond()));
    }

}
