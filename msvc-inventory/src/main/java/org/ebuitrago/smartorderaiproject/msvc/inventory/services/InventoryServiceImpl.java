package org.ebuitrago.smartorderaiproject.msvc.inventory.services;


import lombok.RequiredArgsConstructor;
import org.ebuitrago.smartorderaiproject.msvc.inventory.domain.InventoryEntity;
import org.ebuitrago.smartorderaiproject.msvc.inventory.domain.dto.InventoryResponseDto;
import org.ebuitrago.smartorderaiproject.msvc.inventory.repositories.InvetoryRepository;
import org.ebuitrago.smartorderaiproject.msvc.inventory.services.useCase.InventoryUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;



/**
 * Implementación del caso de uso para la gestión del inventario.
 * <p>
 * Contiene la lógica de negocio para consultar, registrar, actualizar y modificar
 * el stock de productos. Utiliza {@link InvetoryRepository} para acceder a la capa de datos.
 * </p>
 */
@RequiredArgsConstructor
@Service
public class InventoryServiceImpl implements InventoryUseCase {

    private final InvetoryRepository invetoryRepository;

    /**
     * Obtiene todos los registros de inventario existentes.
     *
     * @return lista completa de inventarios.
     */
    @Transactional(readOnly = true)
    @Override
    public List<InventoryEntity> getAll() {
        return invetoryRepository.getAll();
    }

    /**
     * Obtiene los registros de inventario asociados a un código de producto.
     *
     * @param productCode código único del producto.
     * @return lista de inventarios correspondientes al código.
     * @throws RuntimeException si no se encuentra ningún producto con el código especificado.
     */
    @Transactional(readOnly = true)
    @Override
    public List<InventoryEntity> getAllByProductCode(String productCode) {

        List<InventoryEntity> product = invetoryRepository.getAllByProductCode(productCode);

        if (product.isEmpty()) {
            throw new RuntimeException("No existe el producto por el código que ingresaste");
        }

        return product;

    }

    /**
     * Obtiene la cantidad de stock disponible de un producto específico.
     *
     * @param productCode código único del producto.
     * @return cantidad disponible de stock.
     * @throws RuntimeException si el producto no existe.
     */
    @Transactional(readOnly = true)
    public Optional<Integer> getStockByProductCode(String productCode) {

        Optional<InventoryEntity> product = invetoryRepository.getByProductCode(productCode);
        if (product.isEmpty()) {
            throw new RuntimeException("No existe el producto por el código que ingresaste");
        }

        Integer stockProduct = product.get().getStockQuantity();
        return Optional.of(stockProduct);

    }

    /**
     * Crea un nuevo registro de inventario o actualiza el existente.
     * <p>
     * Si el producto ya existe, se suma la nueva cantidad al stock actual.
     * </p>
     *
     * @param newProduct objeto con los datos del inventario.
     * @return el inventario creado o actualizado.
     */
    @Transactional
    @Override
    public InventoryEntity createOrUpdateInvetory(InventoryResponseDto newProduct) {

        Optional<InventoryEntity> inventoryDb = invetoryRepository.getByProductCode(newProduct.getProductCode());
        InventoryEntity inventoryEntity;

        if (inventoryDb.isPresent()) {
            inventoryEntity = inventoryDb.get();
            if (inventoryEntity.getProductCode().equals(newProduct.getProductCode())) {
                inventoryEntity.setStockQuantity(newProduct.getInitialStock() + inventoryEntity.getStockQuantity());
            }
        } else {
            inventoryEntity = new InventoryEntity();
            inventoryEntity.setProductCode(newProduct.getProductCode());
            inventoryEntity.setStockQuantity(newProduct.getInitialStock());
        }

        return invetoryRepository.save(inventoryEntity);

    }

    @Transactional
    @Override
    public InventoryEntity decrementInventory(String productCode, Integer quantity) {

        Optional<InventoryEntity> productDb = invetoryRepository.getByProductCode(productCode);

        if (productDb.isEmpty()){
            throw (new RuntimeException("No existe el producto por el codigo ingresado, prueba con otro codigo"));
        }

        if (productDb.get().getStockQuantity() < quantity) {
            throw (new RuntimeException("No hay stock disponible"));
        }

        productDb.get().setStockQuantity(productDb.get().getStockQuantity() - quantity);

        return invetoryRepository.save(productDb.get());

    }

    /**
     * Reduce en una unidad el stock del producto según su código.
     *
     * @param productCode código único del producto.
     * @return {@code true} si se modificó correctamente el stock.
     * @throws RuntimeException si el producto no existe o no hay stock disponible.
     */
    @Transactional
    @Override
    public Boolean deleteByProductCode(String productCode) {

        Optional<InventoryEntity> productDb = invetoryRepository.getByProductCode(productCode);
        if (productDb.isEmpty()) {
            throw new RuntimeException("No existe el producto por el código que ingresaste");
        }

        InventoryEntity inventoryEntity = productDb.get();
        if (inventoryEntity.getStockQuantity() > 0) {
            inventoryEntity.setStockQuantity(inventoryEntity.getStockQuantity() - 1);
            invetoryRepository.save(inventoryEntity);
            return true;
        } else {
            throw new RuntimeException("No hay stock para modificar");
        }

    }

}
