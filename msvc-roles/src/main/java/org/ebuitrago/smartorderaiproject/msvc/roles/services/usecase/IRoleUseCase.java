package org.ebuitrago.smartorderaiproject.msvc.roles.services.usecase;

import org.ebuitrago.smartorderaiproject.msvc.roles.domain.RoleEntity;

import java.util.List;
import java.util.Optional;

/**
 * Caso de uso para la gestión de roles dentro del sistema.
 * Define las operaciones disponibles para consulta, creación,
 * actualización y eliminación de roles.
 */
public interface IRoleUseCase {

    /**
     * Obtiene todos los roles registrados.
     *
     * @return lista de {@link RoleEntity}
     */
    List<RoleEntity> getAll();

    /**
     * Busca un rol por su identificador.
     *
     * @param id identificador único del rol
     * @return Optional con el rol encontrado, vacío si no existe
     */
    Optional<RoleEntity> getById(Integer id);

    /**
     * Crea y guarda un nuevo rol.
     *
     * @param roleEntity entidad del rol a registrar
     * @return rol almacenado con su ID generado
     */
    RoleEntity save(RoleEntity roleEntity);

    /**
     * Actualiza un rol existente.
     *
     * @param roleEntity entidad con los datos a actualizar
     * @return rol actualizado
     */
    RoleEntity update(RoleEntity roleEntity);

    /**
     * Elimina un rol por su identificador.
     *
     * @param id identificador único del rol
     * @return true si la eliminación fue exitosa
     */
    Boolean deleteById(Integer id);
}

