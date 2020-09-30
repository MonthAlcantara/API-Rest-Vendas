package io.github.monthalcantara.controller;

import io.github.monthalcantara.dto.request.ProductDTO;
import io.github.monthalcantara.dto.response.ProductResponseDTO;
import io.github.monthalcantara.model.Product;
import io.github.monthalcantara.service.interfaces.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

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
    public ResponseEntity findAll(@PageableDefault(size = 5) Pageable pageable, ProductDTO filter) {

        return new ResponseEntity(productService.findAllByExample(pageable, filter), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Search product by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product found successfully"),
            @ApiResponse(code = 404, message = "Product not found by the given ID"),
    })
    public ProductResponseDTO findById(@PathVariable Integer id) {
        return productService.findById(id);
    }

    @GetMapping("/description/{description}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Search for a product by description")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product found successfully"),
            @ApiResponse(code = 404, message = "Product not found by the given description"),
    })
    public ProductResponseDTO findByDescription(@PathVariable String description) {
        return productService.findByDescription(description);
    }

    @GetMapping("/price/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Search the order price by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product found successfully"),
            @ApiResponse(code = 404, message = "Product not found by the given ID"),
    })
    public BigDecimal findPriceById(@PathVariable Integer id) {
        return productService.findById(id).getPrice();
    }

    @GetMapping("/priceByDescription/{description}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Search the order price by description")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product found successfully"),
            @ApiResponse(code = 404, message = "Product not found by the given description"),
    })
    public BigDecimal findPriceByDescription(@PathVariable String description) {
        return productService.findByDescription(description).getPrice();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Updated a product by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product updated successfully"),
            @ApiResponse(code = 400, message = "Validation Error"),
            @ApiResponse(code = 404, message = "Product not found by the given id"),
    })
    public void updateById(@PathVariable Integer id, @RequestBody @Valid ProductDTO product) {
        productService.update(id, product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Delete a product by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product found successfully"),
            @ApiResponse(code = 404, message = "Product not found by the given id"),
    })
    public void deleteById(@PathVariable Integer id) {
        productService.deleteById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Save a new Product")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Product saved successfully"),
            @ApiResponse(code = 400, message = "Validation Error"),
    })
    public ProductResponseDTO save(@RequestBody @Valid ProductDTO product) {
        return productService.save(product);
    }
}
