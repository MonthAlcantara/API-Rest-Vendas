package io.github.monthalcantara.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Request request;

    @OneToMany
    private List<Product> products;

}
