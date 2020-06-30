package io.github.monthalcantara.service.implementation;

import io.github.monthalcantara.model.OrderItem;
import io.github.monthalcantara.service.interfaces.OrderService;

import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    @Override
    public Optional<OrderItem> findById(Integer id) {
        return Optional.empty();
    }
}
