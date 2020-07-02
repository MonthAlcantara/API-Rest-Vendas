package io.github.monthalcantara.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "orderItem_id")
    private OrderItem orderItem;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product products;

    private Integer quantity;

}