package org.ebuitrago.smartorderaiproject.msvc.inventory.domain.dto;


import lombok.*;

import java.math.BigDecimal;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponseDto {

    private String productCode;

    private Integer initialStock;

    private BigDecimal unitPrice;

}
