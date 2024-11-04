/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Requirement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mituz
 */
public class RequirementDAO extends DBContext{
    public List<Requirement> getAllRequirements() {
        List<Requirement> requirements = new ArrayList<>();       
        try{
            String sql = "SELECT * FROM requirement";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Requirement requirement = new Requirement();
                requirement.setReqId(rs.getInt("req_id"));
                requirement.setTitle(rs.getString("title"));
                requirement.setOwnerId(rs.getInt("owner_id"));
                requirement.setComplexityId(rs.getInt("complexity_id"));
                requirement.setStatusId(rs.getInt("status_id"));
                requirement.setDescription(rs.getString("description"));
                requirements.add(requirement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requirements;
    }
    
    public Requirement getRequirementById(int reqId) {
        Requirement requirement = null;
        
        try{
            String sql = "SELECT * FROM requirement WHERE req_id = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, reqId);
            rs = ps.executeQuery();
            if (rs.next()) {
                requirement = new Requirement();
                requirement.setReqId(rs.getInt("req_id"));
                requirement.setTitle(rs.getString("title"));
                requirement.setOwnerId(rs.getInt("owner_id"));
                requirement.setComplexityId(rs.getInt("complexity_id"));
                requirement.setStatusId(rs.getInt("status_id"));
                requirement.setDescription(rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requirement;
    }
}
