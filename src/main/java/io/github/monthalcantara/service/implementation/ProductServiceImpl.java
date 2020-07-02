package io.github.monthalcantara.service.implementation;

import io.github.monthalcantara.model.Product;
import io.github.monthalcantara.repository.ProductRepository;
import io.github.monthalcantara.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List findAll(Example example) {
        return productRepository.findAll(example);
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByDescription(String description) {
        return productRepository.findByDescription(description);
    }
}
