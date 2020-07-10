package io.github.monthalcantara.repository;

import io.github.monthalcantara.model.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserLogin, Integer> {

    Optional<UserLogin> findByLogin(String login);
}
