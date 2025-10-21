package org.ebuitra10.smarorderaiproject.msvc.users.repositories;

import org.ebuitra10.smarorderaiproject.msvc.users.domain.UserEntity;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {

    List<UserEntity> getAll();

    Optional<UserEntity> getById(Integer id);

    Optional<UserEntity> getByUserName(String userName);

    UserEntity save(UserEntity userSave);

    void deleteById(Integer id);

}
