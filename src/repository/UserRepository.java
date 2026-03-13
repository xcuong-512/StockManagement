package repository;

import Model.User;
import java.util.List;

public interface UserRepository {
    List<User> loadUsers();
    void save(List<User> users);
    User findByName(String name);
}
