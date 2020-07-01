package io.github.monthalcantara.endpoint;

import io.github.monthalcantara.model.Client;
import io.github.monthalcantara.service.interfaces.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity updateById(@PathVariable Integer id,
                                     @RequestBody Client client) {
        return clientService.findById(id)
                .map(c -> {
                    client.setId(c.getId());
                    clientService.save(client);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity findAll(Client filter) {
        ExampleMatcher matcher = ExampleMatcher
                .matching() //Criando o objeto
                .withIgnoreCase() //Ignorando CamelCase dos parâmetros String
                .withStringMatcher( //Forma que ele usará para encontrar os valores String
                        ExampleMatcher.StringMatcher.CONTAINING);//CONTAINING -> Encontrar os valores que possuem a String, independente da localização
        Example example = Example.of(filter, matcher);
        List<Client> clients = clientService.findAll(example);
        return ResponseEntity.ok(clients);

    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Integer id) {
        Optional<Client> client = clientService.findById(id);
        return client.map(value -> new ResponseEntity(value, HttpStatus.OK)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/byName/{name}")
    public ResponseEntity findByName(@PathVariable String name) {
        Optional<List<Client>> clients = Optional.of(clientService.findByName(name));
        return clients.map(clientList -> new ResponseEntity(clientList, HttpStatus.OK)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Client> save(@RequestBody Client client) {
        return new ResponseEntity<>(clientService.save(client), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id) {
        if (clientService.findById(id).isPresent()) {
            clientService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}

