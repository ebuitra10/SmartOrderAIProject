package org.ebuitrago.smartorderaiproject.msvc.products.orders.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
/**
 * Entidad que representa la relación entre productos y órdenes.
 *
 * Contiene información del producto dentro de una orden, como la cantidad,
 * el precio unitario y el subtotal correspondiente.
 */
@Data
@Entity
@Table(name = "products_orders")
public class ProductOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_id", nullable = false)
    private Integer productid;

    @Column(name = "order_id", nullable = false)
    private Integer orderId;

    @NotNull
    private Integer quantity;

    @NotNull
    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @NotNull
    private BigDecimal subtotal;
}

