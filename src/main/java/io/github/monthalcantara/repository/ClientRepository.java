package io.github.monthalcantara.repository;

import io.github.monthalcantara.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Long, Client> {
}
