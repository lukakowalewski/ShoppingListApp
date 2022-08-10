package pl.sda.shoppingList.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.shoppingList.exception.EmptyUsernameException;
import pl.sda.shoppingList.model.User;
import pl.sda.shoppingList.repository.UserRepository;
import pl.sda.shoppingList.service.UserService;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void save(User user) {
        user.setPassword(encodeRawPassword(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public boolean existsByUsername(String username) throws EmptyUsernameException {
        if (username == null || username.isBlank()) {
            throw new EmptyUsernameException("Username can not be empty!");
        }

        return userRepository.existsByUsername(username);
    }

    private String encodeRawPassword(String rawPassword) {
        return bCryptPasswordEncoder.encode(rawPassword);
    }
}
