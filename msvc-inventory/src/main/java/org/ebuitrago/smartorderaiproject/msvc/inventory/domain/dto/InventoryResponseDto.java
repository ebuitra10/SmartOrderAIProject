package org.ebuitrago.smartorderaiproject.msvc.inventory.domain.dto;


import lombok.*;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponseDto {

    private String productCode;

    private Integer initialStock;

}
