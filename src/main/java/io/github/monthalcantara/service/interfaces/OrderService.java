package io.github.monthalcantara.service.interfaces;

import io.github.monthalcantara.dto.request.OrderDTO;
import io.github.monthalcantara.dto.response.ItemResponseDTO;
import io.github.monthalcantara.dto.response.OrderResponseDTO;
import io.github.monthalcantara.model.Item;
import io.github.monthalcantara.model.OrderItem;
import org.springframework.data.domain.Example;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public interface OrderService {

    Optional<OrderItem> findById(Integer id);

    Optional<List<OrderItem>> findAll();

    Optional<List<OrderItem>> findOrderItemByClient(Integer id);

    void deleteById(Integer id);

    OrderItem save(OrderDTO orderItem);

    OrderItem save(OrderItem orderItem);

    List<OrderItem> findAll(Example example);

    BigDecimal findPriceTotal(Integer id);

    OrderItem updateById(Integer id, OrderItem orderItem);

    Optional<OrderItem> getOrderComplete(Integer id);

    OrderResponseDTO convertOrder(OrderItem order);

    List<ItemResponseDTO> convertToItemDTO(List<Item> items);

    List<OrderResponseDTO> convertListOrder(List<OrderItem> order);
}
