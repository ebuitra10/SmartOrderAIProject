package org.ebuitrago.smartorderaiproject.msvc.roles.repositories;

import org.ebuitrago.smartorderaiproject.msvc.roles.domain.RoleEntity;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio de dominio para gestionar operaciones CRUD
 * relacionadas con la entidad {@link RoleEntity}.
 */
public interface IRoleRepository {

    /**
     * Obtiene todos los roles registrados.
     * @return lista de {@link RoleEntity}
     */
    List<RoleEntity> getAll();

    /**
     * Busca un rol por su identificador.
     * @param id identificador del rol
     * @return Optional con el rol si existe
     */
    Optional<RoleEntity> getById(Integer id);

    /**
     * Persiste o actualiza un rol en el sistema.
     * @param roleEntity entidad a guardar
     * @return rol almacenado
     */
    RoleEntity save(RoleEntity roleEntity);

    /**
     * Elimina un rol según su identificador.
     * @param id identificador del rol a eliminar
     */
    void deleteById(Integer id);
}

