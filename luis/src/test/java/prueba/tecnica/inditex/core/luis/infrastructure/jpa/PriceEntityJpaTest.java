package prueba.tecnica.inditex.core.luis.infrastructure.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import prueba.tecnica.inditex.core.luis.infrastructure.entity.PriceEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class PriceEntityJpaTest {

    @Autowired
    private PriceEntityJpa priceEntityJpa;

    @Test
    void findPrice_ShouldReturnHighestPriorityPrice_WhenMultipleMatch() {
        LocalDateTime requestDate = LocalDateTime.parse("2020-06-14T16:00:00");
        Long productId = 35455L;
        Long brandId = 1L;

        PriceEntity price1 = PriceEntity.builder()
                .brandId(brandId)
                .productId(productId)
                .startDate(LocalDateTime.parse("2020-06-14T00:00:00"))
                .endDate(LocalDateTime.parse("2020-12-31T23:59:59"))
                .priceList(1)
                .priority(0)
                .price(new BigDecimal("35.50"))
                .currency("EUR")
                .build();

        PriceEntity price2 = PriceEntity.builder()
                .brandId(brandId)
                .productId(productId)
                .startDate(LocalDateTime.parse("2020-06-14T15:00:00"))
                .endDate(LocalDateTime.parse("2020-06-14T18:30:00"))
                .priceList(2)
                .priority(1)
                .price(new BigDecimal("25.45"))
                .currency("EUR")
                .build();

        priceEntityJpa.save(price1);
        priceEntityJpa.save(price2);

        Optional<PriceEntity> result = priceEntityJpa.findPrice(requestDate, productId, brandId);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getPriority());
        assertEquals(new BigDecimal("25.45"), result.get().getPrice());
    }
    
    @Test
    void findPrice_ShouldReturnEmpty_WhenNoDatesMatch() {
        LocalDateTime requestDate = LocalDateTime.parse("2021-01-01T10:00:00");
        Long productId = 35455L;
        Long brandId = 1L;

        PriceEntity price = PriceEntity.builder()
                .brandId(brandId)
                .productId(productId)
                .startDate(LocalDateTime.parse("2020-06-14T00:00:00"))
                .endDate(LocalDateTime.parse("2020-12-31T23:59:59"))
                .priceList(1)
                .priority(0)
                .price(new BigDecimal("35.50"))
                .currency("EUR")
                .build();

        priceEntityJpa.save(price);

        Optional<PriceEntity> result = priceEntityJpa.findPrice(requestDate, productId, brandId);

        assertTrue(result.isEmpty());
    }
}