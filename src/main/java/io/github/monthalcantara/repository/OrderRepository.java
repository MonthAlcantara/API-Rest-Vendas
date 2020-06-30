package io.github.monthalcantara.repository;

import io.github.monthalcantara.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderItem,Integer> {
}
