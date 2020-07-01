package io.github.monthalcantara.service.implementation;

import io.github.monthalcantara.model.OrderItem;
import io.github.monthalcantara.service.interfaces.OrderService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public Optional<OrderItem> findById(Integer id) {
        return Optional.empty();
    }
}
