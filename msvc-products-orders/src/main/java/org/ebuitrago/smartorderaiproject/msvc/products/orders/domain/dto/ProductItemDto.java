package org.ebuitrago.smartorderaiproject.msvc.products.orders.domain.dto;

import lombok.*;

import java.math.BigDecimal;

/**
 * DTO que representa un ítem dentro de una orden,
 * incluyendo código del producto, cantidad y precio unitario.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductItemDto {

    private String productCode;


    private Integer quantity;


    private BigDecimal unitPrice;
}

