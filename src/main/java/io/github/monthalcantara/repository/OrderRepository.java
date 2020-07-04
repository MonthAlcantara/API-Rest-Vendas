package io.github.monthalcantara.repository;

import io.github.monthalcantara.model.Client;
import io.github.monthalcantara.model.OrderItem;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface OrderRepository extends JpaRepository<OrderItem,Integer> {
    Optional<List<OrderItem>> findByClient(Client client);

    List<OrderItem> findAll(Example example);

    OrderItem save(OrderItem orderItem);

    Optional<OrderItem> findByIdFetchItems(Integer id);
}
