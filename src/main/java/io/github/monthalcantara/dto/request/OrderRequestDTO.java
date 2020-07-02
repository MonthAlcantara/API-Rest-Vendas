package io.github.monthalcantara.dto.request;

import io.github.monthalcantara.model.Client;
import io.github.monthalcantara.model.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {

    private Client client;

    private List<Item> items;

}
