package io.github.monthalcantara.endpoint;

import io.github.monthalcantara.model.Client;
import io.github.monthalcantara.service.interfaces.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public ResponseEntity findAll() {
        Optional<List<Client>> clients = Optional.of(clientService.findAll());
        return clients.map(clientList -> new ResponseEntity(clientList, HttpStatus.OK)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Integer id) {
        Optional<Client> client = Optional.of(clientService.findById(id));
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
        if (Optional.of(clientService.findById(id)).isPresent()) {
            clientService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateById(@PathVariable Integer id, @RequestBody Client client) {
        Optional<Client> clientOptional = Optional.of(clientService.updateById(id, client));

        return new ResponseEntity(HttpStatus.OK);
    }
}

