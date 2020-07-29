package io.github.monthalcantara.controller;

import io.github.monthalcantara.model.Product;
import io.github.monthalcantara.service.interfaces.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/api/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    @ApiOperation("Search all products")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product found successfully"),
            @ApiResponse(code = 404, message = "Product not found"),
    })
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
    @ApiOperation("Search product by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product found successfully"),
            @ApiResponse(code = 404, message = "Product not found by the given ID"),
    })
    public Product findById(@PathVariable Integer id) {
        return productService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @GetMapping("/description/{description}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Search for a product by description")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product found successfully"),
            @ApiResponse(code = 404, message = "Product not found by the given description"),
    })
    public String findByDescription(@PathVariable String description) {
        return productService.findByDescription(description)
                .map(product -> product.getDescription())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @GetMapping("/price/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Search the order price by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product found successfully"),
            @ApiResponse(code = 404, message = "Product not found by the given ID"),
    })
    public BigDecimal findPriceById(@PathVariable Integer id) {
        return productService.findById(id)
                .map(product -> product.getPrice())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @GetMapping("/priceByDescription/{description}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Search the order price by description")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product found successfully"),
            @ApiResponse(code = 404, message = "Product not found by the given description"),
    })
    public BigDecimal findPriceByDescription(@PathVariable String description) {
        return productService.findByDescription(description)
                .map(product -> product.getPrice())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Updated a product by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product updated successfully"),
            @ApiResponse(code = 400, message = "Validation Error"),
            @ApiResponse(code = 404, message = "Product not found by the given id"),
    })
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
    @ApiOperation("Delete a product by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product found successfully"),
            @ApiResponse(code = 404, message = "Product not found by the given id"),
    })
    public void deleteById(@PathVariable Integer id) {
        if (productService.findById(id).isPresent()) {
            productService.deleteById(id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Save a new Product")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Product saved successfully"),
            @ApiResponse(code = 400, message = "Validation Error"),
    })
    public Product save(@RequestBody @Valid Product product) {
        return productService.save(product);
    }
}
