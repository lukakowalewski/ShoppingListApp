package pl.sda.shoppingList.service;

import pl.sda.shoppingList.dto.ProductListDTO;
import pl.sda.shoppingList.model.ProductList;

import java.util.List;

public interface ProductListService {

    void add(ProductListDTO productListDTO, Integer userId);
    void update(ProductListDTO productListDTO);
    void remove(Integer id);
    List<ProductList> getAllLists();
    ProductList getProductListById(Integer id);

    List<ProductList> findProductListsByUserId(Integer userId);

}
