package org.ebuitra10.smarorderaiproject.msvc.users.controllers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ebuitra10.smarorderaiproject.msvc.users.domain.UserEntity;
import org.ebuitra10.smarorderaiproject.msvc.users.services.usecase.IUserServiceUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Controlador REST encargado de gestionar las operaciones relacionadas con los usuarios.
 *
 * Expone los endpoints para crear, consultar, actualizar y eliminar usuarios,
 * comunicándose con la capa de servicio {@link IUserServiceUseCase}.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserServiceUseCase userServiceUseCase;

    /**
     * Obtiene la lista completa de usuarios.
     *
     * @return una respuesta HTTP con la lista de usuarios y el estado {@code 200 OK}.
     */
    @GetMapping
    public ResponseEntity<List<UserEntity>> getAll() {
        return ResponseEntity.ok(userServiceUseCase.getAll());
    }

    /**
     * Busca un usuario por su identificador único.
     *
     * @param id identificador del usuario.
     * @return una respuesta HTTP con el usuario encontrado y el estado {@code 200 OK}.
     *         Si no se encuentra, devuelve un estado {@code 404 Not Found}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(userServiceUseCase.getById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @param userName nombre de usuario.
     * @return una respuesta HTTP con el usuario encontrado y el estado {@code 200 OK}.
     *         Si no se encuentra, devuelve un estado {@code 404 Not Found}.
     */
    @GetMapping("/username/{userName}")
    public ResponseEntity<?> getByUserName(@PathVariable String userName) {
        try {
            return ResponseEntity.ok(userServiceUseCase.getByUserName(userName));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Crea un nuevo usuario.
     *
     * @param newUser objeto del usuario a crear, validado mediante anotaciones JSR-303.
     * @param result resultado de la validación de los campos.
     * @return una respuesta HTTP con el usuario creado y el estado {@code 201 Created}.
     *         Si hay errores de validación o de negocio, devuelve {@code 400 Bad Request}.
     */
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody UserEntity newUser, BindingResult result) {

        if (result.hasErrors()) {
            return validar(result);
        }

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userServiceUseCase.save(newUser));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Actualiza los datos de un usuario existente.
     *
     * @param updateUser objeto con los nuevos datos del usuario.
     * @param result resultado de la validación de los campos.
     * @return una respuesta HTTP con el usuario actualizado y el estado {@code 200 OK}.
     *         Si hay errores o el usuario no existe, devuelve {@code 400 Bad Request}.
     */
    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody UserEntity updateUser, BindingResult result) {

        if (result.hasErrors()) {
            return validar(result);
        }

        try {
            return ResponseEntity.status(HttpStatus.OK).body(userServiceUseCase.update(updateUser));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Elimina un usuario según su identificador.
     *
     * @param id identificador del usuario a eliminar.
     * @return una respuesta HTTP con el resultado de la eliminación.
     *         Devuelve {@code 200 OK} si se elimina correctamente o {@code 400 Bad Request} si no existe.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@Valid @PathVariable Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userServiceUseCase.deleteById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Método auxiliar para procesar los errores de validación de los campos del usuario.
     *
     * @param result resultado de la validación de los campos.
     * @return una respuesta HTTP {@code 400 Bad Request} con el detalle de los errores.
     */
    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(error ->
                errores.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
    }
}
