package io.github.monthalcantara.service.implementation;

import io.github.monthalcantara.model.Client;
import io.github.monthalcantara.service.interfaces.ClientService;

import java.util.List;
import java.util.Optional;

public class ClientServiceImpl implements ClientService {
    @Override
    public Optional<Client> findByNameLike(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<Client> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Client>> findByNameOrIdOrderById(String name, Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean existsByName(String name) {
        return false;
    }
}
