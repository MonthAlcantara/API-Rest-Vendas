package io.github.monthalcantara.service.interfaces;

import io.github.monthalcantara.model.OrderItem;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface OrderService {
    Optional<OrderItem> findById(Integer id);
}
