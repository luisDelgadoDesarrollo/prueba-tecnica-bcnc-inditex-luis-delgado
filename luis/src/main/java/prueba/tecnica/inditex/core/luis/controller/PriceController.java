package prueba.tecnica.inditex.core.luis.controller;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import prueba.tecnica.inditex.core.luis.controller.dto.PriceDto;
import prueba.tecnica.inditex.core.luis.controller.mapper.PriceControllerMapper;
import prueba.tecnica.inditex.core.luis.domain.service.PriceService;

import java.time.LocalDateTime;

@RestController
public class PriceController {


    private final PriceService priceService;
    private final PriceControllerMapper priceControllerMapper;

    public PriceController(PriceService priceService, PriceControllerMapper priceControllerMapper) {
        this.priceService = priceService;
        this.priceControllerMapper = priceControllerMapper;
    }


    @GetMapping("/prices")
    public ResponseEntity<PriceDto> getPrices(@RequestParam @NotNull LocalDateTime date, @RequestParam @Positive Long productId,
                                              @RequestParam @Positive Long brandId) {
        return ResponseEntity.ok(
                priceControllerMapper.toPriceDto
                        (priceService.getPrice(date, productId, brandId)));
    }
}
