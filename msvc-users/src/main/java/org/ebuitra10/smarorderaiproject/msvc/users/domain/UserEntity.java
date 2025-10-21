package org.ebuitra10.smarorderaiproject.msvc.users.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


import java.util.List;


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


    List<UserEntity> userEntityList;

}
