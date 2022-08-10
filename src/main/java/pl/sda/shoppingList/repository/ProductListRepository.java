package pl.sda.shoppingList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.shoppingList.model.ProductList;

import java.util.List;

public interface ProductListRepository extends JpaRepository<ProductList, Integer> {

    List<ProductList> findProductListsByUserId(Integer userId);


}
