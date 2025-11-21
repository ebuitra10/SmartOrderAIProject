package org.ebuitrago.smartorderaiproject.msvc.inventory.repositories;


import org.ebuitrago.smartorderaiproject.msvc.inventory.domain.InventoryEntity;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio de dominio para la gestión de inventarios.
 * <p>
 * Define las operaciones de acceso y manipulación de datos para la entidad {@link InventoryEntity},
 * independiente de la implementación de persistencia.
 * </p>
 */
public interface InvetoryRepository {

    /**
     * Obtiene todos los registros de inventario.
     *
     * @return lista completa de inventarios.
     */
    List<InventoryEntity> getAll();

    /**
     * Obtiene los registros de inventario asociados a un código de producto.
     *
     * @param productCode código único del producto.
     * @return lista de inventarios correspondientes al código.
     */
    List<InventoryEntity> getAllByProductCode(String productCode);

    /**
     * Busca un inventario específico según el código del producto.
     *
     * @param productCode código único del producto.
     * @return un {@link Optional} con el inventario si existe.
     */
    Optional<InventoryEntity> getByProductCode(String productCode);

    Optional<BigDecimal> getUnitPriceByProductCode(String productCode);

    /**
     * Guarda un nuevo inventario o actualiza uno existente.
     *
     * @param newProduct entidad del inventario a registrar o actualizar.
     * @return el inventario guardado.
     */
    InventoryEntity save(InventoryEntity newProduct);
}

