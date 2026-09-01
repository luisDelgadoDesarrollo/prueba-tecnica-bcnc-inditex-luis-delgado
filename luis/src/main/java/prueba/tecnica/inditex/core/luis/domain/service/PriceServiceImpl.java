package prueba.tecnica.inditex.core.luis.domain.service;

import org.springframework.stereotype.Service;
import prueba.tecnica.inditex.core.luis.domain.exception.PriceNotFoundException;
import prueba.tecnica.inditex.core.luis.domain.repository.PriceRepository;
import prueba.tecnica.inditex.core.luis.domain.model.Price;

import java.time.LocalDateTime;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Price getPrice(LocalDateTime date, Long productId, Long brandId) {
        return priceRepository.getPrice(date, productId, brandId)
                .orElseThrow(() -> PriceNotFoundException.forProductAndBrand(productId, brandId));
    }
}
