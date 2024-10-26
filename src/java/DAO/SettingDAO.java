/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import model.Setting;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.security.Timestamp;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import service.DBContext;

/**
 *
 * @author DELL
 */
public class SettingDAO extends DBContext{
    public static void main(String[] args) {
        SettingDAO dao = new SettingDAO();
//        int n = dao.addSetting(new Setting("NDemo", "VDemo",1,0,0,));
        // int n = dao.insertStaff(new Staff(13, "FDemo", "LDemo",
        //         "EDemo2", "PDemo", 1, 2, 7));
//        int n = dao.updateStaff(new Staff(13,
//                "FDemo1", "LDemo1",
//                "EDemo2", "PDemo1", 1, 2, 7));
//        if (n > 0) {
//            System.out.println("updated");
//        }
        dao.listAll();
//      //Vector<Staff> vector=dao.getStaff("select * from staffs");
//      String fname="la";
//      Vector<Staff> vector=dao.getStaff("select * from staffs "
//              + " where first_name like '%"+fname+"%'");
//      for(Staff staf:vector){
//          System.out.println(staf);
    }
    public Vector<Setting> getSetting(String sql) {
    Vector<Setting> vector = new Vector<>();
    try {
        Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = state.executeQuery(sql);
        while (rs.next()) {
            int setting_id = rs.getInt(1);
            String name = rs.getString(2),
                   value = rs.getString(3);
            int type_id = rs.getInt(4),
                priority = rs.getInt(5);
            String status = rs.getString(6),
                   description = rs.getString(7);
            java.sql.Timestamp created_at = rs.getTimestamp(8);
            int created_by_id = rs.getInt(9);
            java.sql.Timestamp updated_at = rs.getTimestamp(10);
            int updated_by_id = rs.getInt(11);

            Setting setting = new Setting(setting_id, name, value, type_id, priority, status, description, created_by_id, updated_by_id);
            
            vector.add(setting);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return vector;
}
    
    public void listAll() {
        String sql = "select * from setting";
        try {
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int setting_id = rs.getInt(1);
                String name = rs.getString(2),
                        value = rs.getString(3);
                int type_id = rs.getInt(4),
                        priority = rs.getInt(5);
                String status = rs.getString(6),
                        description = rs.getString(7);
                int created_by_id = rs.getInt(8),
                     updated_by_id = rs.getInt(9);
                Setting setting = new Setting(setting_id, name, value, type_id, priority, status, description, created_by_id, updated_by_id);
                System.out.println(setting);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }  
    public int insertSetting(Setting setting) {
    int n = 0;
    String sql = "INSERT INTO `settings` "
               + "(`name`, `value`, `type_id`, `priority`, `status`, `description`, "
               + "`created_at`, `created_by_id`, `updated_at`, `updated_by_id`) "
               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try {
        PreparedStatement pre = conn.prepareStatement(sql);
       
        pre.setString(1, setting.getName());
        pre.setString(2, setting.getValue());
        pre.setInt(3, setting.getType_id());
        pre.setInt(4, setting.getPriority());
        pre.setString(5, setting.getStatus());
        pre.setString(6, setting.getDescription());
        pre.setInt(7, setting.getCreatedById());
        pre.setInt(8, setting.getUpdatedById());

        
        n = pre.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return n;
}
public int updateSetting(Setting setting) {
    int n = 0;
    String sql = "UPDATE `settings` "
               + "SET `name` = ?, "
               + "`value` = ?, "
               + "`type_id` = ?, "
               + "`priority` = ?, "
               + "`status` = ?, "
               + "`description` = ?, "
               + "`updated_at` = ?, "
               + "`updated_by_id` = ? "
               + "WHERE `setting_id` = ?";
    try {
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.setString(1, setting.getName());
        pre.setString(2, setting.getValue());
        pre.setInt(3, setting.getType_id());
        pre.setInt(4, setting.getPriority());
        pre.setString(5, setting.getStatus());
        pre.setString(6, setting.getDescription());
        pre.setInt(7, setting.getUpdatedById());
        pre.setInt(8, setting.getSetting_id());

        n = pre.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return n;
}
public int addSetting(Setting setting) {
    int n = 0;
    String sql = "INSERT INTO `settings` "
               + "(`setting_id`, `name`, `value`, `type_id`, `priority`, `status`, "
               + "`description`, `created_at`, `created_by_id`) "
               + "VALUES (" 
               + setting.getSetting_id() + ", '"
               + setting.getName() + "', '"
               + setting.getValue() + "', "
               + setting.getType_id() + ", "
               + setting.getPriority() + ", '"
               + setting.getStatus() + "', '"
               + setting.getDescription() + "', '"
               + setting.getCreatedById()+ ")";
//                System.out.println(sql);
    try {
        Statement statement = conn.createStatement();
        n = statement.executeUpdate(sql);
    } catch (SQLException ex) {
        Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return n;
}
public int RemoveSetting(int id) {
    int n = 0;
    String sql = "DELETE FROM settings WHERE setting_id=" + id;
    try {
        // Kiểm tra khóa ngoại trong bảng liên quan (ví dụ: orders hoặc bảng khác)
        ResultSet rs = getData("SELECT * FROM orders WHERE setting_id=" + id);
        if (rs.next()) { // tồn tại khóa ngoại
            // Thay đổi trạng thái active thành inactive
            changeActive(id, "inactive");
            return n; // Không thực hiện xóa, chỉ thay đổi trạng thái
        }
        
        // Nếu không tồn tại khóa ngoại, thực hiện xóa bản ghi
        Statement statement = conn.createStatement();
        n = statement.executeUpdate(sql);
    } catch (SQLException ex) {
        Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, "Error removing setting with ID: " + id, ex);
    }
    return n;
}
public int changeActive(int id, String status) {
    int n = 0;
    String sql = "UPDATE settings SET status = ? WHERE setting_id = ?";
    try {
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.setString(1, status);
        pre.setInt(2, id);
        n = pre.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return n;
}
 public void forwardToJSP(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
