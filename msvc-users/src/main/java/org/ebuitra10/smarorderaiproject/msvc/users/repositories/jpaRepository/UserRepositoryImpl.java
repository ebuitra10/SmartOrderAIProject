package org.ebuitra10.smarorderaiproject.msvc.users.repositories.jpaRepository;

import lombok.RequiredArgsConstructor;
import org.ebuitra10.smarorderaiproject.msvc.users.domain.UserEntity;
import org.ebuitra10.smarorderaiproject.msvc.users.repositories.IUserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
}
