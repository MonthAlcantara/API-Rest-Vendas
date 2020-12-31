package io.github.monthalcantara.service.implementation;

import io.github.monthalcantara.dto.request.ProductDTO;
import io.github.monthalcantara.dto.response.ProductResponseDTO;
import io.github.monthalcantara.exception.ResourceNotFoundException;
import io.github.monthalcantara.mappers.ProductMapper;
import io.github.monthalcantara.model.Product;
import io.github.monthalcantara.repository.ProductRepository;
import io.github.monthalcantara.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper productMapper;

    @Override
    public Page findAll(Example example, Pageable pageable) {

        return productRepository.findAll(example, pageable);
    }

    @Override
    public ProductResponseDTO findById(Integer id) {
        return returnIfProductExist(id);
    }

    private ProductResponseDTO returnIfProductExist(Integer id) {
        return productRepository.findById(id)
                .map(p -> productMapper.toProductResponseDTO(p))
                .orElseThrow(
                        () ->
                                new ResourceNotFoundException("Product not found")
                );
    }

    @Override
    public ProductResponseDTO findByDescription(String description) {
        return productRepository.findByDescription(description).map(p -> productMapper.toProductResponseDTO(p))
                .orElseThrow(
                        () ->
                                new ResourceNotFoundException("Product not found")
                );
    }

    @Override
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductResponseDTO save(ProductDTO productDTO) {
        Product product = productMapper.toProduct(productDTO);
        return productMapper.toProductResponseDTO(productRepository.save(product));
    }

    @Override
    public ProductResponseDTO update(Integer id, ProductDTO productDTO) {
        Product productRequest = productMapper.toProduct(productDTO);
        Product product = productRepository.findById(id).map(p -> {
            p.setDescription(productRequest.getDescription());
            p.setPrice(productRequest.getPrice());
            return productRepository.save(p);
        }).orElseGet(() -> productRepository.save(productRequest));
        return productMapper.toProductResponseDTO(product);
    }

    @Override
    public Page<ProductResponseDTO> findAllByExample(Pageable pageable, ProductDTO productDTO) {
        Product filter = productMapper.toProduct(productDTO);
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filter, matcher);
        return findAll(example, pageable);
    }
}
