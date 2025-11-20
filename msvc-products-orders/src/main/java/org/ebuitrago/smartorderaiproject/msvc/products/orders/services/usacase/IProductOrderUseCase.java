package org.ebuitrago.smartorderaiproject.msvc.products.orders.services.usacase;

import org.ebuitrago.smartorderaiproject.msvc.products.orders.domain.ProductOrderEntity;
import org.ebuitrago.smartorderaiproject.msvc.products.orders.domain.dto.ProductListItemDto;


import java.util.List;
import java.util.Optional;

/**
 * Caso de uso para la gestión de productos asociados a una orden.
 * Define las operaciones principales para consultar, registrar
 * y eliminar productos vinculados a una factura.
 */
public interface IProductOrderUseCase {

    /**
     * Obtiene todos los registros de productos-orden.
     *
     * @return lista completa de ProductOrderEntity
     */
    List<ProductOrderEntity> getAll();

    /**
     * Obtiene un registro según el ID de la orden.
     *
     * @param orderId identificador de la orden
     * @return entidad encontrada, si existe
     */
    Optional<ProductOrderEntity> getByOrderId(Integer orderId);

    /**
     * Registra una lista de productos asociados a una orden.
     *
     * @param requestDto datos de la orden y sus productos
     * @return lista de entidades registradas
     */
    List<ProductOrderEntity> save(ProductListItemDto requestDto);

    /**
     * Elimina los datos asociados a una orden.
     *
     * @param orderId identificador de la orden
     * @return true si se eliminó correctamente
     */
    Boolean deleteByOrderId(Integer orderId);
}

