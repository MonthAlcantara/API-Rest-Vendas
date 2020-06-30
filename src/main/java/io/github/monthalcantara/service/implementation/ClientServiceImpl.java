package io.github.monthalcantara.service.implementation;

import io.github.monthalcantara.model.Client;
import io.github.monthalcantara.repository.ClientRepository;
import io.github.monthalcantara.service.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;

    @Override
    public Client findByNameLike(String name) {
        Optional<Client> client = clientRepository.findByNameLike(name);
        if (client.isPresent()) {
            return client.get();
        }
        return null;
    }

    @Override
    public Client findById(Integer id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            return client.get();
        }
        return null;
    }

    @Override
    public List<Client> findByName(String name) {
        Optional<List<Client>> clients = clientRepository.findByName(name);
        if (clients.isPresent()) {
            return clients.get();
        }
        return null;
    }

    @Override
    public List<Client> findAll() {
        Optional<List<Client>> clients = clientRepository.findAllOp();
        if (clients.isPresent()) {
            return clients.get();
        }
        return null;
    }

    @Override
    public Client findClientFetchOrderItem(Integer id) {
        Optional<Client> client = clientRepository.findClientFetchOrderItem(id);
        if (client.isPresent()) {
            return client.get();
        }
        return null;
    }

    @Override
    public boolean existsByName(String name) {
        return clientRepository.existsByName(name);
    }

    @Override
    public void deleteByName(String name) {
        clientRepository.deleteByName(name);
    }
}
