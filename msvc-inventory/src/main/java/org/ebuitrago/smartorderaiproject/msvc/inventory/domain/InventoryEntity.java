package org.ebuitrago.smartorderaiproject.msvc.inventory.domain;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * Representa la entidad de inventario que gestiona la cantidad disponible de productos.
 * Mapea la tabla {@code inventory} en la base de datos.
 */
@Data
@Entity
@Table(name = "inventory")
public class InventoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_code", nullable = false)
    private String productCode;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    @Transient
    List<InventoryEntity> inventoryEntityList;
}

