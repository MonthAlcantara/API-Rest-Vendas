package io.github.monthalcantara.repository;

import io.github.monthalcantara.model.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List findAll(Example example);

    Optional<Product> findById(Integer id);

    Optional<Product> findByDescription(String description);

}
