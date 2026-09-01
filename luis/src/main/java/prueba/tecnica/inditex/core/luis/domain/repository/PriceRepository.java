package prueba.tecnica.inditex.core.luis.domain.repository;

import prueba.tecnica.inditex.core.luis.domain.model.Price;

import java.time.LocalDateTime;

public interface PriceRepository {

    Price getPrice(LocalDateTime date, Long productId, Long brandId);
}
