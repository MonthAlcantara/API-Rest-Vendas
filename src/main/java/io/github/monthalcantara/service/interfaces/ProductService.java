package io.github.monthalcantara.service.interfaces;

import io.github.monthalcantara.model.Product;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List findAll(Example example);

    Optional<Product> findById(Integer id);

    Optional<Product> findByDescription(String description);

    void deleteById(Integer id);

    Product save(Product product);
}
