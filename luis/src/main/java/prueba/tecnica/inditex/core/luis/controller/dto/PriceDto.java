package prueba.tecnica.inditex.core.luis.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PriceDto(
        Long productId,
        Long brandId,
        Integer priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        BigDecimal price,
        String currency
) {
}
