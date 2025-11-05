package org.ebuitrago.smartorderaiproject.msvc.products.repositories;

import lombok.RequiredArgsConstructor;
import org.ebuitrago.smartorderaiproject.msvc.products.domain.ProductEntity;
import org.ebuitrago.smartorderaiproject.msvc.products.repositories.JpaRepository.IProductJpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Implementación de la interfaz {@link IProductRespository} que actúa como un adaptador
 * entre la capa de dominio y la capa de persistencia utilizando Spring Data JPA.
 * <p>
 * Esta clase delega las operaciones CRUD al repositorio JPA {@link IProductJpaRepository},
 * cumpliendo con el principio de responsabilidad única y permitiendo la abstracción
 * de los detalles de acceso a datos para las capas superiores de la aplicación.
 * </p>
 * */
@RequiredArgsConstructor
@Repository
public class PrudctRepositoryImpl implements  IProductRespository {

    private final IProductJpaRepository iProductJpaRepository;

    @Override
    public List<ProductEntity> getAll() {
        return iProductJpaRepository.findAll();
    }

    @Override
    public Optional<ProductEntity> getById(Integer id) {
        return iProductJpaRepository.findById(id);
    }

    @Override
    public Optional<ProductEntity> getByProductCode(String productCode) {
        return iProductJpaRepository.findByProductCode(productCode);
    }

    @Override
    public ProductEntity save(ProductEntity productEntity) {
        return iProductJpaRepository.save(productEntity);
    }

    @Override
    public void delete(ProductEntity productEntity) {
        iProductJpaRepository.delete(productEntity);
    }
}
