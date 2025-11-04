package org.ebuitrago.smartOrderAIProject.msvc.orders.controllers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ebuitrago.smartOrderAIProject.msvc.orders.domain.OrderEntity;
import org.ebuitrago.smartOrderAIProject.msvc.orders.services.usecase.IOrderServiceUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controlador REST para gestionar las operaciones relacionadas con las órdenes.
 * Expone los endpoints necesarios para consultar, crear, actualizar y eliminar
 * órdenes en el sistema.
 *
 * <p>Este controlador actúa como la capa de entrada de datos al sistema,
 * delegando la lógica de negocio al servicio {@link IOrderServiceUseCase}.
 * </p>
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final IOrderServiceUseCase iOrderServiceUseCase;

    /**
     * Obtiene la lista de todas las órdenes registradas en el sistema.
     *
     * @return una respuesta HTTP con el listado de {@link OrderEntity} y código 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAll() {
        return ResponseEntity.ok(iOrderServiceUseCase.getAll());
    }

    /**
     * Obtiene una orden específica dado su identificador único.
     *
     * @param id identificador único de la orden a buscar.
     * @return la orden encontrada con código 200 OK o un mensaje de error si no existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@Valid @PathVariable Integer id) {
        try {
            return ResponseEntity.ok(iOrderServiceUseCase.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Obtiene la lista de órdenes asociadas a una fecha específica.
     *
     * @param date fecha en formato ISO-8601 (ej: "2025-10-19").
     * @return lista de órdenes encontradas o un mensaje de error si no hay registros.
     */
    @GetMapping("/date/{date}")
    public ResponseEntity<?> getByDate(@Valid @PathVariable LocalDate date) {
        try {
            return ResponseEntity.ok(iOrderServiceUseCase.getByDate(date));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Registra una nueva orden en el sistema.
     *
     * @param orderEntity objeto {@link OrderEntity} con los datos de la orden a crear.
     * @param result contiene el resultado de la validación del objeto recibido.
     * @return la orden creada con código 201 CREATED o errores de validación.
     */
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody OrderEntity orderEntity, BindingResult result) {
        if (result.hasErrors()) {
            return validar(result);
        }

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(iOrderServiceUseCase.save(orderEntity));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Actualiza una orden existente.
     *
     * @param orderEntity objeto {@link OrderEntity} con los nuevos datos de la orden.
     * @param result contiene el resultado de la validación del objeto actualizado.
     * @return la orden actualizada con código de estado 201 CREATED o errores de validación.
     */
    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody OrderEntity orderEntity, BindingResult result) {
        if (result.hasErrors()) {
            return validar(result);
        }

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(iOrderServiceUseCase.update(orderEntity));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Elimina una orden del sistema dado su identificador único.
     *
     * @param id identificador de la orden a eliminar.
     * @return {@code true} si la orden fue eliminada correctamente o un mensaje de error.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@Valid @PathVariable Integer id) {

        try {
            return ResponseEntity.ok(iOrderServiceUseCase.deleteById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Método de utilidad para construir un mapa de errores de validación desde un {@link BindingResult}.
     *
     * @param result resultado de las validaciones aplicadas al objeto recibido.
     * @return una respuesta HTTP con un mapa de errores y el código 400 BAD REQUEST.
     */
    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(error ->
                errores.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
    }
}

