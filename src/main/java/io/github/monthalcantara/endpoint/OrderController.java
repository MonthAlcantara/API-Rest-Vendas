package io.github.monthalcantara.endpoint;

import io.github.monthalcantara.dto.OrderDTO;
import io.github.monthalcantara.model.OrderItem;
import io.github.monthalcantara.service.interfaces.ClientService;
import io.github.monthalcantara.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    ClientService clientService;

    //buscar todos os pedidos


    //buscar pedido pelo client

    //Buscar a data do pedido

    //buscar total do pedido

    //listar items do pedido

    //incluir novo pedido
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody OrderDTO orderItem) {

        return orderService.save(orderItem).getId();

    }
    //deletar pedido

    //Atualizar pedido


}
