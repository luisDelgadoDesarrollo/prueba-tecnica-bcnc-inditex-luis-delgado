package prueba.tecnica.inditex.core.luis.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GetPriceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test 1: Petición a las 10:00 del día 14 para producto 35455 y marca 1 (ZARA)")
    void test1_request_at_10_00_on_day_14() throws Exception {
        mockMvc.perform(get("/prices")
                        .param("date", "2020-06-14T10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.price").value(35.50))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    @DisplayName("Test 2: Petición a las 16:00 del día 14 para producto 35455 y marca 1 (ZARA)")
    void test2_request_at_16_00_on_day_14() throws Exception {
        mockMvc.perform(get("/prices")
                        .param("date", "2020-06-14T16:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(2))
                .andExpect(jsonPath("$.price").value(25.45))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    @DisplayName("Test 3: Petición a las 21:00 del día 14 para producto 35455 y marca 1 (ZARA)")
    void test3_request_at_21_00_on_day_14() throws Exception {
        mockMvc.perform(get("/prices")
                        .param("date", "2020-06-14T21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.price").value(35.50))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    @DisplayName("Test 4: Petición a las 10:00 del día 15 para producto 35455 y marca 1 (ZARA)")
    void test4_request_at_10_00_on_day_15() throws Exception {
        mockMvc.perform(get("/prices")
                        .param("date", "2020-06-15T10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(3))
                .andExpect(jsonPath("$.price").value(30.50))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    @DisplayName("Test 5: Petición a las 21:00 del día 16 para producto 35455 y marca 1 (ZARA)")
    void test5_request_at_21_00_on_day_16() throws Exception {
        mockMvc.perform(get("/prices")
                        .param("date", "2020-06-16T21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(4))
                .andExpect(jsonPath("$.price").value(38.95))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    @DisplayName("Test 6: Petición con producto inexistente debe retornar 404 Not Found")
    void test6_request_with_non_existent_product_returns_404() throws Exception {
        mockMvc.perform(get("/prices")
                        .param("date", "2020-06-14T10:00:00")
                        .param("productId", "99999")
                        .param("brandId", "1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Not Found"))
                .andExpect(jsonPath("$.message").value("Precio no encontrado para el producto: 99999 y la marca: 1"));
    }

    @Test
    @DisplayName("Test 7: Petición con marca inexistente debe retornar 404 Not Found")
    void test7_request_with_non_existent_brand_returns_404() throws Exception {
        mockMvc.perform(get("/prices")
                        .param("date", "2020-06-14T10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Not Found"))
                .andExpect(jsonPath("$.message").value("Precio no encontrado para el producto: 35455 y la marca: 99"));
    }

    @Test
    @DisplayName("Test 8: Petición con fecha fuera de rango debe retornar 404 Not Found")
    void test8_request_with_date_out_of_range_returns_404() throws Exception {
        mockMvc.perform(get("/prices")
                        .param("date", "2019-01-01T10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Not Found"));
    }
}
