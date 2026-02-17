package org.ebuitrago.smartorderaiproject.msvc.products.orders.clientrest;


import org.ebuitrago.smartorderaiproject.msvc.products.orders.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * Cliente Feign para comunicarse con el microservicio de inventario.
 * Permite actualizar el stock de un producto reduciendo la cantidad disponible.
 */
@FeignClient(name = "msvc-inventory",
             url = "http://localhost:8004/api/inventory",
             configuration = FeignConfig.class)
public interface InventoryClientRest {

    /**
     * Decrementa el stock de un producto según la cantidad indicada.
     *
     * @param productCode código del producto cuyo stock será reducido
     * @param quantity    cantidad a descontar del inventario
     */
    @PutMapping("/inventory/{productCode}/decrement-stock")
    void decrementStock(@PathVariable String productCode, @RequestParam Integer quantity);


    @GetMapping("/inventory/unit-price-by-product/{productCode}")
    BigDecimal getUnitPriceByProductCode(@PathVariable String productCode);

}

