/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import dao.AllocationDAO;
import java.sql.ResultSet;
import java.util.List;
import model.Allocation;

/**
 *
 * @author DELL
 */
public class AllocationService {
    private final AllocationDAO allocationDAO;

    // Constructor initializes SettingDAO instance
    public AllocationService() {
        allocationDAO = new AllocationDAO();
    }

    public List<Allocation> getUserIdListByPro(int projectid) {
        return allocationDAO.getUserIdListByPro(projectid);
    }
    
    public int getProjectIdByUser(int userid) {
        return allocationDAO.getProjectIdByUser(userid);
    }
}
