package pl.sda.shoppingList.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.shoppingList.exception.EmptyUsernameException;
import pl.sda.shoppingList.model.User;
import pl.sda.shoppingList.service.UserService;

@Slf4j
@Controller
public class RegistrationController {

    private final UserService userService;


    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String showRegistrationForm(ModelMap modelMap) {
        modelMap.addAttribute("emptyUser", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String handleNewUser(@ModelAttribute("emptyUser") User user, ModelMap modelMap) {

        log.info("Received new user: " + user.getUsername());

        try {
            if (userService.existsByUsername(user.getUsername())) {
                log.info("User with username " + user.getUsername() + " exists!");
                modelMap.addAttribute("exceptionMessage", "User " + user.getUsername() + " already exists!");
                return "registration";
            }
        } catch (EmptyUsernameException e) {
            log.info(e.getMessage());
            modelMap.addAttribute("exceptionMessage", e.getMessage());
            return "registration";
        }

        userService.save(user);

        return "redirect:/login";
    }

}
