package pl.sda.shoppingList.service.impl;

import org.springframework.stereotype.Service;
import pl.sda.shoppingList.dto.ProductListDTO;
import pl.sda.shoppingList.model.ProductList;
import pl.sda.shoppingList.repository.ProductListRepository;
import pl.sda.shoppingList.service.ProductListService;

import java.util.List;

@Service
public class ProductListServiceImpl implements ProductListService {

    private final ProductListRepository productListRepository;


    public ProductListServiceImpl(ProductListRepository productListRepository) {
        this.productListRepository = productListRepository;

    }

    @Override
    public void add(ProductListDTO productListDTO, Integer userId) {
        ProductList productList = new ProductList();
        productList.setUserId(userId);
        productList.setName(productListDTO.getName());
        productListRepository.save(productList);
    }
    @Override
    public void update(ProductListDTO productListDTO) {
        ProductList productList = productListRepository.findById(productListDTO.getId()).orElse(null);
        productList.setName(productListDTO.getName());
        productListRepository.save(productList);
    }

    @Override
    public void remove(Integer id) {
        productListRepository.deleteById(id);
    }

    @Override
    public List<ProductList> getAllLists() {
       return productListRepository.findAll();
    }

    @Override
    public ProductList getProductListById(Integer id) {
        return productListRepository.findById(id).orElse(null);
    }

    @Override
    public List<ProductList> findProductListsByUserId(Integer userId) {
        return productListRepository.findProductListsByUserId(userId);
    }


}
