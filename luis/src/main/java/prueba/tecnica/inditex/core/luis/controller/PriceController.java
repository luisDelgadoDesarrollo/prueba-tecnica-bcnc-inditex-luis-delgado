package prueba.tecnica.inditex.core.luis.controller;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import prueba.tecnica.inditex.core.luis.controller.dto.PriceDto;
import prueba.tecnica.inditex.core.luis.controller.mapper.PriceControllerMapper;
import prueba.tecnica.inditex.core.luis.domain.service.PriceService;

import java.time.LocalDateTime;

@RestController
@Validated
public class PriceController {

    private final PriceService priceService;
    private final PriceControllerMapper priceControllerMapper;

    public PriceController(PriceService priceService, PriceControllerMapper priceControllerMapper) {
        this.priceService = priceService;
        this.priceControllerMapper = priceControllerMapper;
    }

    @GetMapping("/brands/{brandId}/products/{productId}/prices")
    public ResponseEntity<PriceDto> getPrices(
            @PathVariable @Positive Long brandId,
            @PathVariable @Positive Long productId,
            @RequestParam @NotNull LocalDateTime date) {
        return ResponseEntity.ok(
                priceControllerMapper.toPriceDto(
                        priceService.getPrice(date, productId, brandId)));
    }
}
