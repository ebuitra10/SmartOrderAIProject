package org.ebuitrago.smartOrderAIProject.msvc.orders.services.usecase;

import org.ebuitrago.smartOrderAIProject.msvc.orders.domain.OrderEntity;
import org.ebuitrago.smartOrderAIProject.msvc.orders.domain.OrderResponseDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define los casos de uso para la gestión de órdenes en el sistema.
 * Proporciona las operaciones principales del negocio relacionadas con las órdenes.
 */
public interface IOrderServiceUseCase {

    /**
     * Obtiene una lista de todas las órdenes registradas en el sistema.
     *
     * @return una lista de objetos {@link OrderEntity} que representan todas las órdenes existentes.
     */
    List<OrderEntity> getAll();

    /**
     * Busca una orden específica por su identificador único.
     *
     * @param id el identificador único de la orden a consultar.
     * @return un {@link Optional} que contiene el objeto {@link OrderEntity} si se encuentra,
     *         o un Optional vacío si no existe.
     * @throws RuntimeException si no existe una orden con el ID proporcionado.
     */
    Optional<OrderEntity> getById(Integer id);

    /**
     * Obtiene todas las órdenes realizadas en una fecha específica.
     *
     * @param orderByDateList la fecha por la cual se filtrarán las órdenes.
     * @return una lista de objetos {@link OrderEntity} correspondientes a esa fecha.
     * @throws RuntimeException si no se encuentran órdenes en la fecha indicada.
     */
    List<OrderEntity> getByDate(LocalDate orderByDateList);

    /**
     * Guarda una nueva orden en el sistema.
     *
     * @param orderEntity el objeto {@link OrderEntity} que representa la orden a ser guardada.
     * @return el objeto {@link OrderEntity} que fue guardado, incluyendo los datos generados (como el ID).
     */
    OrderEntity save(OrderEntity orderEntity);

    /**
     * Actualiza una orden existente en el sistema.
     *
     * @param orderEntity el objeto {@link OrderEntity} con la información actualizada.
     * @return el objeto {@link OrderEntity} actualizado después de guardar los cambios.
     * @throws RuntimeException si la orden a actualizar no existe en el sistema.
     */
    OrderEntity update(OrderEntity orderEntity);

    /**
     * Elimina una orden del sistema por su identificador único.
     *
     * @param id el identificador único de la orden a eliminar.
     * @return {@code true} si la eliminación fue exitosa.
     * @throws RuntimeException si no existe una orden con el ID proporcionado.
     */
    Boolean deleteById(Integer id);


    /**
     * Obtiene una lista de ordenes asociadas a un usuario
     * @param userId el usuario a buscar para filtrar sus ordenes
     * @return una lista de ordenes {@link OrderEntity} asociadas al usuario
     */
    List<OrderResponseDto> getOrdersByUser(Integer userId);


}

