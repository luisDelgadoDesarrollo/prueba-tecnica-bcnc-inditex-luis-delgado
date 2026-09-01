package prueba.tecnica.inditex.core.luis.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import prueba.tecnica.inditex.core.luis.infrastructure.entity.PriceEntity;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceEntityJpa extends JpaRepository<PriceEntity, Long> {

    @Query("""
            SELECT p FROM PriceEntity p
            WHERE p.startDate <= :date AND p.endDate >= :date
            AND p.productId = :productId
            AND p.brandId = :brandId
            ORDER BY p.priority DESC
            LIMIT 1
            """)
    Optional<PriceEntity> findPrice(LocalDateTime date, Long productId, Long brandId);
}
