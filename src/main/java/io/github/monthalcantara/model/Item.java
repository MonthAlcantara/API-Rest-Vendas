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
    @JoinColumn(name = "orderItem.id")
    private OrderItem orderItem;

    @ManyToOne
    @JoinColumn(name = "product.id")
    private Product products;

    private Integer quantity;

}
