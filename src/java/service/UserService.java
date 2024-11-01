package service;

import static com.sun.source.util.DocTrees.instance;
import static com.sun.source.util.JavacTask.instance;
import static com.sun.source.util.Trees.instance;
import dao.UserDAO;
import model.User;

import java.util.List;

public class UserService {

    public static UserService getInstance() {
        UserService instance = null;
if (instance == null) {
        instance = new UserService();
    }
    return instance;    }
    private UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }

    public void registerUser(User user) {
        userDAO.insertUser(user);
    }

    public User loginUser(String email, String password) {
        return userDAO.getUserByEmailAndPassword(email, password);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }
}
