package org.ebuitrago.smartorderaiproject.msvc.products.domain.dto;

import lombok.*;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryRequest {

    private String productCode;

    private Integer initialStock;

}
