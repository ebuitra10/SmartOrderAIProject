package org.ebuitrago.smartorderaiproject.msvc.roles.repositories;

import lombok.RequiredArgsConstructor;
import org.ebuitrago.smartorderaiproject.msvc.roles.domain.RoleEntity;
import org.ebuitrago.smartorderaiproject.msvc.roles.repositories.jparepository.RoleJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del repositorio {@link IRoleRepository}.
 * Delegada a {@link RoleJpaRepository} para realizar operaciones
 * de persistencia sobre la entidad {@link RoleEntity}.
 */
@RequiredArgsConstructor
@Repository
public class RoleRepositoryImpl implements IRoleRepository{

    private final RoleJpaRepository roleJpaRepository;


    @Override
    public List<RoleEntity> getAll() {
        return roleJpaRepository.findAll();
    }

    @Override
    public Optional<RoleEntity> getById(Integer id) {
        return roleJpaRepository.findById(id);
    }

    @Override
    public RoleEntity save(RoleEntity roleEntity) {
        return roleJpaRepository.save(roleEntity);
    }

    @Override
    public void deleteById(Integer id) {
        roleJpaRepository.deleteById(id);
    }

}
