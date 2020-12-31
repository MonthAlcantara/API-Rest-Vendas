package io.github.monthalcantara.service.implementation;

import io.github.monthalcantara.dto.request.ItemDTO;
import io.github.monthalcantara.dto.request.OrderDTO;
import io.github.monthalcantara.dto.response.ClientResponseDTO;
import io.github.monthalcantara.dto.response.ItemResponseDTO;
import io.github.monthalcantara.dto.response.OrderResponseDTO;
import io.github.monthalcantara.enums.OrderStatus;
import io.github.monthalcantara.exception.BusinessRuleException;
import io.github.monthalcantara.exception.ResourceNotFoundException;
import io.github.monthalcantara.mappers.ClientMapper;
import io.github.monthalcantara.mappers.ProductMapper;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
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

    @Autowired
    ClientMapper clientMapper;

    @Autowired
    ProductMapper productMapper;


    @Override
    public Optional<OrderItem> findById(Integer id) {
        return orderRepository.findById(id);
    }

    @Override
    public Optional<Page<OrderItem>> findAll(Pageable pageable) {
        return Optional.of(orderRepository.findAll(pageable));
    }

    @Override
    public Optional<Page<OrderItem>> findOrderItemByClient(Integer id, Pageable pageable) {
        ClientResponseDTO client = clientService.findById(id);
        return orderRepository.findByClient(clientMapper.clientResponseDTOToClient(client), pageable);
    }

    @Override
    public void deleteById(Integer id) {
        orderRepository.deleteById(id);
    }

    @Override
    @Transactional
    public OrderItem save(OrderDTO orderItem) {
        Integer idClient = orderItem.getClient();
        ClientResponseDTO client = clientService.findById(idClient);

        OrderItem build = OrderItem.builder()
                .client(clientMapper.clientResponseDTOToClient(client))
                .status(OrderStatus.DONE)
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
    public Page<OrderItem> findAll(Example example, Pageable pageable) {

        return orderRepository.findAll(pageable);
    }

    @Override
    public BigDecimal findPriceTotal(Integer id) {
        Optional<OrderItem> order = orderRepository.findById(id);
        return order
                .map(orderItem -> orderItem.getTotal())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Order not found"));
    }

    @Override
    public OrderItem updateById(Integer id, OrderItem orderItem) {
        return orderRepository.findById(id).map(order -> {
            Integer idOrder = order.getId();
            orderItem.setId(idOrder);
            return orderRepository.save(orderItem);
        }).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    @Override
    public Optional<OrderItem> getOrderComplete(Integer id) {
        return orderRepository.findByIdFetchItems(id);
    }

    private List<Item> convertItems(OrderItem orderItem, List<ItemDTO> items) {
        if (items.isEmpty()) {
            throw new BusinessRuleException("Impossible. List is Empty");
        }
        return items
                .stream()
                .map(item -> {
                    Integer idProduct = item.getProduct();
                    Product product = productMapper.productResponseDTOToProduct(productService.findById(idProduct));
                            Optional.of(product).orElseThrow(() -> new ResourceNotFoundException("Product not found: " + idProduct));

                    return Item.builder()
                            .quantity(item.getQuantity())
                            .product(product)
                            .orderItem(orderItem)
                            .build();
                }).collect(Collectors.toList());

    }

    public OrderResponseDTO convertOrder(OrderItem order) {
        return new OrderResponseDTO()
                .builder().clientName(order.getClient().getName())
                .code(order.getId())
                .date(order.getDate())
                .status(order.getStatus().name())
                .cpf(order.getClient().getCpf())
                .items(convertToItemDTO(order.getItems()))
                .total(order.getTotal())
                .build();

    }

    public Page<OrderResponseDTO> convertListOrder(Page<OrderItem> order) {
        List<OrderResponseDTO> orderResponseDTOList = new ArrayList<>();
        order.stream().map(orderItem -> {
            return orderResponseDTOList.add(new OrderResponseDTO()
                    .builder().clientName(orderItem.getClient().getName())
                    .code(orderItem.getId())
                    .date(orderItem.getDate())
                    .status(orderItem.getStatus().name())
                    .cpf(orderItem.getClient().getCpf())
                    .items(convertToItemDTO(orderItem.getItems()))
                    .total(orderItem.getTotal())
                    .build()
            );
        }).collect(Collectors.toList());
        return new PageImpl<>(orderResponseDTOList);
    }

    @Override
    public void updateStatus(Integer id, OrderStatus status) {
        orderRepository.findById(id)
                .map(orderItem -> {
                    orderItem.setStatus(status);
                    return orderRepository.save(orderItem);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    public List<ItemResponseDTO> convertToItemDTO(List<Item> items) {
        if (CollectionUtils.isEmpty(items)) {
            return Collections.emptyList();
        }
        return items.stream().map(item -> {
            return new ItemResponseDTO()
                    .builder()
                    .description(item.getProduct().getDescription())
                    .priceUnit(item.getProduct().getPrice())
                    .quantity(item.getQuantity())
                    .build();
        })
                .collect(Collectors.toList());
    }


}

