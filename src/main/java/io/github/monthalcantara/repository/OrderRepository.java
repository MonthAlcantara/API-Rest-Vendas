package io.github.monthalcantara.repository;

import io.github.monthalcantara.model.Client;
import io.github.monthalcantara.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderItem,Integer> {
    List<OrderItem> findByClient(Client client);
}
