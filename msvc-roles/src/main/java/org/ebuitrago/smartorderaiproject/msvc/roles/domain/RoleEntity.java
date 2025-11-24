package org.ebuitrago.smartorderaiproject.msvc.roles.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Entidad JPA que representa un rol dentro del sistema,
 * utilizada para la gestión de autorización y permisos.
 */
@Entity
@Getter@Setter
@Table(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name",  nullable = false)
    private String roleName;

    @Column(name = "description",  nullable = false)
    private String roleDescription;

}
