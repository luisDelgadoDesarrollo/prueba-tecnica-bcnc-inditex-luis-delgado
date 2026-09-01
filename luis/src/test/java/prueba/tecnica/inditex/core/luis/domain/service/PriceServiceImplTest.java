package prueba.tecnica.inditex.core.luis.domain.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import prueba.tecnica.inditex.core.luis.domain.model.Price;
import prueba.tecnica.inditex.core.luis.domain.repository.PriceRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceServiceImplTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceServiceImpl priceService;

    @Test
    void getPrice_ShouldReturnPrice_WhenCalled() {
        LocalDateTime date = LocalDateTime.parse("2020-06-14T10:00:00");
        Long productId = 35455L;
        Long brandId = 1L;

        Price expectedPrice = Price.builder()
                .brandId(brandId)
                .productId(productId)
                .price(new BigDecimal("35.50"))
                .currency("EUR")
                .build();

        when(priceRepository.getPrice(date, productId, brandId)).thenReturn(expectedPrice);

        Price result = priceService.getPrice(date, productId, brandId);

        assertEquals(expectedPrice, result);
        verify(priceRepository).getPrice(date, productId, brandId);
    }
}
