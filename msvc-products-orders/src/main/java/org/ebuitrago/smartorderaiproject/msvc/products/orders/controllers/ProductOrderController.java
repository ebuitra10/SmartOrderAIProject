package org.ebuitrago.smartorderaiproject.msvc.products.orders.controllers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ebuitrago.smartorderaiproject.msvc.products.orders.domain.dto.ProductListItemDto;
import org.ebuitrago.smartorderaiproject.msvc.products.orders.services.usacase.IProductOrderUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Controlador REST para gestionar las operaciones relacionadas con
 * los productos asociados a una orden.
 * Ofrece endpoints para consultar, crear y eliminar registros,
 * delegando la lógica al caso de uso correspondiente.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/products-orders")
public class ProductOrderController {

    private final IProductOrderUseCase productOrderUseCase;

    /**
     * Obtiene todos los registros de productos-orden.
     *
     * @return lista completa de ProductOrderEntity
     */
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(productOrderUseCase.getAll());
    }

    /**
     * Obtiene un registro de ProductOrder por el ID de la orden.
     *
     * @param id identificador de la orden
     * @return registro encontrado o error si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getByOrderId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(productOrderUseCase.getByOrderId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Crea o actualiza un registro de ProductOrder.
     *
     * @param requestDto datos de la lista de productos a registrar
     * @param result validación del body
     * @return entidad creada o actualizada
     */
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody ProductListItemDto requestDto, BindingResult result) {

        if (result.hasErrors()) {
            validar(result);
        }

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(productOrderUseCase.save(requestDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Elimina un registro por ID de orden.
     *
     * @param id identificador de la orden
     * @return true si se eliminó, error si no existe
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {

        try {
            return ResponseEntity.ok(productOrderUseCase.deleteByOrderId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Maneja los errores de validación y construye un mapa de errores.
     *
     * @param result resultado de validación
     * @return mapa de errores en un ResponseEntity
     */
    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(error ->
                errores.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
    }
}

