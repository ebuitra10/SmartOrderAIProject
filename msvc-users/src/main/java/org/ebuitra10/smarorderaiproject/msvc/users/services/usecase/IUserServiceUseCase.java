package org.ebuitra10.smarorderaiproject.msvc.users.services.usecase;


import org.ebuitra10.smarorderaiproject.msvc.users.domain.UserEntity;

import java.util.List;
import java.util.Optional;

public interface IUserServiceUseCase {

    List<UserEntity> getAll();

    Optional<UserEntity> getById(Integer id);

    Optional<UserEntity> getByUserName(String userName);

    UserEntity save(UserEntity saveUser);

    UserEntity update(UserEntity userUpdate);

    boolean deleteById(Integer id);

}
