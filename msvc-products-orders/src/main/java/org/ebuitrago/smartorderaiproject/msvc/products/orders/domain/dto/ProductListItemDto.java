package org.ebuitrago.smartorderaiproject.msvc.products.orders.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductListItemDto {

    private Integer orderId;

    private List<ProductItemDto> items;

}
