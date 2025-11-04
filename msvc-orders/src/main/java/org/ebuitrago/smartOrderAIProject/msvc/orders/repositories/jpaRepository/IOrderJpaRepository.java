package org.ebuitrago.smartOrderAIProject.msvc.orders.repositories.jpaRepository;

import org.ebuitrago.smartOrderAIProject.msvc.orders.domain.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

/**
 * Repositorio JPA para la entidad {@link OrderEntity}.
 *
 * <p>Extiende {@link JpaRepository} proporcionando operaciones CRUD básicas y
 * permite agregar consultas personalizadas utilizando el estándar de nomenclatura de Spring Data JPA.</p>
 *
 * <p>Este repositorio es implementado automáticamente por Spring en tiempo de ejecución,
 * por lo que no se requiere una implementación explícita.</p>
 */
public interface IOrderJpaRepository extends JpaRepository<OrderEntity, Integer> {

    /**
     * Busca todas las órdenes asociadas a una fecha específica.
     *
     * @param orderByDate la fecha para filtrar las órdenes.
     * @return una lista de entidades {@link OrderEntity} que coinciden con la fecha indicada.
     */
    List<OrderEntity> findByDate(LocalDate orderByDate);
}