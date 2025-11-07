package org.ebuitrago.smartorderaiproject.msvc.products.repositories.JpaRepository;

import org.ebuitrago.smartorderaiproject.msvc.products.domain.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * Repositorio JPA para la gestión de la entidad {@link ProductEntity}.
 * <p>
 * Extiende de {@link JpaRepository} para proporcionar las operaciones CRUD básicas,
 * así como la capacidad de definir métodos de consulta personalizados basados en
 * la convención de nombres de Spring Data JPA.
 * </p>
 */
public interface IProductJpaRepository extends JpaRepository<ProductEntity, Integer> {

    List<ProductEntity> findProductEntitiesByProductCode(String productCode);
}
