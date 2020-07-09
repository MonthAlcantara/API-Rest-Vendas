package io.github.monthalcantara.endpoint;

import io.github.monthalcantara.model.Product;
import io.github.monthalcantara.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity findAll(Product filter) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filter, matcher);

        return new ResponseEntity(productService.findAll(example), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product findById(@PathVariable Integer id) {
        return productService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @GetMapping("/description/{description}")
    @ResponseStatus(HttpStatus.OK)
    public String findByDescription(@PathVariable String description) {
        return productService.findByDescription(description)
                .map(product -> product.getDescription())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @GetMapping("/price/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal findPriceById(@PathVariable Integer id) {
        return productService.findById(id)
                .map(product -> product.getPrice())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @GetMapping("/priceByDescription/{description}")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal findPriceByDescription(@PathVariable String description) {
        return productService.findByDescription(description)
                .map(product -> product.getPrice())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateById(@PathVariable Integer id, @RequestBody @Valid Product product) {
        productService
                .findById(id)
                .map(p -> {
                    product.setId(p.getId());
                    productService.save(product);
                    return product;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Integer id) {
        if (productService.findById(id).isPresent()) {
            productService.deleteById(id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product save(@RequestBody @Valid Product product) {
        return productService.save(product);
    }
}
