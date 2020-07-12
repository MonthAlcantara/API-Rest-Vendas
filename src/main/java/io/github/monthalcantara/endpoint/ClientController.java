package io.github.monthalcantara.endpoint;

import io.github.monthalcantara.model.Client;
import io.github.monthalcantara.service.interfaces.ClientService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/clients")
@Api("Api Clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Updated a client by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Client updated successfully"),
            @ApiResponse(code = 400, message = "Validation Error"),
            @ApiResponse(code = 404, message = "Client not found by the given id"),
    })
    public Client updateById(@PathVariable Integer id,
                             @RequestBody @Valid Client client) {
        return clientService.findById(id)
                .map(c -> {
                    client.setId(c.getId());
                    clientService.save(client);
                    return client;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
    }

    @GetMapping
    @ApiOperation("Seeks all clients")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Clients found successfully"),
            @ApiResponse(code = 404, message = "Clients not found"),
    })
    public ResponseEntity findAll(Client filter) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filter, matcher);
        List<Client> clients = clientService.findAll(example);
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    @ApiOperation("Search for a client by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Client found successfully"),
            @ApiResponse(code = 404, message = "Client not found by the given id"),
    })
    public ResponseEntity findById(@PathVariable Integer id) {
        Optional<Client> client = clientService.findById(id);
        return client.map(value -> new ResponseEntity(value, HttpStatus.OK)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/byName/{name}")
    @ApiOperation("Search for a client by name")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Client found successfully"),
            @ApiResponse(code = 404, message = "Client not found by the given name"),
    })
    public ResponseEntity findByName(@PathVariable String name) {
        Optional<List<Client>> clients = Optional.of(clientService.findByName(name));
        return clients.map(clientList -> new ResponseEntity(clientList, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound()
                        .build());
    }

    @PostMapping
    @ApiOperation("Save a new client")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Client saved successfully"),
            @ApiResponse(code = 400, message = "Validation Error"),
    })
    public ResponseEntity<Client> save(@RequestBody @Valid Client client) {
        return new ResponseEntity<>(clientService.save(client), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete a client by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Client found successfully"),
            @ApiResponse(code = 404, message = "Client not found by the given id"),
    })
    public ResponseEntity deleteById(@PathVariable Integer id) {
        if (clientService.findById(id).isPresent()) {
            clientService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}

