package org.ebuitrago.smartorderaiproject.msvc.roles.controllers;

import lombok.RequiredArgsConstructor;
import org.ebuitrago.smartorderaiproject.msvc.roles.domain.RoleEntity;
import org.ebuitrago.smartorderaiproject.msvc.roles.services.usecase.IRoleUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * Controlador REST encargado de gestionar las operaciones relacionadas
 * con los roles del sistema. Expone endpoints para consultar, crear,
 * actualizar y eliminar roles.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {

    private final IRoleUseCase iRoleUseCase;

    /**
     * Obtiene la lista de todos los roles registrados.
     *
     * @return ResponseEntity con la lista de roles y estado HTTP 200
     */
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(iRoleUseCase.getAll());
    }

    /**
     * Busca un rol según su identificador.
     *
     * @param id identificador único del rol
     * @return ResponseEntity con el rol encontrado y estado HTTP 200,
     *         o mensaje de error con estado HTTP 400 si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(iRoleUseCase.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Crea un nuevo rol en el sistema.
     *
     * @param role   entidad del rol recibida en el cuerpo de la solicitud
     * @param result resultado de validación del objeto recibido
     * @return ResponseEntity con el rol creado y estado HTTP 201,
     *         o mensajes de validación en estado HTTP 400
     */
    @PostMapping
    public ResponseEntity<?> save(@RequestBody RoleEntity role, BindingResult result) {

        if (result.hasErrors()) {
            return validar(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(iRoleUseCase.save(role));
    }

    /**
     * Actualiza un rol existente.
     *
     * @param role   entidad con los datos actualizados
     * @param result resultado de validación del objeto recibido
     * @return ResponseEntity con el rol actualizado y estado HTTP 200,
     *         o mensaje de error en estado HTTP 400
     */
    @PutMapping
    public ResponseEntity<?> update(@RequestBody RoleEntity role, BindingResult result) {

        if (result.hasErrors()) {
            return validar(result);
        }

        try {
            return ResponseEntity.ok(iRoleUseCase.update(role));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Elimina un rol según su identificador.
     *
     * @param id identificador único del rol
     * @return ResponseEntity con true si fue eliminado y estado HTTP 200,
     *         o mensaje de error en estado HTTP 400
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {

        try {
            return ResponseEntity.ok(iRoleUseCase.deleteById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Construye un mapa de errores de validación a partir del BindingResult.
     *
     * @param result resultado de la validación
     * @return ResponseEntity con los errores y estado HTTP 400
     */
    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {

        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(error ->
                errores.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
    }

}
