package io.github.monthalcantara.endpoint;

import io.github.monthalcantara.dto.request.OrderDTO;
import io.github.monthalcantara.dto.response.ItemResponseDTO;
import io.github.monthalcantara.dto.response.OrderResponseDTO;
import io.github.monthalcantara.exception.BusinessRuleException;
import io.github.monthalcantara.model.Item;
import io.github.monthalcantara.model.OrderItem;
import io.github.monthalcantara.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    //buscar todos os pedidos
    @GetMapping
    public ResponseEntity findAll(OrderItem orderItem) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(orderItem, matcher);
        List<OrderItem> orderItems = orderService.findAll(example);
        return new ResponseEntity(orderItems, HttpStatus.OK);
    }

    //buscar pedido pelo client
    @GetMapping("/client/{id}")
    public ResponseEntity findByClient(@PathVariable Integer id) {
        Optional<List<OrderItem>> orderItemByClient = orderService.findOrderItemByClient(id);
        return new ResponseEntity(orderItemByClient, HttpStatus.OK);
    }

    //buscar total do pedido
    @GetMapping("/total/{id}")
    public ResponseEntity getTotal(@PathVariable Integer id) {
        BigDecimal priceTotal = orderService.findPriceTotal(id);
        return new ResponseEntity(priceTotal, HttpStatus.OK);
    }

    //listar items do pedido
    @GetMapping("/item/{id}")
    public ResponseEntity getItems(@PathVariable Integer id) {
        return new ResponseEntity(orderService
                .findById(id)
                .map(orderItem -> orderItem.getItems()).orElseThrow(() ->
                        new BusinessRuleException("Items not found")), HttpStatus.OK);
    }

    //incluir novo pedido
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody OrderDTO orderItem) {

        return orderService.save(orderItem).getId();

    }

    //deletar pedido
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrder(@PathVariable Integer id) {
        orderService.deleteById(id);
    }
    //Atualizar pedido
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody OrderItem orderItem){
        return new ResponseEntity(orderService.findById(id).map(order -> {
            Integer idOrder = order.getId();
            orderItem.setId(idOrder);
            return orderService.save(orderItem);
        }).orElseThrow(() -> new BusinessRuleException("Order not Found")), HttpStatus.OK);

    }

    private OrderResponseDTO convertOrder(OrderItem order){
        return new OrderResponseDTO()
                .builder().clientName(order.getClient().getName())
                .code(order.getId())
                .cpf(order.getClient().getCpf())
                .items(convertToItemDTO(order.getItems()))
                .total(order.getTotal())
                .build();

    }
    private List<ItemResponseDTO> convertToItemDTO(List<Item> items){
        return null;
    }



}
