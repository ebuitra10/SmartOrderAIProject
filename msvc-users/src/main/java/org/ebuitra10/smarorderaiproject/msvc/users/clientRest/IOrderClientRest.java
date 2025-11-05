package org.ebuitra10.smarorderaiproject.msvc.users.clientRest;


import jakarta.validation.Valid;
import org.ebuitra10.smarorderaiproject.msvc.users.domain.order.OrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

/**
 * Cliente Feign para comunicarse con el microservicio de órdenes (msvc-orders).
 * <p>
 * Esta interfaz permite realizar solicitudes HTTP al microservicio encargado de gestionar
 * las órdenes, utilizando Feign como cliente declarativo.
 * </p>
 */
@FeignClient(name = "msvc-orders", url = "http://localhost:8002")
public interface IOrderClientRest {

    /**
     * Obtiene una lista de órdenes asociadas a un usuario específico.
     *
     * @param userId Identificador (número de documento) del usuario cuyas
     *               órdenes se desean consultar.
     * @return Una lista de objetos {@link OrderResponse} que contienen la información
     *         resumida de las órdenes realizadas por el usuario.
     */
    @GetMapping("/orders-by-user/{userId}")
    List<OrderResponse> getOrdersByUser(@Valid @PathVariable Integer userId);

}
