package io.github.monthalcantara.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemResponseDTO implements Serializable {

    private String description;

    private BigDecimal priceUnit;

    private Integer quantity;
}
