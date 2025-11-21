package org.ebuitrago.smartorderaiproject.msvc.products.domain.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryRequest {

    private String productCode;

    private Integer initialStock;

    private BigDecimal unitPrice;

}
