package org.ebuitrago.smartorderaiproject.msvc.products.orders.clientrest;

import org.ebuitrago.smartorderaiproject.msvc.products.orders.domain.dto.OrderRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

/**
 * Cliente Feign para comunicarse con el microservicio de órdenes.
 * Permite consultar información de una orden por su identificador.
 */
@FeignClient(name = "msvc-orders", url = "http://localhost:8002/api")
public interface OrderClientRest {

    /**
     * Obtiene una orden según su ID.
     *
     * @param id identificador de la orden
     * @return Optional con los datos de la orden si existe
     */
    @GetMapping("/orders/{id}")
    Optional<OrderRequestDto> getById(@PathVariable Integer id);
}

