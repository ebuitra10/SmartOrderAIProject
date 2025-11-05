package org.ebuitrago.smartorderaiproject.msvc.products.repositories.JpaRepository;

import org.ebuitrago.smartorderaiproject.msvc.products.domain.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repositorio JPA para la gestión de la entidad {@link ProductEntity}.
 * <p>
 * Extiende de {@link JpaRepository} para proporcionar las operaciones CRUD básicas,
 * así como la capacidad de definir métodos de consulta personalizados basados en
 * la convención de nombres de Spring Data JPA.
 * </p>
 */
public interface IProductJpaRepository extends JpaRepository<ProductEntity, Integer> {

    /**
     * Busca un producto por su código interno o SKU.
     *
     * @param productCode el código del producto a buscar.
     * @return un {@link Optional} conteniendo la entidad {@link ProductEntity} si se encuentra,
     * o vacío si no existe un producto con el código especificado.
     */
    Optional<ProductEntity> findByProductCode(String productCode);
}
