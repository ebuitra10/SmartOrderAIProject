package org.ebuitrago.smartorderaiproject.msvc.products.orders.respositories;


import lombok.RequiredArgsConstructor;
import org.ebuitrago.smartorderaiproject.msvc.products.orders.domain.ProductOrderEntity;
import org.ebuitrago.smartorderaiproject.msvc.products.orders.respositories.jpaRepository.IProductOrderJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del repositorio {@link IProductOrderRepository}.
 * Gestiona las operaciones de persistencia para la entidad {@link ProductOrderEntity},
 * delegando las acciones al repositorio JPA correspondiente.
 */
@RequiredArgsConstructor
@Repository
public class ProductOrderRepositoryImpl implements  IProductOrderRepository {

    private final IProductOrderJpaRepository jpaRepository;

    @Override
    public List<ProductOrderEntity> getAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Optional<ProductOrderEntity> getByOrderId(Integer orderId) {
        return jpaRepository.findByOrderId(orderId);
    }

    @Override
    public ProductOrderEntity save(ProductOrderEntity productOrderEntity) {
        return jpaRepository.save(productOrderEntity);
    }

    @Override
    public void deleteByOrderId(Integer orderId) {
        jpaRepository.deleteByOrderId(orderId);
    }
}
