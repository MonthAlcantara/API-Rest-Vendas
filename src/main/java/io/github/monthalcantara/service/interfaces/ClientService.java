package io.github.monthalcantara.service.interfaces;

import io.github.monthalcantara.model.Client;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ClientService {
    Optional<Client> findByNameLike(String name);
    Optional<Client> findById(Integer id);
    Optional<List<Client>> findByNameOrIdOrderById(String name, Integer id);
    boolean existsByName(String name);
}
