package prueba.tecnica.inditex.core.luis.infrastructure.repository;

import org.springframework.stereotype.Repository;
import prueba.tecnica.inditex.core.luis.domain.repository.PriceRepository;
import prueba.tecnica.inditex.core.luis.domain.model.Price;
import prueba.tecnica.inditex.core.luis.infrastructure.jpa.PriceEntityJpa;
import prueba.tecnica.inditex.core.luis.infrastructure.mapper.PriceRepositoryMapper;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class PriceRepositoryImpl implements PriceRepository {

    private final PriceEntityJpa priceEntityJpa;
    private final PriceRepositoryMapper priceMapper;

    public PriceRepositoryImpl(PriceEntityJpa priceEntityJpa, PriceRepositoryMapper priceMapper) {
        this.priceEntityJpa = priceEntityJpa;
        this.priceMapper = priceMapper;
    }

    @Override
    public Optional<Price> getPrice(LocalDateTime date, Long productId, Long brandId) {
        return priceEntityJpa.findPrice(date, productId, brandId)
                .map(priceMapper::toPrice);
    }
}
