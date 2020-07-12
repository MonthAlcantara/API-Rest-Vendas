package io.github.monthalcantara.endpoint;

import io.github.monthalcantara.dto.request.OrderDTO;
import io.github.monthalcantara.dto.request.UpdateOrderStatusDTO;
import io.github.monthalcantara.enums.OrderStatus;
import io.github.monthalcantara.exception.BusinessRuleException;
import io.github.monthalcantara.model.OrderItem;
import io.github.monthalcantara.service.interfaces.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping
    @ApiOperation("Seeks all orders")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Order found successfully"),
            @ApiResponse(code = 404, message = "Order not found"),
    })
    public ResponseEntity findAll(OrderItem orderItem) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(orderItem, matcher);
        List<OrderItem> orderItems = orderService.findAll(example);
        return new ResponseEntity(orderService.convertListOrder(orderItems), HttpStatus.OK);
    }

    @GetMapping("/client/{id}")
    @ApiOperation("Search for a order by client ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Client found successfully"),
            @ApiResponse(code = 404, message = "Client not found by the given ID"),
    })
    public ResponseEntity findByClient(@PathVariable Integer id) {
        Optional<List<OrderItem>> orderItemByClient = orderService.findOrderItemByClient(id);
        return new ResponseEntity(orderItemByClient, HttpStatus.OK);
    }

    @GetMapping("/total/{id}")
    @ApiOperation("Search for the total order value by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Order found successfully"),
            @ApiResponse(code = 404, message = "Order not found by the given ID"),
    })
    public ResponseEntity getTotal(@PathVariable Integer id) {
        BigDecimal priceTotal = orderService.findPriceTotal(id);
        return new ResponseEntity(priceTotal, HttpStatus.OK);
    }

    @GetMapping("/item/{id}")
    @ApiOperation("Fetch order items by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Order found successfully"),
            @ApiResponse(code = 404, message = "Order not found by the given ID"),
    })
    public ResponseEntity getItems(@PathVariable Integer id) {
        return new ResponseEntity(orderService
                .findById(id)
                .map(orderItem -> orderItem.getItems()).orElseThrow(() ->
                        new BusinessRuleException("Items not found")), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    @ApiOperation("Search order by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Order found successfully"),
            @ApiResponse(code = 404, message = "Order not found by the given ID"),
    })
    public ResponseEntity findOrderById(@PathVariable Integer id) {
        return new ResponseEntity(orderService
                .findById(id)
                .map(orderItem -> orderService.convertOrder(orderItem)).orElseThrow(() ->
                        new BusinessRuleException("Items not found")), HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Save a new order")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Order saved successfully"),
            @ApiResponse(code = 400, message = "Validation Error"),
    })
    public Integer save(@RequestBody @Valid OrderDTO orderItem) {

        return orderService.save(orderItem).getId();

    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Updated a order by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Order updated successfully"),
            @ApiResponse(code = 400, message = "Validation Error"),
            @ApiResponse(code = 404, message = "Order not found by the given id"),
    })
    void updateStatus(@RequestBody UpdateOrderStatusDTO status, @PathVariable Integer id){
        String newStatus = status.getNewStatus();
        orderService.updateStatus(id, OrderStatus.valueOf(newStatus));

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Delete a order by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Order found successfully"),
            @ApiResponse(code = 404, message = "Order not found by the given id"),
    })
    public void deleteOrder(@PathVariable Integer id) {
        orderService.deleteById(id);
    }

    @PutMapping("/{id}")
    @ApiOperation("Updated a order by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Order updated successfully"),
            @ApiResponse(code = 400, message = "Validation Error"),
            @ApiResponse(code = 404, message = "Order not found by the given id"),
    })
    public ResponseEntity update(@PathVariable Integer id, @RequestBody OrderItem orderItem) {
        return new ResponseEntity(orderService.findById(id).map(order -> {
            Integer idOrder = order.getId();
            orderItem.setId(idOrder);
            return orderService.save(orderItem);
        }).orElseThrow(() -> new BusinessRuleException("Order not Found")), HttpStatus.OK);

    }

}
