package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.User;
import service.DBContext;

public class UserDAO {
    private final DBContext db;

    // Constructor để khởi tạo DBContext
    public UserDAO() {
        this.db = DBContext.getInstance(); // Lấy instance của DBContext
    }

    // Phương thức validateUser để xác thực người dùng
    public User validateUser(String email, String password) {
        List<User> users = getAllUsers(); // Giả lập lấy người dùng từ cơ sở dữ liệu
        return users.stream()
                .filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    // Kiểm tra xem email đã đăng ký hay chưa
    public boolean isEmailRegistered(String email) {
        return getAllUsers().stream().anyMatch(user -> user.getEmail().equals(email));
    }

    // Thêm người dùng mới
    public void addUser(User user) {
        // Thêm vào cơ sở dữ liệu thật (ví dụ) hoặc một danh sách mẫu nếu cần
    }

    // Lấy tất cả người dùng từ cơ sở dữ liệu
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                users.add(new User(
                        rs.getString("full_name"),
                        rs.getString("user_name"),
                        rs.getString("email"),
                        rs.getString("password")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    // Lấy người dùng bằng email
    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email = ?";
        User user = null;
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User(
                            rs.getString("full_name"),
                            rs.getString("user_name"),
                            rs.getString("email"),
                            rs.getString("password")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    // Lấy người dùng theo ID từ cơ sở dữ liệu
    public User getUserById(int userId) {
        String sql = "SELECT * FROM user WHERE user_id = ?";
        User user = null;
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User(
                            rs.getInt("user_id"),
                            rs.getString("full_name"),
                            rs.getInt("role_id"),
                            rs.getInt("dep_id"),
                            rs.getInt("status")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    
    // Cập nhật người dùng
    public void updateUser(User user) {
        // Giả lập cập nhật người dùng hoặc thêm vào cơ sở dữ liệu nếu cần
    }

    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        User user = dao.getUserById(1);
        if (user != null) {
            System.out.println("User name: " + user.getFullName());
        } else {
            System.out.println("User không tồn tại.");
        }
    }
}
