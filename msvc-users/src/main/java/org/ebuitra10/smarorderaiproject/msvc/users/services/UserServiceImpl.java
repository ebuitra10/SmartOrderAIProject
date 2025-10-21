package org.ebuitra10.smarorderaiproject.msvc.users.services;

import lombok.RequiredArgsConstructor;
import org.ebuitra10.smarorderaiproject.msvc.users.domain.UserEntity;
import org.ebuitra10.smarorderaiproject.msvc.users.repositories.IUserRepository;
import org.ebuitra10.smarorderaiproject.msvc.users.repositories.jpaRepository.IUserJpaRespository;
import org.ebuitra10.smarorderaiproject.msvc.users.services.usecase.IUserServiceUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements IUserServiceUseCase {

    private final IUserRepository userRepository;



    @Transactional(readOnly = true)
    @Override
    public List<UserEntity> getAll() {
        return userRepository.getAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<UserEntity> getById(Integer id) {
        return userRepository.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<UserEntity> getByUserName(String userName) {

       return userRepository.getByUserName(userName);

    }

    @Transactional
    @Override
    public UserEntity save(UserEntity saveUser) {
        Optional<UserEntity> o = userRepository.getById(saveUser.getId());
        if(o.isPresent()){
            throw (new RuntimeException("Ya existe un usuario con ese numero de documento"));
        }

        return userRepository.save(saveUser);
    }

    @Transactional
    @Override
    public UserEntity update(UserEntity userUpdate) {

        Optional<UserEntity> userId = userRepository.getById(userUpdate.getId());
        if (!userId.isPresent()){
            throw (new RuntimeException("No existe un usuario con ese numero de documento"));
        }

        UserEntity newUser = userId.get();
        newUser.setName(userUpdate.getName());
        newUser.setEmail(userUpdate.getEmail());
        newUser.setUserName(userUpdate.getUserName());
        newUser.setPassword(userUpdate.getPassword());

        return userRepository.save(newUser);
    }

    @Transactional
    @Override
    public boolean deleteById(Integer id) {
        Optional<UserEntity> user = userRepository.getById(id);
        if (!user.isPresent()){
            throw (new RuntimeException("El numero de documento ingresado no existe"));
        }
        userRepository.deleteById(id);
        return true;
    }
}
