package org.ebuitra10.smarorderaiproject.msvc.users.services.usecase;


import org.ebuitra10.smarorderaiproject.msvc.users.domain.UserEntity;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define los casos de uso relacionados con la gestión de usuarios.
 *
 * Será implementada por el servicio que contenga la lógica de negocio,
 * actuando como una capa intermedia entre el controlador y el repositorio.
 */
public interface IUserServiceUseCase {

    /**
     * Obtiene la lista completa de usuarios registrados.
     *
     * @return una lista con todos los usuarios existentes.
     */
    List<UserEntity> getAll();

    /**
     * Busca un usuario por su numero de cedula.
     *
     * @param id Numero de documento del usuario.
     * @return un {@link Optional} que puede contener el usuario si existe.
     */
    Optional<UserEntity> getById(Integer id);

    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @param userName Nombre de usuario a buscar.
     * @return un {@link Optional} que puede contener el usuario si existe.
     */
    Optional<UserEntity> getByUserName(String userName);

    /**
     * Guarda un nuevo usuario en el sistema.
     *
     * @param saveUser usuario a guardar.
     * @return la entidad de usuario guardada.
     */
    UserEntity save(UserEntity saveUser);

    /**
     * Actualiza la información de un usuario existente.
     *
     * @param userUpdate usuario con los nuevos datos.
     * @return la entidad actualizada.
     */
    UserEntity update(UserEntity userUpdate);

    /**
     * Elimina un usuario por su identificador único.
     *
     * @param id identificador del usuario a eliminar.
     * @return {@code true} si el usuario fue eliminado correctamente,
     *         {@code false} en caso contrario.
     */
    boolean deleteById(Integer id);


}