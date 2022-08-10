package pl.sda.shoppingList.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.shoppingList.dto.ProductListDTO;
import pl.sda.shoppingList.service.ProductListService;
import pl.sda.shoppingList.service.impl.ProductListServiceImpl;


@Slf4j
@Controller
public class ProductListController {

    private final ProductListService productListService;

    public ProductListController(ProductListServiceImpl productListService) {
        this.productListService = productListService;
    }

    @GetMapping("/user/{userId}/lists/{id}")
    public String showProductsList(@PathVariable Integer userId, @PathVariable Integer id, ModelMap modelMap) {
        modelMap.addAttribute("productList", productListService.getProductListById(id));
        modelMap.addAttribute("userId", userId);
        return "list-show";
    }

    @GetMapping("/user/{userId}/lists/add")
    public String addList(@PathVariable Integer userId, ModelMap modelMap) {
        modelMap.addAttribute("emptyList", new ProductListDTO());
        modelMap.addAttribute("userId", userId);
        return "list-add";
    }

    @PostMapping("/user/{userId}/lists/add")
    public String saveNewList(@PathVariable Integer userId ,@ModelAttribute("emptyList") ProductListDTO productListDTO) {
        log.info("Add new list: " + productListDTO + " to user with id: " + userId);
        productListService.add(productListDTO, userId);
        return "redirect:/user/{userId}/lists/all";
    }

    @GetMapping("/user/{userId}/lists/edit/{id}")
    public String editList(@PathVariable Integer userId ,@PathVariable Integer id, ModelMap modelMap) {
        modelMap.addAttribute("list", productListService.getProductListById(id));
        modelMap.addAttribute("userId", userId);
        return "list-edit";
    }

    @PostMapping("/user/{userId}/lists/edit/{id}")
    public String updateList(@ModelAttribute("list") ProductListDTO productListDTO) {
        log.info("Edit list: "  + productListDTO);
        productListService.update(productListDTO);
        return "redirect:/user/{userId}/lists/all";
    }

    @GetMapping("/user/{userId}/lists/delete/{id}")
    public String deleteListById(@PathVariable Integer id) {
        log.info("Remove list with id: " + id);
        productListService.remove(id);
        return "redirect:/user/{userId}/lists/all";
    }
    @GetMapping("/user/{userId}/lists/all")
    public String showAllLists(@PathVariable Integer userId, ModelMap modelMap) {
        modelMap.addAttribute("lists", productListService.findProductListsByUserId(userId));
        modelMap.addAttribute("userId", userId);
        return "list-all";
    }

}
