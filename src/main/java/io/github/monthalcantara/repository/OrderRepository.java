package io.github.monthalcantara.repository;

import io.github.monthalcantara.model.Client;
import io.github.monthalcantara.model.OrderItem;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface OrderRepository extends JpaRepository<OrderItem,Integer> {
    Optional<List<OrderItem>> findByClient(Client client);

    List<OrderItem> findAll(Example example);

    OrderItem save(OrderItem orderItem);

    @Query(" select p from OrderItem p left join fetch p.items where p.id = :id ")
    Optional<OrderItem> findByIdFetchItems(@Param("id") Integer id);
}
