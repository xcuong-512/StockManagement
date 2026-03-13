package service;

import Model.User;
import repository.UserRepository;

import java.util.List;

public class AuthenticationService {
    private UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String username, String password) {
        User user = userRepository.findByName(username);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public void register(String username, String password) {
        List<User> users = userRepository.loadUsers();
        users.add(new User(username, password, "user"));
        userRepository.save(users);
    }
}
