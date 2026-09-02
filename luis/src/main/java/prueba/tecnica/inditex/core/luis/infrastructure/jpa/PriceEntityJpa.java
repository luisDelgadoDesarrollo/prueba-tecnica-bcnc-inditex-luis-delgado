package prueba.tecnica.inditex.core.luis.infrastructure.jpa;

import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import prueba.tecnica.inditex.core.luis.infrastructure.entity.PriceEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PriceEntityJpa extends JpaRepository<PriceEntity, Long> {

    @Query("""
            SELECT p FROM PriceEntity p
            WHERE p.startDate <= :date AND p.endDate >= :date
            AND p.productId = :productId
            AND p.brandId = :brandId
            ORDER BY p.priority DESC
            """)
    List<PriceEntity> findPricesByCriteria(
            @Param("date") LocalDateTime date, 
            @Param("productId") Long productId, 
            @Param("brandId") Long brandId, 
            Limit limit);

    default Optional<PriceEntity> findPrice(LocalDateTime date, Long productId, Long brandId) {
        return findPricesByCriteria(date, productId, brandId, Limit.of(1))
                .stream().findFirst();
    }
}
