package io.github.monthalcantara.controller;

import io.github.monthalcantara.dto.request.ClientDTO;
import io.github.monthalcantara.dto.response.ClientResponseDTO;
import io.github.monthalcantara.service.interfaces.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    public ClientResponseDTO updateById(@PathVariable Integer id,
                             @RequestBody @Valid ClientDTO client) {
        return clientService.updateById(id, client);
    }

    @GetMapping
    @ApiOperation("Search all clients")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Clients found successfully"),
            @ApiResponse(code = 404, message = "Clients not found"),
    })
    public ResponseEntity<Page<ClientResponseDTO>> findAll(@PageableDefault(size = 5) Pageable pageable, ClientDTO filter) {

        return ResponseEntity.ok(clientService.findAllByExample(pageable, filter));
    }

    @GetMapping("/{id}")
    @ApiOperation("Search for a client by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Client found successfully"),
            @ApiResponse(code = 404, message = "Client not found by the given id"),
    })
    public ResponseEntity<ClientResponseDTO> findById(@PathVariable Integer id) {

        return ResponseEntity.ok(clientService.findById(id));
    }

    @GetMapping("/byName/{name}")
    @ApiOperation("Search for a client by name")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Client found successfully"),
            @ApiResponse(code = 404, message = "Client not found by the given name"),
    })
    public ResponseEntity<Page<ClientResponseDTO>> findByName(@PathVariable String name, @PageableDefault(size = 5) Pageable pageable) {
         return new ResponseEntity<>(clientService.findByName(name, pageable), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("Save a new client")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Client saved successfully"),
            @ApiResponse(code = 400, message = "Validation Error"),
    })
    public ResponseEntity<ClientResponseDTO> save(@RequestBody @Valid ClientDTO client) {
        return new ResponseEntity<>(clientService.save(client), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete a client by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Client found successfully"),
            @ApiResponse(code = 404, message = "Client not found by the given id"),
    })
    public void deleteById(@PathVariable Integer id) {
        clientService.deleteById(id);
    }
}

