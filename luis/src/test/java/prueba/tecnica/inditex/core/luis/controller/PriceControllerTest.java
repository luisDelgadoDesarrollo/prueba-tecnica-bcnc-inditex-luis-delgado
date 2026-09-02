package prueba.tecnica.inditex.core.luis.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import prueba.tecnica.inditex.core.luis.controller.dto.PriceDto;
import prueba.tecnica.inditex.core.luis.controller.mapper.PriceControllerMapper;
import prueba.tecnica.inditex.core.luis.domain.model.Price;
import prueba.tecnica.inditex.core.luis.domain.service.PriceService;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PriceController.class)
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PriceService priceService;

    @MockitoBean
    private PriceControllerMapper priceControllerMapper;

    @Test
    void getPrices_ShouldReturnPriceDto_WhenValidRequest() throws Exception {
        Price domainPrice = Price.builder().price(new BigDecimal("35.50")).build();
        PriceDto expectedDto = new PriceDto(35455L, 1L, 1, null, null, new BigDecimal("35.50"), "EUR");

        when(priceService.getPrice(any(), any(), any())).thenReturn(domainPrice);
        when(priceControllerMapper.toPriceDto(domainPrice)).thenReturn(expectedDto);

        mockMvc.perform(get("/brands/{brandId}/products/{productId}/prices", 1, 35455)
                        .param("date", "2020-06-14T10:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.50))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }
    
    @Test
    void getPrices_ShouldReturnBadRequest_WhenParameterIsNegative() throws Exception {
        try {
            mockMvc.perform(get("/brands/{brandId}/products/{productId}/prices", -1, 35455)
                            .param("date", "2020-06-14T10:00:00"));
        } catch (Exception e) {
            assert e.getCause() instanceof jakarta.validation.ConstraintViolationException;
        }
    }
}
