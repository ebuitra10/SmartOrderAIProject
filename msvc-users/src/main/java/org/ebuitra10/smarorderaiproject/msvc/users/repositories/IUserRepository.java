package org.ebuitra10.smarorderaiproject.msvc.users.repositories;

import org.ebuitra10.smarorderaiproject.msvc.users.domain.UserEntity;
import org.ebuitra10.smarorderaiproject.msvc.users.repositories.jpaRepository.UserRepositoryImpl;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define las operaciones de acceso en {@link UserRepositoryImpl} Y seran los metodos
 * que se implementaran para la interaccion con la base de datos.
 */
public interface IUserRepository {

    /**
     * Obtiene la lista completa de usuarios.
     * @return una lista con todos los usuarios registrados.
     */
    List<UserEntity> getAll();

    /**
     * Busca un usuario por su identificador único.
     * @param id identificador del usuario.
     * @return un {@link Optional} que puede contener el usuario si existe.
     */
    Optional<UserEntity> getById(Integer id);

    /**
     * Busca un usuario por su nombre de usuario.
     * @param userName nombre de usuario a buscar.
     * @return un {@link Optional} que puede contener el usuario si existe.
     */
    Optional<UserEntity> getByUserName(String userName);

    /**
     * Guarda un nuevo usuario o actualiza uno existente.
     * @param userSave usuario a guardar.
     * @return la entidad guardada.
     */
    UserEntity save(UserEntity userSave);

    /**
     * Elimina un usuario según su identificador.
     * @param id identificador del usuario a eliminar.
     */
    void deleteById(Integer id);

}
