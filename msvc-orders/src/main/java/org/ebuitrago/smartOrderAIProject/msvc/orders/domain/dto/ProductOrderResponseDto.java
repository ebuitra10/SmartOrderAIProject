package org.ebuitrago.smartOrderAIProject.msvc.orders.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase que se encarga de solicitar el id de la orden
 * para enviarla al products-orders
 */
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrderResponseDto {

    private Integer orderId;

}
