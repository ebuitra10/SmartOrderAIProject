package org.ebuitra10.smarorderaiproject.msvc.users.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.ebuitra10.smarorderaiproject.msvc.users.domain.UserEntity;
import org.ebuitra10.smarorderaiproject.msvc.users.repositories.IUserRepository;
import org.ebuitra10.smarorderaiproject.msvc.users.services.usecase.IUserServiceUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Servicio que se comunica con el repositorio para realizar las respectivas validaciones
 * de logica de negocio.
 */


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements IUserServiceUseCase {

    private final IUserRepository userRepository;


    /**
     * Metodo que busca todos los usuarios.
     * @return Retorna una lista con todos los usuarios.
     */

    @Transactional(readOnly = true)
    @Override
    public List<UserEntity> getAll() {
        return userRepository.getAll();
    }


    /**
     * Metodo que busca al usuario por su numero de documento.
     * * @param id Numero de documento a buscar.
     * @return El usuario completo.
     */
    @Transactional(readOnly = true)
    @Override
    public Optional<UserEntity> getById(Integer id) {

        Optional<UserEntity> user = userRepository.getById(id);

        if (user.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        return user;
    }

    /**
     * Metodo que busca un usuario por su nombre de usuario.
     * @param userName Nombre de Usuario a buscar.
     * @return El usuario completo.
     */
    @Transactional(readOnly = true)
    @Override
    public Optional<UserEntity> getByUserName(String userName) {

       Optional<UserEntity> user = userRepository.getByUserName(userName);

       if (user.isEmpty()) {
           throw new RuntimeException("No existe ese nombre de usuario");
       }

       return user;

    }

    /**
     * Metodo que guarda un usuario nuevo.
     * @param saveUser Usurio nuevo a guardar.
     * @return retorna el usuario guardado en el repositorio
     */
    @Transactional
    @Override
    public UserEntity save(UserEntity saveUser) {

        Optional<UserEntity> o = userRepository.getById(saveUser.getId());
        if(o.isPresent()){
            throw (new RuntimeException("Ya existe un usuario con ese numero de documento"));
        }

        return userRepository.save(saveUser);
    }


    /**
     * Metodo que actualiza un usuario ya existente.
     * @param userUpdate Usuario a buscar para eliminar.
     * @return El usuario actualizado.
     */
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


    /**
     * Metodo que elimina un usuario por su numero de documento.
     * @param id Numero de documento a buscar.
     * @return Falso o Verdadero dependiendo si existe o no el usuario a eliminar.
     */
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
