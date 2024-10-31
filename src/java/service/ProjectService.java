/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.ProjectDAO;
import model.Project;

/**
 *
 * @author DELL
 */
public class ProjectService {
    private final ProjectDAO projectDAO;

    // Constructor initializes SettingDAO instance
    public ProjectService() {
        projectDAO = new ProjectDAO();
    }

    public Project getProjectById(int projectId) {
        return projectDAO.getProjectById(projectId);
    }
}
