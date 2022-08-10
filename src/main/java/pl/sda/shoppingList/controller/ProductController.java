package pl.sda.shoppingList.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.shoppingList.dto.ProductDTO;
import pl.sda.shoppingList.service.ProductService;

@Slf4j
@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/user/{userId}/lists/{id}/product")
    public String addProduct(@PathVariable Integer userId, @PathVariable Integer id, ModelMap modelMap) {
        ProductDTO productDTO = new ProductDTO();
        modelMap.addAttribute("listId", id);
        modelMap.addAttribute("userId", userId);
        modelMap.addAttribute("emptyProduct", productDTO);
        return "product-add";
    }

    @PostMapping("/user/{userId}/lists/{id}/product")
    public String saveProduct(@PathVariable Integer userId ,@PathVariable Integer id, @ModelAttribute("emptyProduct") ProductDTO productDTO) {
        log.info("Add new product: " + productDTO + " to list" + id);
        productService.addProduct(productDTO, id);
        return "redirect:/user/{userId}/lists/{id}";
    }

    @GetMapping("/user/{userId}/lists/{listId}/product/edit/{id}")
    public String editProduct(@PathVariable Integer userId ,@PathVariable Integer listId, @PathVariable Integer id, ModelMap modelMap) {
        modelMap.addAttribute("product", productService.getProductById(id));
        modelMap.addAttribute("listId", listId);
        modelMap.addAttribute("userId", userId);
        return "product-edit";
    }

    @PostMapping("/user/{userId}/lists/{listId}/product/edit/{id}")
    public String updateProduct(@PathVariable Integer userId, @PathVariable Integer listId,  @ModelAttribute("product") ProductDTO productDTO) {
        log.info("Edit product: " + productDTO);
        productService.updateProduct(productDTO);
        return "redirect:/user/{userId}/lists/{listId}";
    }

    @GetMapping("/user/{userId}/lists/{listId}/product/incart/{id}")
    public String changeProductStatus(@PathVariable Integer userId, @PathVariable Integer listId, @PathVariable Integer id) {
        productService.changeProductBoughtStatus(id);
        return "redirect:/user/{userId}/lists/{listId}";
    }

    @GetMapping("/user/{userId}/lists/{listId}/product/delete/{id}")
    public String editProduct(@PathVariable Integer id) {
        log.info("Remove product with id : " + id);
        productService.removeProduct(id);
        return "redirect:/user/{userId}/lists/{listId}";

    }
}
