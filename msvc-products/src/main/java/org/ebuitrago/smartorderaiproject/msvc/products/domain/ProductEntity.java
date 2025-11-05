package org.ebuitrago.smartorderaiproject.msvc.products.domain;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entidad que representa la tabla "products" en la base de datos.
 * Modela la información de los productos y sirve como puente entre la aplicación y la capa de persistencia.
 */
@Data
@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String productName;

    @Column(name = "product_code", nullable = false)
    private String productCode;

    @NotNull
    private Integer stock;

    @NotNull
    private BigDecimal price;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "creation_date", nullable = false)
    private LocalDate createdDate;

    private String description;

    /**
     * Método ejecutado automáticamente antes de guardar la entidad por primera vez en la base de datos.
     * Asigna la fecha actual al atributo createdDate, garantizando que siempre exista una fecha de creación.
     */
    @PrePersist
    public void prePersist() {
        this.createdDate = LocalDate.now();
    }
}
