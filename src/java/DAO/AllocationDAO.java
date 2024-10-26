/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Allocation;
import java.util.Date;
import service.DBContext;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.jsp.jstl.sql.Result;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.Vector;
/**
 *
 * @author admin
 */
public class AllocationDAO extends DBContext{
    public static void main(String[] args) {
        AllocationDAO dao = new AllocationDAO();
//        int n = dao.addStaff(new Allocation(13, "FDemo", "LDemo", "EDemo1", "PDemo", 1, 2, 7));
        // int n = dao.insertStaff(new Staff(13, "FDemo", "LDemo",
        //         "EDemo2", "PDemo", 1, 2, 7));
//        int n = dao.updateStaff(new Staff(13,
//                "FDemo1", "LDemo1",
//                "EDemo2", "PDemo1", 1, 2, 7));
//        if (n > 0) {
//            System.out.println("updated");
//        }
        dao.listAllAllocations();
//      Vector<Allocation> vector=dao.getAllocation("select * from allocation");
//      String fname="la";
//      Vector<Allocation> vector=dao.getAllocation("select * from allocation "
//              + " where first_name like '%"+fname+"%'");
//      for(Allocation allocation:vector){
//          System.out.println(allocation);
    }
    public Vector<Allocation> getAllocation(String sql) {
        Vector<Allocation> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                // Extract values from result set
                int memberId = rs.getInt("member_id");
                int projectId = rs.getInt("project_id");
                int roleId = rs.getInt("role_id");
                Date fromDate = rs.getDate("from_date");
                Date toDate = rs.getDate("to_date");
                int rate = rs.getInt("rate");
                String description = rs.getString("description");
                int status = rs.getInt("status");
                java.sql.Timestamp createdAt = rs.getTimestamp("created_at");
                int createdById = rs.getInt("created_by_id");
                java.sql.Timestamp updatedAt = rs.getTimestamp("updated_at");
                int updatedById = rs.getInt("updated_by_id");

                // Create an Allocation object and add to vector
                Allocation allocation = new Allocation(memberId, projectId, roleId, fromDate, toDate, rate, description, status, LocalDateTime.MAX, createdById, LocalDateTime.MAX, updatedById);
                vector.add(allocation);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    // Method to list all Allocation records
    public void listAllAllocations() {
        String sql = "SELECT * FROM allocation";
        try {
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                // Extract values from result set
                int memberId = rs.getInt("member_id");
                int projectId = rs.getInt("project_id");
                int roleId = rs.getInt("role_id");
                Date fromDate = rs.getDate("from_date");
                Date toDate = rs.getDate("to_date");
                int rate = rs.getInt("rate");
                String description = rs.getString("description");
                int status = rs.getInt("status");
                java.sql.Timestamp createdAt = rs.getTimestamp("created_at");
                int createdById = rs.getInt("created_by_id");
                java.sql.Timestamp updatedAt = rs.getTimestamp("updated_at");
                int updatedById = rs.getInt("updated_by_id");

                // Create an Allocation object and print it
                Allocation allocation = new Allocation(memberId, projectId, roleId, fromDate, toDate, rate, description, status, LocalDateTime.MAX, createdById, LocalDateTime.MAX, updatedById);
                System.out.println(allocation);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public int addAllocation(Allocation allocation) {
    int n = 0;
    String sql = "INSERT INTO allocation " +
                 "(member_id, project_id, role_id, from_date, to_date, rate, description, status) " +
                 "VALUES (" +
                 allocation.getMemberId() + ", " +
                 allocation.getProjectId() + ", " +
                 allocation.getRoleId() + ", " +
                 "'" + allocation.getFromDate() + "', " +  // Giả sử từ ngày là kiểu DATE
                 "'" + allocation.getToDate() + "', " +    // Giả sử đến ngày là kiểu DATE
                 allocation.getRate() + ", " +
                 "'" + allocation.getDescription() + "', " +
                 allocation.getStatus() + 
                 ")";
    try {
        Statement statement = conn.createStatement();
        n = statement.executeUpdate(sql);
    } catch (SQLException ex) {
        Logger.getLogger(AllocationDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return n;
}

public int removeAllocation(int memberId, int projectId, int roleId) {
    int n = 0;
    String sql = "DELETE FROM allocation WHERE member_id = ? AND project_id = ? AND role_id = ?";
    
    try {
        // Kiểm tra nếu có đơn đặt hàng liên quan trước khi xóa
        ResultSet rs = getData("SELECT * FROM orders WHERE member_id = ? AND project_id = ?", memberId, projectId);
        if (rs.next()) { // Nếu có đơn đặt hàng liên quan
            // Bạn có thể thực hiện hành động khác như thay đổi trạng thái
            // Ví dụ: changeActive(memberId, 0);
            return n; // Không xóa nếu có đơn hàng liên quan
        }
        
        // Sử dụng PreparedStatement để xóa allocation
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, memberId);
        preparedStatement.setInt(2, projectId);
        preparedStatement.setInt(3, roleId);
        
        n = preparedStatement.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(AllocationDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return n;
}
public int insertAllocation(Allocation allocation) {
    int n = 0;
    String sql = "INSERT INTO allocation (member_id, project_id, role_id, from_date, to_date, rate, description, status, created_by_id) "
               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try {
        PreparedStatement pre = conn.prepareStatement(sql);
        // Set value for parameters (index starts from 1)
        pre.setInt(1, allocation.getMemberId());
        pre.setInt(2, allocation.getProjectId());
        pre.setInt(3, allocation.getRoleId());
        pre.setDate(4, (java.sql.Date) allocation.getFromDate()); // Assuming from_date is of type java.sql.Date
        pre.setDate(5, (java.sql.Date) allocation.getToDate());   // Assuming to_date is of type java.sql.Date
        pre.setInt(6, allocation.getRate());
        pre.setString(7, allocation.getDescription());
        pre.setInt(8, allocation.getStatus());
        pre.setInt(9, allocation.getCreatedById());

        n = pre.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return n;
}
public int updateAllocation(Allocation allocation) {
    int n = 0;
    String sql = "UPDATE allocation "
               + "SET from_date = ?, "
               + "to_date = ?, "
               + "rate = ?, "
               + "description = ?, "
               + "status = ?, "
               + "updated_by_id = ? "
               + "WHERE member_id = ? AND project_id = ? AND role_id = ?";
    
    try {
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.setDate(1, (java.sql.Date) allocation.getFromDate());
        pre.setDate(2, (java.sql.Date) allocation.getToDate());
        pre.setInt(3, allocation.getRate());
        pre.setString(4, allocation.getDescription());
        pre.setInt(5, allocation.getStatus());
        pre.setInt(6, allocation.getUpdatedById());
        pre.setInt(7, allocation.getMemberId());
        pre.setInt(8, allocation.getProjectId());
        pre.setInt(9, allocation.getRoleId());
        
        n = pre.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return n; // Trả về số bản ghi đã cập nhật
}

}