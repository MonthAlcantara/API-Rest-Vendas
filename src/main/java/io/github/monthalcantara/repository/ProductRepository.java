package io.github.monthalcantara.repository;

import io.github.monthalcantara.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Long, Product> {
}
