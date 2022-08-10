package pl.sda.shoppingList.service;

import pl.sda.shoppingList.exception.EmptyUsernameException;
import pl.sda.shoppingList.model.User;

public interface UserService {

    void save(User user);

    boolean existsByUsername(String username) throws EmptyUsernameException;

}
