package pl.sda.shoppingList.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import pl.sda.shoppingList.model.User;
import pl.sda.shoppingList.repository.UserRepository;

@Component
public class WebSecurity {

    private final UserRepository userRepository;

    public WebSecurity(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean checkUserId(Authentication authentication, int id) {
        String name = authentication.getName();
        User user = userRepository.findByUsername(name);
        return user.getId() == id;
    }


}
