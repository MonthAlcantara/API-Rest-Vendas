package io.github.monthalcantara.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    private Client client;

    @OneToMany
    private List<Item> items;
}
