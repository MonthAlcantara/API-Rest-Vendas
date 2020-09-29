package io.github.monthalcantara.mappers;

import io.github.monthalcantara.dto.request.ClientDTO;
import io.github.monthalcantara.dto.request.OrderDTO;
import io.github.monthalcantara.dto.response.OrderResponseDTO;
import io.github.monthalcantara.model.OrderItem;
import org.springframework.data.domain.Page;

public interface OrderMapper {
    OrderDTO clientToClientDTO(OrderItem client);

    OrderResponseDTO clientToClientResponseDTO(OrderItem client);

    OrderItem clientDTOToClient(ClientDTO clientDTO);

    OrderItem clientResponseDTOToClient(OrderResponseDTO clientResponseDTO);

    Page<OrderResponseDTO> toPageResponseDTO(Page<OrderItem> orders);
}
