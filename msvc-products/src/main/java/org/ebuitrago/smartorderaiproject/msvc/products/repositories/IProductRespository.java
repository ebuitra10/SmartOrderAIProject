package org.ebuitrago.smartorderaiproject.msvc.products.repositories;

import org.ebuitrago.smartorderaiproject.msvc.products.domain.ProductEntity;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para gestionar las operaciones de acceso a datos relacionadas con la entidad {@link ProductEntity}.
 * <p>
 * Esta interfaz define los métodos necesarios para realizar las operaciones básicas de
 * persistencia y consulta de productos dentro del sistema, aplicando el patrón Repository.
 * </p>
 */
public interface IProductRespository {

    /**
     * Obtiene la lista completa de productos registrados en el sistema.
     *
     * @return una lista de todas las entidades {@link ProductEntity}.
     */
    List<ProductEntity> getAll();

    /**
     * Busca un producto por su identificador único.
     *
     * @param id el identificador único del producto.
     * @return un {@link Optional} conteniendo la entidad {@link ProductEntity} si existe,
     * o vacío si no se encuentra.
     */
    Optional<ProductEntity> getById(Integer id);

    /**
     * Busca un producto por su código interno o SKU.
     *
     * @param productCode el código del producto.
     * @return un {@link Optional} conteniendo la entidad {@link ProductEntity} si existe,
     * o vacío si no se encuentra.
     */
    List<ProductEntity> getByProductCode(String productCode);

    /**
     * Guarda o actualiza un producto en la base de datos.
     *
     * @param productEntity la entidad {@link ProductEntity} que será persistida.
     * @return la entidad persistida, incluyendo cualquier cambio generado automáticamente (como el ID).
     */
    ProductEntity save(ProductEntity productEntity);

    /**
     * Elimina un producto de la base de datos.
     *
     * @param id la entidad {@link ProductEntity} que se desea eliminar.
     */
    void deleteById(Integer id);
}
