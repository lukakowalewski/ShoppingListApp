package pl.sda.shoppingList.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String productName;
    private String unit;
    private Double quantity;
    private boolean isBought;

    private Integer productListId;


}
