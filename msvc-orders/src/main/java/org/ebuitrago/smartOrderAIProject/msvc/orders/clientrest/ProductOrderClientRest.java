package org.ebuitrago.smartOrderAIProject.msvc.orders.clientrest;


import jakarta.validation.Valid;
import org.ebuitrago.smartOrderAIProject.msvc.orders.domain.dto.ProductOrderResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * Cliente Feign para comunicarse con el microservicio de gestión de
 * productos por orden (msvc-products-orders).
 *
 * Expone las operaciones remotas necesarias para crear y eliminar
 * registros de productos asociados a una orden.
 */
@FeignClient(name = "msvc-products-orders", url = "http://localhost:8005")
public interface ProductOrderClientRest {

    /**
     * Envía una solicitud al microservicio para registrar un producto dentro de una orden.
     *
     * @param responseDto datos del producto asociado a la orden.
     * @return el ID generado para el registro creado.
     */
    @PostMapping
    Integer save(@Valid @RequestBody ProductOrderResponseDto responseDto);

    /**
     * Solicita la eliminación de un registro de producto por orden mediante su ID.
     *
     * @param id identificador del registro.
     * @return true si la eliminación fue exitosa.
     */
    @DeleteMapping("/{id}")
    Boolean deleteById(@PathVariable Integer id);
}

