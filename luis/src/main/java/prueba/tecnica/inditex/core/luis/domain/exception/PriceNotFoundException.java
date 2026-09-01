package prueba.tecnica.inditex.core.luis.domain.exception;

public class PriceNotFoundException extends RuntimeException {

    public PriceNotFoundException(String message) {
        super(message);
    }

    public static PriceNotFoundException forProductAndBrand(Long productId, Long brandId) {
        return new PriceNotFoundException(
                String.format("Precio no encontrado para el producto: %d y la marca: %d", productId, brandId)
        );
    }
}

