package io.github.monthalcantara.dto.response;

import io.github.monthalcantara.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    private Product products;

    private Integer quantity;
}
