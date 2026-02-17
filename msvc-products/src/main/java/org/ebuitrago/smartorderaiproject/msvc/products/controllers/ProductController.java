package org.ebuitrago.smartorderaiproject.msvc.products.controllers;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.ebuitrago.smartorderaiproject.msvc.products.domain.ProductEntity;
import org.ebuitrago.smartorderaiproject.msvc.products.services.useCase.IProductUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Controlador REST para la gestión de productos.
 * <p>
 * Expone endpoints HTTP para realizar las operaciones CRUD
 * relacionadas con los productos.
 * </p>
 *
 * <p>Se apoya en la capa de servicio {@link IProductUseCase} para ejecutar
 * la lógica de negocio correspondiente.</p>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final IProductUseCase iProductUseCase;

    /**
     * Obtiene todos los productos registrados.
     *
     * @return Lista de productos (HTTP 200 OK)
     */
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(iProductUseCase.getAll());
    }

    @GetMapping("/debug")
    public Object debug(Authentication authentication) {
        return authentication.getAuthorities();
    }

    /**
     * Busca un producto por su identificador único.
     *
     * @param id ID del producto a consultar
     * @return El producto correspondiente (HTTP 200 OK) o error (HTTP 404 Not Found)
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable @Min(1) Integer id) {
        try {
            return ResponseEntity.ok(iProductUseCase.getById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Busca un producto por su código interno.
     *
     * @param productCode Código del producto a consultar
     * @return El producto correspondiente (HTTP 200 OK) o error (HTTP 404 Not Found)
     */
    @GetMapping("/product-code/{productCode}")
    public ResponseEntity<?> getByProductCode(@PathVariable String productCode) {
        try {
            return ResponseEntity.ok(iProductUseCase.getByProductCode(productCode));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Crea un nuevo producto.
     *
     * @param newProduct Datos del nuevo producto
     * @param result Resultado de las validaciones
     * @return El producto creado (HTTP 200 OK) o errores de validación/negocio (HTTP 400 Bad Request)
     */
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody ProductEntity newProduct, BindingResult result) {
        if (result.hasErrors()) {
            return validar(result);
        }

        try {
            return ResponseEntity.ok(iProductUseCase.save(newProduct));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Actualiza un producto existente.
     *
     * @param updateProduct Datos del producto a actualizar
     * @param result Resultado de las validaciones
     * @return El producto actualizado (HTTP 200 OK) o error (HTTP 400 Bad Request)
     */
    @PutMapping
    ResponseEntity<?> update(@Valid @RequestBody ProductEntity updateProduct, BindingResult result) {
        if (result.hasErrors()) {
            return validar(result);
        }

        try {
            return ResponseEntity.ok(iProductUseCase.update(updateProduct));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Elimina un producto mediante su identificador.
     *
     * @param id ID del producto a eliminar
     * @return true si la operación fue exitosa (HTTP 200 OK) o error (HTTP 400 Bad Request)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable @Min(1) Integer id) {
        try {
            return ResponseEntity.ok(iProductUseCase.deleteById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Procesa y formatea los errores de validación.
     *
     * @param result Resultado de la validación
     * @return Mapa con los errores encontrados
     */
    private static ResponseEntity<?> validar(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), "El campo: " + error.getField() + " " + error.getDefaultMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
