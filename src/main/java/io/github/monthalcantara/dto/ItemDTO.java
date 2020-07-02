package io.github.monthalcantara.dto;

import io.github.monthalcantara.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    private Integer product;

    private Integer quantity;


}
