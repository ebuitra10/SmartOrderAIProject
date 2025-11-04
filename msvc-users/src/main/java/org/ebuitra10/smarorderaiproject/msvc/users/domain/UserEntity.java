package org.ebuitra10.smarorderaiproject.msvc.users.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;



import java.util.List;

/**
 * Clase que contiene entidades y los atributos de la tabla "users" de la base de datos.
 */

@Data
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @Column(name = "id_document",  nullable = false, unique = true)
    private  Integer id;

    @NotEmpty
    private String name;

    @NotEmpty
    @Column(name = "user_name")
    private String userName;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String password;

    @Transient
    List<UserEntity> userEntityList;



}
