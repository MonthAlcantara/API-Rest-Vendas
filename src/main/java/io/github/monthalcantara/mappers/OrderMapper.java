package io.github.monthalcantara.mappers;

import io.github.monthalcantara.dto.request.OrderDTO;
import io.github.monthalcantara.dto.response.OrderResponseDTO;
import io.github.monthalcantara.model.OrderItem;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

//@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDTO toOrderDTO(OrderItem orderItem);

    OrderResponseDTO toOrderResponseDTO(OrderItem orderItem);

    OrderItem orderDTOToOrderItem(OrderDTO orderDTO);

    OrderItem orderResponseDTOToOrderItem(OrderResponseDTO orderResponseDTO);

}
