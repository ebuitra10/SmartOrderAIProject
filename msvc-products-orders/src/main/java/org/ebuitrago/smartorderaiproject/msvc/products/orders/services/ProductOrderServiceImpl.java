package org.ebuitrago.smartorderaiproject.msvc.products.orders.services;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.ebuitrago.smartorderaiproject.msvc.products.orders.clientrest.InventoryClientRest;
import org.ebuitrago.smartorderaiproject.msvc.products.orders.clientrest.OrderClientRest;
import org.ebuitrago.smartorderaiproject.msvc.products.orders.domain.ProductOrderEntity;
import org.ebuitrago.smartorderaiproject.msvc.products.orders.domain.dto.OrderRequestDto;
import org.ebuitrago.smartorderaiproject.msvc.products.orders.domain.dto.ProductItemDto;
import org.ebuitrago.smartorderaiproject.msvc.products.orders.domain.dto.ProductListItemDto;
import org.ebuitrago.smartorderaiproject.msvc.products.orders.respositories.IProductOrderRepository;
import org.ebuitrago.smartorderaiproject.msvc.products.orders.services.usacase.IProductOrderUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Servicio que implementa las operaciones del caso de uso para la gestión
 * de productos asociados a una orden.
 * Coordina el acceso al repositorio, valida la existencia de órdenes,
 * calcula totales y comunica actualizaciones al microservicio de inventario.
 */
@RequiredArgsConstructor
@Service
public class ProductOrderServiceImpl implements IProductOrderUseCase {

    private final IProductOrderRepository productOrderRepo;
    private final InventoryClientRest inventoryClientRest;
    private final OrderClientRest orderClientRest;

    /**
     * Obtiene todos los registros de productos-orden.
     *
     * @return lista completa de ProductOrderEntity
     */
    @Transactional(readOnly = true)
    @Override
    public List<ProductOrderEntity> getAll() {
        return productOrderRepo.getAll();
    }

    /**
     * Obtiene un registro por ID de orden.
     *
     * @param orderId identificador de la orden
     * @return entidad encontrada
     * @throws RuntimeException si no existe
     */
    @Transactional(readOnly = true)
    @Override
    public Optional<ProductOrderEntity> getByOrderId(Integer orderId) {
        Optional<ProductOrderEntity> idDb = productOrderRepo.getByOrderId(orderId);

        if (idDb.isEmpty()) {
            throw new RuntimeException("No existe ninguna factura por ese id");
        }
        return idDb;
    }

    /**
     * Registra una lista de productos asociados a una orden.
     * También actualiza el stock en el microservicio de inventario.
     *
     * @param requestDto datos de la orden y su lista de productos
     * @return lista de entidades guardadas
     * @throws RuntimeException si la orden no existe o falla un servicio externo
     */
    @Transactional
    @Override
    public List<ProductOrderEntity> save(ProductListItemDto requestDto) {


        Optional<OrderRequestDto> order = orderClientRest.getById(requestDto.getOrderId());
        if (order.isEmpty()) {
            throw new RuntimeException("No existe ninguna factura por ese id, falló el Rest");
        }

        List<ProductOrderEntity> savedItems = new ArrayList<>();



        for (ProductItemDto dto : requestDto.getItems()) {

            BigDecimal unitPrice = inventoryClientRest.getUnitPriceByProductCode(dto.getProductCode());

            ProductOrderEntity productOrder = new ProductOrderEntity();
            productOrder.setOrderId(requestDto.getOrderId());
            productOrder.setProductCode(dto.getProductCode());
            productOrder.setQuantity(dto.getQuantity());
            productOrder.setUnitPrice(unitPrice);
            productOrder.setSubtotal(calculateTotal(productOrder));
            productOrder.setTotalPrice(calculateTotal(productOrder));

            productOrderRepo.save(productOrder);
            savedItems.add(productOrder);

            try {
                inventoryClientRest.decrementStock(productOrder.getProductCode(), productOrder.getQuantity());
            } catch (FeignException e) {
                throw new RuntimeException("Error al actualizar inventario: " + e.contentUTF8());
            }
        }

        return savedItems;
    }

    /**
     * Elimina un registro basado en el ID de la orden.
     *
     * @param orderId identificador de la orden
     * @return true si la eliminación fue exitosa
     * @throws RuntimeException si no existe la orden
     */
    @Transactional
    @Override
    public Boolean deleteByOrderId(Integer orderId) {

        Optional<ProductOrderEntity> db = productOrderRepo.getByOrderId(orderId);
        if (db.isEmpty()) {
            throw new RuntimeException("No existe ninguna factura por ese id");
        }

        productOrderRepo.deleteByOrderId(orderId);
        return true;
    }

    /**
     * Calcula el total de un producto según cantidad y precio unitario.
     *
     * @param productOrder entidad con cantidad y precio
     * @return total calculado
     */
    public BigDecimal calculateTotal(ProductOrderEntity productOrder) {
        return BigDecimal.valueOf(productOrder.getQuantity())
                .multiply(productOrder.getUnitPrice());
    }
}
