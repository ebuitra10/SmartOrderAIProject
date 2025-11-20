package org.ebuitrago.smartorderaiproject.msvc.inventory.controllers;



import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ebuitrago.smartorderaiproject.msvc.inventory.domain.dto.InventoryResponseDto;
import org.ebuitrago.smartorderaiproject.msvc.inventory.services.useCase.InventoryUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * Controlador REST que gestiona las operaciones del inventario.
 * <p>
 * Expone endpoints para consultar, registrar, actualizar y modificar el stock de productos.
 * Utiliza {@link InventoryUseCase} como capa de negocio.
 * </p>
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryUseCase inventoryUseCase;

    /**
     * Obtiene todos los registros de inventario.
     *
     * @return respuesta con la lista de inventarios y código HTTP 200.
     */
    @GetMapping
    public ResponseEntity<?> getAll() {

        return ResponseEntity.ok(inventoryUseCase.getAll());

    }

    /**
     * Obtiene los registros de inventario por código de producto.
     *
     * @param productCode código único del producto.
     * @return respuesta con la lista de inventarios o mensaje de error.
     */
    @GetMapping("/{productCode}")
    ResponseEntity<?> getAllByProductCode(@PathVariable String productCode) {

        try {
            return ResponseEntity.ok(inventoryUseCase.getAllByProductCode(productCode));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    /**
     * Obtiene la cantidad de stock disponible de un producto.
     *
     * @param productCode código único del producto.
     * @return respuesta con el stock disponible o mensaje de error.
     */
    @GetMapping("/stock-by-code/{productCode}")
    ResponseEntity<?> getStockByProductCode(@PathVariable String productCode) {

        try {
            return ResponseEntity.ok(inventoryUseCase.getStockByProductCode(productCode));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    /**
     * Registra o actualiza un inventario.
     *
     * @param inventory objeto con los datos del inventario.
     * @param result    resultado de la validación del cuerpo de la solicitud.
     * @return respuesta con el inventario creado/actualizado o errores de validación.
     */
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody InventoryResponseDto inventory, BindingResult result) {

        if (result.hasErrors()) {
            validar(result);
        }

        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(inventoryUseCase.createOrUpdateInvetory(inventory));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/{productCode}/decrement-stock")
    public ResponseEntity<?> decrementStock(@PathVariable String productCode, @RequestParam Integer quantity) {


        try {
            return ResponseEntity.ok(inventoryUseCase.decrementInventory(productCode, quantity));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


    /**
     * Reduce en una unidad el stock de un producto por su código.
     *
     * @param productCode código único del producto.
     * @return respuesta con el resultado de la operación o mensaje de error.
     */
    @DeleteMapping("/delete-inventory/{productCode}")
    public ResponseEntity<?> delete(@PathVariable String productCode) {

        try {
            return ResponseEntity.ok(inventoryUseCase.deleteByProductCode(productCode));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    /**
     * Maneja los errores de validación de los datos de entrada.
     *
     * @param result resultado con los errores de validación.
     * @return respuesta con los campos y mensajes de error.
     */
    private static ResponseEntity<?> validar(BindingResult result) {

        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(error ->
                errors.put(error.getField(), "El campo: " + error.getField() + " " + error.getDefaultMessage())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}

