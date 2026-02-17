package org.ebuitrago.smartorderaiproject.msvc.products.clientRest;

import org.ebuitrago.smartorderaiproject.msvc.products.config.FeignConfig;
import org.ebuitrago.smartorderaiproject.msvc.products.domain.dto.InventoryRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * Cliente Feign para comunicarse con el microservicio de inventario.
 * <p>
 * Proporciona métodos para registrar, eliminar y consultar el stock de productos.
 * </p>
 */
@FeignClient(name = "msvc-inventory",
             url = "http://localhost:8004/api/inventory",
             configuration = FeignConfig.class)
public interface InventoryClientRest {

    /**
     * Registra un nuevo inventario o actualiza uno existente.
     *
     * @param inventoryRequest objeto con los datos del inventario.
     * @return el inventario registrado o actualizado.
     */
    @PostMapping
    InventoryRequest save(@RequestBody InventoryRequest inventoryRequest);

    /**
     * Elimina un registro de inventario según el código del producto.
     *
     * @param productCode código único del producto.
     * @return mensaje de confirmación.
     */
    @DeleteMapping("/delete-inventory/{productCode}")
    String delete(@PathVariable String productCode);

    /**
     * Obtiene la cantidad disponible en inventario de un producto específico.
     *
     * @param productCode código único del producto.
     * @return cantidad disponible en stock.
     */
    @GetMapping("/stock-by-code/{productCode}")
    Integer getStockByProductCode(@PathVariable String productCode);
}
