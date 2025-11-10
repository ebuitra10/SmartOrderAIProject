package org.ebuitrago.smartorderaiproject.msvc.products.services;


import lombok.RequiredArgsConstructor;
import org.ebuitrago.smartorderaiproject.msvc.products.clientRest.InventoryClientRest;
import org.ebuitrago.smartorderaiproject.msvc.products.domain.ProductEntity;
import org.ebuitrago.smartorderaiproject.msvc.products.domain.dto.InventoryRequest;
import org.ebuitrago.smartorderaiproject.msvc.products.repositories.IProductRespository;
import org.ebuitrago.smartorderaiproject.msvc.products.services.useCase.IProductUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del caso de uso para la gestión de productos.
 * <p>
 * Esta clase representa la capa de servicio que encapsula la lógica
 * de negocio relacionada con los productos y delega las operaciones
 * de persistencia al repositorio correspondiente.
 * </p>
 *
 * <p>Incluye manejo transaccional para garantizar la integridad de los datos
 * durante las operaciones de lectura y escritura.</p>
 */
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements IProductUseCase {

    private final IProductRespository iProductRespository;

    private final InventoryClientRest  inventoryClientRest;

    /**
     * Obtiene la lista completa de productos.
     * <p>El acceso es de solo lectura.</p>
     */
    @Transactional(readOnly = true)
    @Override
    public List<ProductEntity> getAll() {
        return iProductRespository.getAll();
    }

    /**
     * Obtiene un producto según su identificador único.
     * <p>El acceso es de solo lectura.</p>
     *
     * @throws RuntimeException si no existe un producto con el ID proporcionado
     */
    @Transactional(readOnly = true)
    @Override
    public Optional<ProductEntity> getById(Integer id) {

        Optional<ProductEntity> productid = iProductRespository.getById(id);

        if(productid.isEmpty()){
            throw (new RuntimeException("Numero de id no encontrado"));
        }

        return productid;
    }

    /**
     * Obtiene un producto según su código interno.
     * <p>El acceso es de solo lectura.</p>
     *
     * @throws RuntimeException si no existe un producto con el código proporcionado
     */
    @Transactional(readOnly = true)
    @Override
    public List<ProductEntity> getByProductCode(String productCode) {

        List<ProductEntity> productCodeDb = iProductRespository.getByProductCode(productCode);

        if(productCodeDb.isEmpty()){
            throw (new RuntimeException("No existe un producto con el codigo: " + productCode));
        }

        return productCodeDb;
    }

    /**
     * Guarda un nuevo producto en la base de datos.
     * <p>Operación transaccional de escritura.</p>
     */
    @Transactional
    @Override
    public ProductEntity save(ProductEntity newProduct) {

        ProductEntity saved = iProductRespository.save(newProduct);

        InventoryRequest inventoryRequest = new InventoryRequest(saved.getProductCode(),1);

        inventoryClientRest.save(inventoryRequest);

        return saved;
    }

    /**
     * Actualiza un producto existente.
     * <p>Verifica la existencia y coherencia del código interno antes de actualizar.</p>
     * Actualiza tambien el inventario de los productos llamando a InventoryClientRest
     *
     * @throws RuntimeException si no existe el producto o los códigos no coinciden
     */
    @Transactional
    @Override
    public ProductEntity update(ProductEntity updatedProduct) {

        Optional<ProductEntity> productDb = iProductRespository.getById(updatedProduct.getId());

        if(productDb.isEmpty()){
            throw (new RuntimeException("No existe el producto con el id: " + updatedProduct.getId()));
        }

        ProductEntity productEntity = productDb.get();
        productEntity.setProductName(updatedProduct.getProductName());
        productEntity.setProductCode(updatedProduct.getProductCode());
        productEntity.setPrice(updatedProduct.getPrice());
        productEntity.setImageUrl(updatedProduct.getImageUrl());
        productEntity.setDescription(updatedProduct.getDescription());

        Integer productStock = inventoryClientRest.getStockByProductCode(productEntity.getProductCode());

        InventoryRequest inventoryRequest = new InventoryRequest();
        inventoryRequest.setProductCode(updatedProduct.getProductCode());
        inventoryRequest.setInitialStock(productStock);

        inventoryClientRest.save(inventoryRequest);
        return iProductRespository.save(productEntity);
    }

    /**
     * Elimina un producto de la base de datos mediante su identificador.
     * <p>Operación transaccional de escritura.</p>
     *
     * @throws RuntimeException si el producto no existe
     */
    @Transactional
    @Override
    public Boolean deleteById(Integer id) {

        Optional<ProductEntity> productDb = iProductRespository.getById(id);
        if(productDb.isEmpty()){
            throw (new RuntimeException("No existe el producto con el codigo: " + id));
        }


        iProductRespository.deleteById(productDb.get().getId());
        inventoryClientRest.delete(productDb.get().getProductCode());

        return true;
    }

}
