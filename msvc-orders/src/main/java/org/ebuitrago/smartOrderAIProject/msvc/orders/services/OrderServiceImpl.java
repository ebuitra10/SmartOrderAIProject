package org.ebuitrago.smartOrderAIProject.msvc.orders.services;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.ebuitrago.smartOrderAIProject.msvc.orders.domain.OrderEntity;
import org.ebuitrago.smartOrderAIProject.msvc.orders.repositories.IOrderRepository;
import org.ebuitrago.smartOrderAIProject.msvc.orders.services.usecase.IOrderServiceUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Implementación del caso de uso para las operaciones relacionadas con las órdenes.
 *
 * <p>Esta clase actúa como puente entre el controlador y el repositorio, encapsulando la lógica de negocio
 * correspondiente a la manipulación de entidades {@link OrderEntity}.
 * Se utiliza {@link IOrderRepository} como dependencia para interactuar con la capa de persistencia.</p>
 */
@Getter
@Setter
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements IOrderServiceUseCase {

    private final IOrderRepository iOrderRepository;

    /**
     * Obtiene todas las órdenes registradas.
     *
     * @return Lista con todas las órdenes existentes.
     */
    @Transactional(readOnly = true)
    @Override
    public List<OrderEntity> getAll() {
        return iOrderRepository.getAll();
    }

    /**
     * Busca una orden por su identificador único.
     *
     * @param id Identificador único de la orden.
     * @return {@link Optional} que contiene la orden si existe.
     * @throws RuntimeException si no se encuentra ninguna orden con el ID ingresado.
     */
    @Transactional(readOnly = true)
    @Override
    public Optional<OrderEntity> getById(Integer id) {

        Optional<OrderEntity> order = iOrderRepository.getById(id);

        if (order.isEmpty()) {
            throw new RuntimeException("Número de orden no encontrado");
        }

        return order;
    }

    /**
     * Obtiene todas las órdenes realizadas en una fecha específica.
     *
     * @param orderByDateList Fecha en la que se quiere filtrar las órdenes.
     * @return Lista de órdenes realizadas en la fecha especificada.
     * @throws RuntimeException si no existen órdenes registradas en dicha fecha.
     */
    @Transactional(readOnly = true)
    @Override
    public List<OrderEntity> getByDate(LocalDate orderByDateList) {

        List<OrderEntity> order = iOrderRepository.getByDate(orderByDateList);
        if (order.isEmpty()) {
            throw new RuntimeException("En la fecha que ingresaste no se realizaron órdenes");
        }

        return order;

    }

    /**
     * Guarda una nueva orden en la base de datos.
     *
     * @param orderEntity Objeto {@link OrderEntity} a guardar.
     * @return La orden guardada con su ID generado.
     */
    @Transactional
    @Override
    public OrderEntity save(OrderEntity orderEntity) {
        return iOrderRepository.save(orderEntity);
    }

    /**
     * Actualiza una orden existente.
     *
     * @param orderEntity Objeto {@link OrderEntity} con los nuevos datos.
     * @return La orden actualizada.
     * @throws RuntimeException si no se encuentra la orden a actualizar.
     */
    @Transactional
    @Override
    public OrderEntity update(OrderEntity orderEntity) {

        Optional<OrderEntity> order = iOrderRepository.getById(orderEntity.getId());

        if (order.isEmpty()) {
            throw new RuntimeException("Número de orden no encontrado");
        }

        OrderEntity updatedOrder = order.get();
        updatedOrder.setDate(orderEntity.getDate());
        updatedOrder.setStore(orderEntity.getStore());
        updatedOrder.setTotalPrice(orderEntity.getTotalPrice());
        updatedOrder.setPaymentMethod(orderEntity.getPaymentMethod());

        return updatedOrder;

    }

    /**
     * Elimina una orden según su identificador único.
     *
     * @param id Identificador de la orden a eliminar.
     * @return {@code true} si la eliminación fue exitosa.
     * @throws RuntimeException si no se encuentra la orden con el ID proporcionado.
     */
    @Transactional
    @Override
    public Boolean deleteById(Integer id) {

        Optional<OrderEntity> order = iOrderRepository.getById(id);

        if (order.isEmpty()) {
            throw new RuntimeException("Número de orden no encontrado");
        }

        iOrderRepository.deleteById(id);
        return true;

    }
}
