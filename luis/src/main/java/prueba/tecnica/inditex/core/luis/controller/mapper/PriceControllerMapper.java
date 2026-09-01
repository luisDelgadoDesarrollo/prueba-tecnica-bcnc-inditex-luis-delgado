package prueba.tecnica.inditex.core.luis.controller.mapper;

import org.mapstruct.Mapper;
import prueba.tecnica.inditex.core.luis.controller.dto.PriceDto;
import prueba.tecnica.inditex.core.luis.domain.model.Price;

@Mapper
public interface PriceControllerMapper {
    PriceDto toPriceDto(Price price);

}
