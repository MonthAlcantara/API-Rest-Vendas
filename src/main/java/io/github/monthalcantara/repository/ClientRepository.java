package io.github.monthalcantara.repository;

import io.github.monthalcantara.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {


    Optional<Client> findByIdOrName(Integer integer, String name);
}
