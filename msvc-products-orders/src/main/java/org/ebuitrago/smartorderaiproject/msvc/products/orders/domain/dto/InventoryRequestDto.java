package org.ebuitrago.smartorderaiproject.msvc.products.orders.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class InventoryRequestDto {

    private String productCode;

    private Integer quantity;
}
