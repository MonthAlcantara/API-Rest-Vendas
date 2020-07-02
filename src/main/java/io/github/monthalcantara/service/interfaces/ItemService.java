package io.github.monthalcantara.service.interfaces;

import io.github.monthalcantara.model.Item;

import java.util.List;


public interface ItemService {
    List<Item> saveAll(Iterable<Item> iterable);
}
