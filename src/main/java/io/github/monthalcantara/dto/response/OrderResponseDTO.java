package io.github.monthalcantara.dto.response;

import io.github.monthalcantara.model.Client;
import io.github.monthalcantara.model.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {

    private Client client;

    private LocalDate date;

    private BigDecimal total;

    private List<Item> items;

}
