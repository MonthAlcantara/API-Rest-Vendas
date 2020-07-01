package io.github.monthalcantara.service.implementation;

import io.github.monthalcantara.model.Client;
import io.github.monthalcantara.repository.ClientRepository;
import io.github.monthalcantara.service.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;

    @Override
    public Client findByNameLike(String name) {
        Optional<Client> client = clientRepository.findByNameLike(name);
        return client.orElse(null);
    }

    @Override
    public Optional<Client> findById(Integer id) {
        Optional<Client> client = clientRepository.findById(id);
        return client;
    }

    @Override
    public List<Client> findByName(String name) {
        Optional<List<Client>> clients = clientRepository.findByName(name);
        return clients.orElse(null);
    }

    @Override
    public List<Client> findAll() {
        Optional<List<Client>> clients = Optional.ofNullable(clientRepository.findAll());
        return clients.orElse(null);
    }

    @Override
    public Client findClientFetchOrderItem(Integer id) {
        Optional<Client> client = clientRepository.findClientFetchOrderItem(id);
        return client.orElse(null);
    }

    @Override
    public boolean existsByName(String name) {
        return clientRepository.existsByName(name);
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void deleteByName(String name) {
        clientRepository.deleteByName(name);
    }

    @Override
    public void deleteById(Integer id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Client updateById(Integer id, Client client) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isPresent()) {
            clientOptional.get().setName(client.getName());
            return clientRepository.save(clientOptional.get());
        }
        return clientRepository.save(client);
    }
    public List<Client> findAll(Example example){
        return clientRepository.findAll(example);
    }

}
