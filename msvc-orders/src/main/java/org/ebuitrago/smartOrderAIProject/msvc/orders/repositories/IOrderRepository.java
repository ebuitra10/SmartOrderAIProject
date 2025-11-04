package org.ebuitrago.smartOrderAIProject.msvc.orders.repositories;

import org.ebuitrago.smartOrderAIProject.msvc.orders.domain.OrderEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define las operaciones de persistencia relacionadas con la entidad {@link OrderEntity}.
 *
 * <p>Esta interfaz actúa como un contrato que debe ser implementado por la capa de acceso a datos
 * (por ejemplo, en {@link OrderRepositoryImpl}), proporcionando métodos para la interacción con
 * la base de datos de órdenes en un sistema de tienda.</p>
 */
public interface IOrderRepository {

    /**
     * Obtiene todas las órdenes registradas en el sistema.
     *
     * @return una lista con todas las entidades {@link OrderEntity} almacenadas.
     */
    List<OrderEntity> getAll();

    /**
     * Busca una orden específica por su identificador único.
     *
     * @param id el identificador de la orden a buscar.
     * @return un {@link Optional} que puede contener la entidad {@link OrderEntity} si existe,
     *         o estar vacío si la orden no fue encontrada.
     */
    Optional<OrderEntity> getById(Integer id);

    /**
     * Obtiene una lista de órdenes realizadas en una fecha específica.
     *
     * @param orderByDateList la fecha de las órdenes a buscar.
     * @return una lista de entidades {@link OrderEntity} correspondientes a la fecha dada.
     */
    List<OrderEntity> getByDate(LocalDate orderByDateList);

    /**
     * Guarda una nueva orden o actualiza una existente en la base de datos.
     *
     * @param orderEntity la entidad {@link OrderEntity} a guardar o actualizar.
     * @return la entidad guardada o actualizada, incluyendo cualquier cambio generado automáticamente
     *         por la base de datos (como el ID generado).
     */
    OrderEntity save(OrderEntity orderEntity);

    /**
     * Elimina una orden existente de la base de datos.
     *
     * @param orderEntity la entidad {@link OrderEntity} que se desea eliminar.
     */
    void delete(OrderEntity orderEntity);
}
