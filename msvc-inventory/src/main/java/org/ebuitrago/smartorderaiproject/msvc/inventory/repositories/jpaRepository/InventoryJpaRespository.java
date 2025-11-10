package org.ebuitrago.smartorderaiproject.msvc.inventory.repositories.jpaRepository;

import org.ebuitrago.smartorderaiproject.msvc.inventory.domain.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio JPA para la gestión del inventario.
 * <p>
 * Proporciona operaciones de acceso a datos para la entidad {@link InventoryEntity},
 * permitiendo consultas, registros y eliminaciones basadas en el código del producto.
 * </p>
 */
public interface InventoryJpaRespository extends JpaRepository<InventoryEntity, Integer> {

    /**
     * Obtiene una lista de registros de inventario según el código del producto.
     *
     * @param productCode código único del producto.
     * @return lista de inventarios asociados al código.
     */
    List<InventoryEntity> findAllByProductCode(String productCode);

    /**
     * Busca un registro de inventario específico por código de producto.
     *
     * @param productCode código único del producto.
     * @return un {@link Optional} que contiene el inventario si existe.
     */
    Optional<InventoryEntity> findByProductCode(String productCode);

    /**
     * Elimina un registro de inventario según el código del producto.
     *
     * @param productCode código único del producto.
     */
    @Transactional
    @Modifying
    void deleteByProductCode(String productCode);
}

