package io.github.monthalcantara.repository;

import io.github.monthalcantara.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Long, Item> {
}
