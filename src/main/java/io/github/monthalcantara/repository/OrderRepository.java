package io.github.monthalcantara.repository;

import io.github.monthalcantara.model.Client;
import io.github.monthalcantara.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<OrderItem,Integer> {
    List<OrderItem> findByClient(Client client);
}
