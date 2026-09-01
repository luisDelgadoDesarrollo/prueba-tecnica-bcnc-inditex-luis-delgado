package prueba.tecnica.inditex.core.luis.infrastructure.mapper;

import org.mapstruct.Mapper;
import prueba.tecnica.inditex.core.luis.domain.model.Price;
import prueba.tecnica.inditex.core.luis.infrastructure.entity.PriceEntity;

@Mapper
public interface PriceRepositoryMapper {

    Price toPrice(PriceEntity priceEntity);
}
