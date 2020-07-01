package io.github.monthalcantara.repository;

import io.github.monthalcantara.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Optional<List<Client>> findByName(String name);

    void deleteByName(String name);

    boolean existsByName(String name);

    @Query("Select c from Client c left join fetch c.orderItems where c.id = :id")
    Optional<Client> findClientFetchOrderItem(@Param("id") Integer id);

    Optional<Client> findByNameLike(String name);

    Optional<Client> findById(Integer id);

    List<Client> findAll();

}
