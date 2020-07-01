package io.github.monthalcantara.service.interfaces;

import io.github.monthalcantara.model.Client;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;


public interface ClientService {
    Client findByNameLike(String name);

    Optional<Client> findById(Integer id);

    List<Client> findByName(String name);

    List<Client> findAll();

    Client findClientFetchOrderItem(Integer id);

    boolean existsByName(String name);

    Client save(Client client);

    void deleteByName(String name);

    void deleteById(Integer id);

    Client updateById(Integer id, Client client);

    List<Client> findAll(Example example);

}
