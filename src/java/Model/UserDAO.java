/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author DELL
 */


import Entity.User;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User("Admin", "admin@example.com", "adminpass", "admin"));
    }

    public User validateUser(String email, String password) {
        return users.stream()
                .filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public boolean isEmailRegistered(String email) {
        return users.stream().anyMatch(user -> user.getEmail().equals(email));
    }

    public void addUser(User user) {
        users.add(user);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    public User getUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    public void updateUser(User user) {
        users.replaceAll(u -> u.getEmail().equals(user.getEmail()) ? user : u);
    }
}

