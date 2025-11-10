package org.ebuitrago.smartorderaiproject.msvc.products.orders.respositories.jpaRepository;

import org.ebuitrago.smartorderaiproject.msvc.products.orders.domain.ProductOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Repositorio JPA para la entidad {@link ProductOrderEntity}.
 * Proporciona operaciones de acceso a datos relacionadas con los productos
 * asociados a una orden, como la búsqueda y eliminación por ID de orden.
 */
public interface IProductOrderJpaRepository extends JpaRepository<ProductOrderEntity, Integer> {

    Optional<ProductOrderEntity> findByOrderId(Integer orderId);

    @Transactional
    @Modifying
    void deleteByOrderId(Integer orderId);
}
