package org.ebuitrago.smartorderaiproject.msvc.inventory.services.useCase;

import org.ebuitrago.smartorderaiproject.msvc.inventory.domain.InventoryEntity;
import org.ebuitrago.smartorderaiproject.msvc.inventory.domain.dto.InventoryRequest;

import java.util.List;
import java.util.Optional;

/**
 * Caso de uso que define las operaciones de negocio relacionadas con el inventario.
 * <p>
 * Proporciona la lógica de aplicación para la gestión de productos y sus existencias.
 * </p>
 */
public interface InventoryUseCase {

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
     * Obtiene la cantidad disponible en inventario de un producto.
     *
     * @param productCode código único del producto.
     * @return cantidad disponible, si el producto existe.
     */
    Optional<Integer> getStockByProductCode(String productCode);

    /**
     * Crea un nuevo inventario o actualiza uno existente.
     *
     * @param newProduct datos del inventario a registrar o actualizar.
     * @return el inventario creado o actualizado.
     */
    InventoryEntity createOrUpdateInvetory(InventoryRequest newProduct);

    /**
     * Elimina un registro de inventario según el código del producto.
     *
     * @param productCode código único del producto.
     * @return {@code true} si la eliminación fue exitosa; {@code false} en caso contrario.
     */
    Boolean deleteByProductCode(String productCode);
}

