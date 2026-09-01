package prueba.tecnica.inditex.core.luis.domain.repository;

import prueba.tecnica.inditex.core.luis.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {

    Optional<Price> getPrice(LocalDateTime date, Long productId, Long brandId);
}
