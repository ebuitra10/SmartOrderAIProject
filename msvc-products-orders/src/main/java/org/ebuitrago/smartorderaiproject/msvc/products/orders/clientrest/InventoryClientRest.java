package org.ebuitrago.smartorderaiproject.msvc.products.orders.clientrest;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Cliente Feign para comunicarse con el microservicio de inventario.
 * Permite actualizar el stock de un producto reduciendo la cantidad disponible.
 */
@FeignClient(name = "msvc-inventory", url = "http://localhost:8004/api")
public interface InventoryClientRest {

    /**
     * Decrementa el stock de un producto según la cantidad indicada.
     *
     * @param productCode código del producto cuyo stock será reducido
     * @param quantity    cantidad a descontar del inventario
     */
    @PutMapping("/inventory/{productCode}/decrement-stock")
    void decrementStock(@PathVariable String productCode, @RequestParam Integer quantity);

}

