package org.ebuitrago.smartorderaiproject.msvc.inventory.repositories;

import lombok.RequiredArgsConstructor;
import org.ebuitrago.smartorderaiproject.msvc.inventory.domain.InventoryEntity;
import org.ebuitrago.smartorderaiproject.msvc.inventory.repositories.jpaRepository.InventoryJpaRespository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Implementación del repositorio de inventario que utiliza JPA para acceder a la base de datos.
 * <p>
 * Delega las operaciones CRUD al {@link InventoryJpaRespository}.
 * </p>
 */
@RequiredArgsConstructor
@Repository
public class InventoryRepositoryImpl implements InvetoryRepository {

    private final InventoryJpaRespository inventoryJpaRespository;

    @Override
    public List<InventoryEntity> getAll() {
        return inventoryJpaRespository.findAll();
    }

    @Override
    public List<InventoryEntity> getAllByProductCode(String productCode) {
        return inventoryJpaRespository.findAllByProductCode(productCode);
    }

    @Override
    public Optional<InventoryEntity> getByProductCode(String productCode) {
        return inventoryJpaRespository.findByProductCode(productCode);
    }

    @Override
    public InventoryEntity save(InventoryEntity newProduct) {
        return inventoryJpaRespository.save(newProduct);
    }
}

