package io.github.monthalcantara.service.interfaces;

import io.github.monthalcantara.model.Client;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ClientService {
    Client findByNameLike(String name);

    Client findById(Integer id);

    List<Client> findByName(String name);

    List<Client> findAll();

    Client findClientFetchOrderItem(Integer id);

    boolean existsByName(String name);

    void deleteByName(String name);

}
