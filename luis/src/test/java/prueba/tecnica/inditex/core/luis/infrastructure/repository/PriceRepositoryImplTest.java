package prueba.tecnica.inditex.core.luis.infrastructure.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import prueba.tecnica.inditex.core.luis.domain.exception.PriceNotFoundException;
import prueba.tecnica.inditex.core.luis.domain.model.Price;
import prueba.tecnica.inditex.core.luis.infrastructure.entity.PriceEntity;
import prueba.tecnica.inditex.core.luis.infrastructure.jpa.PriceEntityJpa;
import prueba.tecnica.inditex.core.luis.infrastructure.mapper.PriceRepositoryMapper;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceRepositoryImplTest {

    @Mock
    private PriceEntityJpa priceEntityJpa;

    @Mock
    private PriceRepositoryMapper priceMapper;

    @InjectMocks
    private PriceRepositoryImpl priceRepository;

    @Test
    void getPrice_ShouldReturnPrice_WhenEntityIsFound() {
        LocalDateTime date = LocalDateTime.parse("2020-06-14T10:00:00");
        Long productId = 35455L;
        Long brandId = 1L;

        PriceEntity entity = new PriceEntity();
        Price expectedPrice = new Price();

        when(priceEntityJpa.findPrice(date, productId, brandId)).thenReturn(Optional.of(entity));
        when(priceMapper.toPrice(entity)).thenReturn(expectedPrice);

        Price result = priceRepository.getPrice(date, productId, brandId);

        assertEquals(expectedPrice, result);
        verify(priceEntityJpa).findPrice(date, productId, brandId);
        verify(priceMapper).toPrice(entity);
    }

    @Test
    void getPrice_ShouldThrowPriceNotFoundException_WhenEntityIsNotFound() {
        LocalDateTime date = LocalDateTime.parse("2020-06-14T10:00:00");
        Long productId = 35455L;
        Long brandId = 1L;

        when(priceEntityJpa.findPrice(date, productId, brandId)).thenReturn(Optional.empty());

        assertThrows(PriceNotFoundException.class, () -> 
                priceRepository.getPrice(date, productId, brandId));
        
        verify(priceEntityJpa).findPrice(date, productId, brandId);
    }
}
