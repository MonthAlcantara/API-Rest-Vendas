package io.github.monthalcantara.repository;

import io.github.monthalcantara.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Long, Request> {
}
