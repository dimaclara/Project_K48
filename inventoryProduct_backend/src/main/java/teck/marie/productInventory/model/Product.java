package teck.marie.productInventory.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nameProduct;


    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int stock;

}
