package pl.sda.shoppingList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.shoppingList.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername (String username);

    boolean existsByUsername(String username);

}
