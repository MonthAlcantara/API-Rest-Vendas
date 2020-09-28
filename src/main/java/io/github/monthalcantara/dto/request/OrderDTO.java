package io.github.monthalcantara.dto.request;

import io.github.monthalcantara.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO implements Serializable {

    @NotNull(message = "Enter the client code")
    private Integer client;

    @NotNull(message = "Total field is required")
    private BigDecimal total;

    @NotEmptyList
    private List<ItemDTO> items;

}
