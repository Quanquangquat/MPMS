/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.*;
import java.util.List;
import model.*;

/**
 *
 * @author DELL
 */
public class RequirementService {

    private final RequirementDAO requirementDAO;

    // Constructor initializes SettingDAO instance
    public RequirementService() {
        requirementDAO = new RequirementDAO();
    }

    public Requirement getRequirementById(int reqId) {
        return requirementDAO.getRequirementById(reqId);
    }
    
    public List<Requirement> getRequirementList() {
        return requirementDAO.getRequirementList();
    }
    
}
