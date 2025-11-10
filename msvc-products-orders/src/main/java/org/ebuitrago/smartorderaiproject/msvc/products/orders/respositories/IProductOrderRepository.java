package org.ebuitrago.smartorderaiproject.msvc.products.orders.respositories;


import org.ebuitrago.smartorderaiproject.msvc.products.orders.domain.ProductOrderEntity;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define las operaciones de persistencia para la entidad {@link ProductOrderEntity}.
 * Proporciona métodos para gestionar los productos asociados a una orden.
 */
public interface IProductOrderRepository {

    /**
     * Obtiene todas las relaciones producto-orden registradas.
     *
     * @return lista de todas las entidades {@link ProductOrderEntity}.
     */
    List<ProductOrderEntity> getAll();

    /**
     * Busca una relación producto-orden por el ID de la orden.
     *
     * @param orderId identificador de la orden.
     * @return un {@link Optional} con la entidad si existe.
     */
    Optional<ProductOrderEntity> getByOrderId(Integer orderId);

    /**
     * Guarda o actualiza una relación producto-orden en la base de datos.
     *
     * @param productOrderEntity entidad a guardar.
     * @return la entidad persistida.
     */
    ProductOrderEntity save(ProductOrderEntity productOrderEntity);

    /**
     * Elimina una relación producto-orden según el ID de la orden.
     *
     * @param orderId identificador de la orden.
     */
    void deleteByOrderId(Integer orderId);
}

