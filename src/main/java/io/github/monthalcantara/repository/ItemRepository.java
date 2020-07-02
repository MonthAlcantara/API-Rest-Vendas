package io.github.monthalcantara.repository;

import io.github.monthalcantara.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ItemRepository extends JpaRepository<Item, Integer> {

}
