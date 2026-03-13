package repository;

import Model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonUserRepository implements UserRepository{

    private final String FILE = "src/data/user.json";

    @Override
    public List<User> loadUsers() {
        List<User> users = new ArrayList<>();

        try {
            File file = new File(FILE);

            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            BufferedReader reader = new BufferedReader(new FileReader(FILE));
            String line;
            while((line = reader.readLine()) != null){
                String [] data = line.split(",");
                users.add(new User(
                        data[0], data[1], data[2]
                ));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void save(List<User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE))){
            for (User user : users){
                writer.write(
                        user.getUsername() + "," + user.getPassword() + "," + user.getRole()
                );
                writer.newLine();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public User findByName(String name) {
        List<User> users = loadUsers();
        for (User user : users){
            if (user.getUsername().equals(name)){
                return user;
            }
        }
        return null;
    }
}
