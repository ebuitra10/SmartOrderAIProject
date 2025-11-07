package org.ebuitrago.smartorderaiproject.msvc.products.services.useCase;

import org.ebuitrago.smartorderaiproject.msvc.products.domain.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface IProductUseCase {

    List<ProductEntity> getAll();

    Optional<ProductEntity> getById(Integer id);

    List<ProductEntity> getByProductCode(String productCode);

    ProductEntity save(ProductEntity newProduct);

    ProductEntity update(ProductEntity updatedProduct);

    Boolean deleteById(Integer id);

}
