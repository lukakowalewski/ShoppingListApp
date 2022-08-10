package pl.sda.shoppingList.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.shoppingList.dto.ProductDTO;
import pl.sda.shoppingList.model.Product;
import pl.sda.shoppingList.repository.ProductRepository;
import pl.sda.shoppingList.service.ProductService;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    @Override
    public void addProduct(ProductDTO productDTO, Integer id) {
        Product product = new Product();
        product.setProductListId(id);
        product.setProductName(productDTO.getProductName());
        product.setQuantity(productDTO.getQuantity());
        product.setUnit(productDTO.getUnit());
        productRepository.save(product);
    }

    @Override
    public void updateProduct(ProductDTO productDTO) {
        Product product = productRepository.findById(productDTO.getId()).orElse(null);
        product.setProductName(productDTO.getProductName());
        product.setQuantity(productDTO.getQuantity());
        product.setUnit(productDTO.getUnit());
        productRepository.save(product);
    }

    @Override
    public void removeProduct(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public void changeProductBoughtStatus(Integer id) {
        Product product = productRepository.findById(id).orElse(null);
        product.setBought(!product.isBought());
        productRepository.save(product);
    }

    @Override
    public Product getProductById(Integer id) {
       return productRepository.findById(id).orElse(null);
    }
}
