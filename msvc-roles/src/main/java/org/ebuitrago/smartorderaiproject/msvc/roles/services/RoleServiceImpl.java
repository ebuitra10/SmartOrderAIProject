package org.ebuitrago.smartorderaiproject.msvc.roles.services;


import lombok.RequiredArgsConstructor;
import org.ebuitrago.smartorderaiproject.msvc.roles.domain.RoleEntity;
import org.ebuitrago.smartorderaiproject.msvc.roles.repositories.IRoleRepository;
import org.ebuitrago.smartorderaiproject.msvc.roles.services.usecase.IRoleUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Servicio que implementa las operaciones de negocio para la gestión de roles.
 * Actúa como intermediario entre el controlador y el repositorio.
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleUseCase {

    private final IRoleRepository roleRepository;

    /**
     * Obtiene todos los roles registrados.
     *
     * @return lista de {@link RoleEntity}
     */
    @Transactional(readOnly = true)
    @Override
    public List<RoleEntity> getAll() {
        return roleRepository.getAll();
    }

    /**
     * Busca un rol por su identificador.
     *
     * @param id identificador del rol
     * @return Optional con el rol encontrado
     * @throws RuntimeException si no existe
     */
    @Transactional(readOnly = true)
    @Override
    public Optional<RoleEntity> getById(Integer id) {

        Optional<RoleEntity> roleEntity = roleRepository.getById(id);

        if (roleEntity.isEmpty()){
            throw new RuntimeException("Role no encontrado con ese id");
        }

        return roleEntity;
    }

    /**
     * Crea y almacena un nuevo rol.
     *
     * @param roleEntity entidad a guardar
     * @return rol almacenado
     */
    @Transactional
    @Override
    public RoleEntity save(RoleEntity roleEntity) {
        return roleRepository.save(roleEntity);
    }

    /**
     * Actualiza un rol existente.
     *
     * @param roleEntity datos actualizados del rol
     * @return rol modificado
     * @throws RuntimeException si el rol no existe
     */
    @Transactional
    @Override
    public RoleEntity update(RoleEntity roleEntity) {

        Optional<RoleEntity> roleEntityOptional = roleRepository.getById(roleEntity.getId());

        if (roleEntityOptional.isEmpty()){
            throw new RuntimeException("Role no encontrado con ese id, no es posible actualizar");
        }

        RoleEntity roleEntityUpdate = roleEntityOptional.get();
        roleEntityUpdate.setRoleName(roleEntity.getRoleName());
        roleEntityUpdate.setRoleDescription(roleEntity.getRoleDescription());

        return roleRepository.save(roleEntityUpdate);
    }

    /**
     * Elimina un rol por su identificador.
     *
     * @param id identificador del rol
     * @return true si la eliminación fue exitosa
     * @throws RuntimeException si el rol no existe
     */
    @Transactional
    @Override
    public Boolean deleteById(Integer id) {

        Optional<RoleEntity> roleEntityOptional = roleRepository.getById(id);
        if (roleEntityOptional.isEmpty()){
            throw new RuntimeException("Role no encontrado con ese id, no es posible eliminar");
        }

        roleRepository.deleteById(id);

        return true;
    }
}

