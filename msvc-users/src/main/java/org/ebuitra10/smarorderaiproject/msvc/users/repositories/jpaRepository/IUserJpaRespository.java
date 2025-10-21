package org.ebuitra10.smarorderaiproject.msvc.users.repositories.jpaRepository;

import org.ebuitra10.smarorderaiproject.msvc.users.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserJpaRespository extends JpaRepository<UserEntity,Integer> {

    UserEntity findByUserName(String userName);



}
