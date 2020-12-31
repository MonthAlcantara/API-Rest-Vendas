package io.github.monthalcantara.repository;

import io.github.monthalcantara.model.Client;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query("Select c from Client c where c.name =:name ")
    Page<Client> findByName(@Param("name") String name, Pageable pageable);

   // @Query("Delete from Client c where c.name = :name")
    void deleteByName( String name);

    boolean existsByName(String name);

    @Query("Select c from Client c left join fetch c.orderItems where c.id = :id")
    Optional<Client> findClientFetchOrderItem(@Param("id") Integer id);

    @Query("Select c from Client c where c.name like %:name%")
    Optional<Client> findByNameLike(@Param("name") String name);

    Optional<Client> findById(Integer id);

    Page<Client> findAll(Example example, Pageable pageable);

}
