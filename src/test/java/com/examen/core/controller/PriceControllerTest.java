package com.examen.core.controller;

import com.examen.core.model.Price;
import com.examen.core.service.PriceService;
import com.examen.core.service.impl.PriceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = PriceController.class)
public class PriceControllerTest {

    @Mock
    private PriceServiceImpl priceService;

    @InjectMocks
    private PriceController priceController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(priceController).build();
    }

    @Test
    public void testGetPricesByDateAndProductAndBrand() throws Exception {
        // Mock del resultado del servicio
        Price price1 = new Price(1L, 1L, getDate(2020, 6, 14, 0, 0, 0),
                getDate(2020, 12, 31, 23, 59, 59), 1, 35455L, 0,
                new BigDecimal("35.50"), "EUR");
        Price price2 = new Price(2L, 1L, getDate(2020, 6, 14, 15, 0, 0),
                getDate(2020, 6, 14, 18, 30, 0), 2, 35455L, 1,
                new BigDecimal("25.45"), "EUR");
        List<Price> prices = Arrays.asList(price1, price2);
        given(priceService.getPricesByDateAndProductAndBrand(any(Date.class), any(Date.class), anyLong(), anyLong()))
                .willReturn(prices);

        // Ejecutar la solicitud GET
        mockMvc.perform(get("/api/v1/prices")
                        .param("startDate", "2020-06-14 00:00:00")
                        .param("endDate", "2020-06-14 23:59:59")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.prices").isArray())
                .andExpect(jsonPath("$.prices").value(hasSize(2)))
                .andExpect(jsonPath("$.prices[0].id").value(1))
                .andExpect(jsonPath("$.prices[0].brandId").value(1))
                .andExpect(jsonPath("$.prices[0].startDate").value("2020-06-14T00:00:00"))
                .andExpect(jsonPath("$.prices[0].endDate").value("2020-12-31T23:59:59"))
                .andExpect(jsonPath("$.prices[0].priceList").value(1))
                .andExpect(jsonPath("$.prices[0].productId").value(35455))
                .andExpect(jsonPath("$.prices[0].priority").value(0))
                .andExpect(jsonPath("$.prices[0].price").value("35.50"))
                .andExpect(jsonPath("$.prices[0].currency").value("EUR"))
                .andExpect(jsonPath("$.prices[1].id").value(2))
                .andExpect(jsonPath("$.prices[1].brandId").value(1))
                .andExpect(jsonPath("$.prices[1].startDate").value("2020-06-14T15:00:00"))
                .andExpect(jsonPath("$.prices[1].endDate").value("2020-06-14T18:30:00"))
                .andExpect(jsonPath("$.prices[1].priceList").value(2))
                .andExpect(jsonPath("$.prices[1].productId").value(35455))
                .andExpect(jsonPath("$.prices[1].priority").value(1))
                .andExpect(jsonPath("$.prices[1].price").value("25.45"))
                .andExpect(jsonPath("$.prices[1].currency").value("EUR"));

        // Verificar que el servicio fue llamado con los parámetros correctos
        verify(priceService, times(1)).getPricesByDateAndProductAndBrand(
                getDate(2020, 6, 14, 0, 0, 0),
                getDate(2020, 6, 14, 23, 59, 59),
                35455L,
                1L);
    }

    private Date getDate(int year, int month, int day, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, hour, minute, second);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
