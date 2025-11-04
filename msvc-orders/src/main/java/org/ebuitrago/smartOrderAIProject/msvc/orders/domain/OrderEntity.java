package org.ebuitrago.smartOrderAIProject.msvc.orders.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Entidad que representa una orden dentro del sistema. Esta clase define la estructura
 * de la tabla "orders" en la base de datos y aplica restricciones de validación
 * para garantizar la integridad de los datos.
 */
@Data
@Entity
@Table(name = "orders")
public class OrderEntity {

    /**
     * Identificador único de la orden.
     * Es generado automáticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Identificador del usuario que realizó la orden.
     * Corresponde a una llave foránea externa (ID del usuario).
     */
    @Column(name = "id_document_user", nullable = false)
    private Integer userId;

    /**
     * Fecha en que se realizó la orden.
     * No puede ser nula.
     */
    @NotNull(message = "La fecha de la orden es obligatoria")
    private LocalDate date;

    /**
     * Nombre de la tienda donde se realizó la orden.
     * No puede estar vacío ni ser solo espacios en blanco.
     */
    @NotBlank(message = "El nombre de la tienda no puede estar vacío")
    private String store;

    /**
     * Precio total de la orden.
     * Es obligatorio y no puede ser nulo.
     */
    @NotNull(message = "El precio total no puede ser nulo")
    @Column(name = "total_price")
    private BigDecimal totalPrice;

    /**
     * Método de pago de la orden (ejemplo: "tarjeta", "efectivo").
     * No puede estar vacío ni ser solo espacios en blanco.
     */
    @NotBlank(message = "El método de pago no puede estar vacío")
    @Column(name = "payment_method")
    private String paymentMethod;

    /**
     * Lista de órdenes relacionadas. Este atributo es transitorio, por lo que
     * no se guarda en la base de datos. Se usa en procesos internos de negocio,
     * como reportes o agrupaciones.
     */
    @Transient
    private List<OrderEntity> orders;
}
