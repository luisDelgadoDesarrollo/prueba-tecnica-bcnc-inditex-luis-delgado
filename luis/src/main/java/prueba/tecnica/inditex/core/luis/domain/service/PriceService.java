package prueba.tecnica.inditex.core.luis.domain.service;

import prueba.tecnica.inditex.core.luis.domain.model.Price;

import java.time.LocalDateTime;

public interface PriceService {
    Price getPrice(LocalDateTime date, Long productId, Long brandId);
}
