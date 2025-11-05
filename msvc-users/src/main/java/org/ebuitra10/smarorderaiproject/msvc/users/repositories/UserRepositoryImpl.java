package org.ebuitra10.smarorderaiproject.msvc.users.repositories;

import lombok.RequiredArgsConstructor;
import org.ebuitra10.smarorderaiproject.msvc.users.domain.UserEntity;
import org.ebuitra10.smarorderaiproject.msvc.users.repositories.jpaRepository.IUserJpaRespository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Clase que funcionara como repositorio para interactuar con la base de datos.
 * Implementa la interfaz {@link IUserRepository} y deja las operaciones al repositorio
 * JPA correspondiente.
 */
@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements IUserRepository {

    private final IUserJpaRespository userJpaRespository;

    @Override
    public List<UserEntity> getAll() {
        return userJpaRespository.findAll();
    }

    @Override
    public Optional<UserEntity> getById(Integer id) {
        return userJpaRespository.findById(id);
    }

    @Override
    public Optional<UserEntity> getByUserName(String userName) {
        return Optional.of(userJpaRespository.findByUserName(userName));
    }

    @Override
    public UserEntity save(UserEntity userSave) {
        return userJpaRespository.save(userSave);
    }

    @Override
    public void deleteById(Integer id) {
        userJpaRespository.deleteById(id);
    }

    @Override
    public List<UserEntity> getOrdersByUser(Iterable<Integer> ids) {
        return userJpaRespository.findAllById(ids);
    }
}
