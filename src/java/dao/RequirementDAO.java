/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Requirement;
import service.DBContext;

/**
 *
 * @author DELL
 */
public class RequirementDAO extends DBContext{
    DBContext db = new DBContext();

    public List<Requirement> getRequirementList() {
        List<Requirement> t = new ArrayList<>();
        int xReq_id, xOwner_id, xComplexity_id, xStatus_id, xCreated_by_id;
        String xTitle, xDescription;
        LocalDateTime xCreated_at;
        String xSql = "select * from requirement ";
        try {
            java.sql.PreparedStatement ps = conn.prepareStatement(xSql);
            ResultSet rs = ps.executeQuery();
            Requirement x;
            while (rs.next()) {
                xReq_id = rs.getInt("req_id");
                xOwner_id = rs.getInt("owner_id");
                xComplexity_id = rs.getInt("complexity_id");
                xStatus_id = rs.getInt("status_id");
                xCreated_by_id = rs.getInt("updated_by_id");
                xTitle = rs.getString("title");
                xDescription = rs.getString("description");
                xCreated_at = rs.getTimestamp("created_at").toLocalDateTime();
                x = new Requirement(xReq_id, xTitle, xOwner_id, xComplexity_id, xStatus_id, xDescription, xCreated_at, xCreated_by_id);
                t.add(x);
            }
            db.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }
    
    public Requirement getRequirementById(int xReq_id) {
        String xTitle;
        Requirement x = null;
        String xSql = "select * from requirement where req_id = ?";
        ResultSet rs = null;
        java.sql.PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(xSql);
            ps.setInt(1, xReq_id);

            rs = ps.executeQuery();
            if (rs.next()) {
                xTitle = rs.getString("title");
                x = new Requirement(xReq_id, xTitle);
            }

            db.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                db.closeConnection();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return (x);
    }
    
    public static void main(String[] args) {
        RequirementDAO a = new RequirementDAO();
        List<Requirement> lst = a.getRequirementList();
        System.out.println(lst.size());
    }
}
