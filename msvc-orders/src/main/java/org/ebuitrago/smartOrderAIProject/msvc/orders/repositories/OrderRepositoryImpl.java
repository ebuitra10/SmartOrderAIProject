package org.ebuitrago.smartOrderAIProject.msvc.orders.repositories;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.ebuitrago.smartOrderAIProject.msvc.orders.domain.OrderEntity;
import org.ebuitrago.smartOrderAIProject.msvc.orders.repositories.jpaRepository.IOrderJpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Implementación del repositorio para la entidad {@link OrderEntity}.
 * Esta clase actúa como el adaptador entre la capa de dominio y la capa de persistencia
 * utilizando {@link IOrderJpaRepository} para interactuar con la base de datos.
 * Contiene métodos básicos de consulta, guardado y eliminación de órdenes.
 */
@RequiredArgsConstructor
@Getter
@Setter
@Repository
public class OrderRepositoryImpl implements IOrderRepository {

    private final IOrderJpaRepository iOrderJpaRepository;

    @Override
    public List<OrderEntity> getAll() {
        return iOrderJpaRepository.findAll();
    }

    @Override
    public Optional<OrderEntity> getById(Integer id) {
        return iOrderJpaRepository.findById(id);
    }

    @Override
    public List<OrderEntity> getByDate(LocalDate orderByDateList) {
        return iOrderJpaRepository.findByDate(orderByDateList);
    }

    @Override
    public OrderEntity save(OrderEntity orderEntity) {
        return iOrderJpaRepository.save(orderEntity);
    }

    @Override
    public void delete(OrderEntity orderEntity) {
        iOrderJpaRepository.delete(orderEntity);
    }
}
