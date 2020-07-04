package io.github.monthalcantara.service.implementation;

import io.github.monthalcantara.dto.OrderDTO;
import io.github.monthalcantara.dto.ItemDTO;
import io.github.monthalcantara.exception.BusinessRuleException;
import io.github.monthalcantara.model.Client;
import io.github.monthalcantara.model.Item;
import io.github.monthalcantara.model.OrderItem;
import io.github.monthalcantara.model.Product;
import io.github.monthalcantara.repository.OrderRepository;
import io.github.monthalcantara.service.interfaces.ClientService;
import io.github.monthalcantara.service.interfaces.ItemService;
import io.github.monthalcantara.service.interfaces.OrderService;
import io.github.monthalcantara.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ClientService clientService;

    @Autowired
    ProductService productService;

    @Autowired
    ItemService itemService;


    @Override
    public Optional<OrderItem> findById(Integer id) {
        return orderRepository.findById(id);
    }

    @Override
    public Optional<List<OrderItem>> findAll() {
        return Optional.empty();
    }

    @Override
    public Optional<List<OrderItem>> findOrderItemByClient(Integer id) {
        Client client = clientService.findById(id)
                .orElseThrow(() ->
                        new BusinessRuleException("Client code not found"));
        return orderRepository.findByClient(client);
    }

    @Override
    public void deleteById(Integer id) {
orderRepository.deleteById(id);
    }

    @Override
    @Transactional
    public OrderItem save(OrderDTO orderItem) {
        Integer idClient = orderItem.getClient();
        Client client = clientService.findById(idClient)
                .orElseThrow(() -> new BusinessRuleException("Client code not found"));

        OrderItem build = OrderItem.builder()
                .client(client)
                .date(LocalDate.now())
                .total(orderItem.getTotal())
                .build();

        List<Item> items = convertItems(build, orderItem.getItems());

        orderRepository.save(build);
        itemService.saveAll(items);
        build.setItems(items);
        return build;

    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        return orderRepository.save(orderItem);
    }

    @Override
    public List<OrderItem> findAll(Example example) {

        return orderRepository.findAll();
    }

    @Override
    public BigDecimal findPriceTotal(Integer id) {
        Optional<OrderItem> order = orderRepository.findById(id);
        return order
                .map(orderItem -> orderItem.getTotal())
                .orElseThrow(() ->
                        new BusinessRuleException("Order not found"));
    }

    @Override
    public OrderItem updateById(Integer id, OrderItem orderItem) {
        return orderRepository.findById(id).map(order -> {
            Integer idOrder = order.getId();
            orderItem.setId(idOrder);
            return orderRepository.save(orderItem);
            }).orElseThrow(()-> new BusinessRuleException("Order not found"));
    }

    private List<Item> convertItems(OrderItem orderItem, List<ItemDTO> items) {
        if (items.isEmpty()) {
            throw new BusinessRuleException("Impossible. List is Empty");
        }
        return items
                .stream()
                .map(item -> {
                    Integer idProduct = item.getProduct();
                    Product product = productService.findById(idProduct)
                            .orElseThrow(() -> new BusinessRuleException("Product not found: " + idProduct));

                    return Item.builder()
                            .quantity(item.getQuantity())
                            .products(product)
                            .orderItem(orderItem)
                            .build();

                }).collect(Collectors.toList());

    }
}
