package pl.sda.shoppingList.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ProductList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "productListId", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("IS_BOUGHT, PRODUCT_NAME")
    private List<Product> productList = new ArrayList<>();

    private Integer userId;

}
