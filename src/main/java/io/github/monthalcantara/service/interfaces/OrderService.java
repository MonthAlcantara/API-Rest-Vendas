package io.github.monthalcantara.service.interfaces;

import io.github.monthalcantara.dto.request.OrderDTO;
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
}
