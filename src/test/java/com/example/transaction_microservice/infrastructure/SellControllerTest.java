package com.example.transaction_microservice.infrastructure;

import com.example.transaction_microservice.application.services.SellService;
import com.example.transaction_microservice.domain.models.Sell;
import com.example.transaction_microservice.infrastructure.adapters.input.controller.SellController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class SellControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SellService sellService;

    @InjectMocks
    private SellController sellController;

    private Sell sell;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(sellController).build();
        Long userId = 1L;
        sell = new Sell(1L, userId, new BigDecimal("300.0"), LocalDateTime.now());
    }

    @Test
    void testCreateSell_Success() throws Exception {
        when(sellService.addSell()).thenReturn(sell);

        mockMvc.perform(post("/Sell")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(sell.getId()))
                .andExpect(jsonPath("$.userId").value(sell.getUserId()))
                .andExpect(jsonPath("$.totalPrice").value(sell.getTotalPrice()))
                .andExpect(jsonPath("$.sellDate").exists());
    }
}
