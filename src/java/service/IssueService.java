/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.IssueDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import model.Issue;

/**
 *
 * @author DELL
 */
public class IssueService {
    private final IssueDAO issueDAO;

    // Constructor initializes SettingDAO instance
    public IssueService() {
        issueDAO = new IssueDAO();
    }
    
    public List<Issue> getIssueList() {
        return issueDAO.getIssueList();
    }
    
    public List<Issue> getIssueListByPId(int projectid) {
        return issueDAO.getIssueListByPId(projectid);
    }
    
    public List<Issue> searchIssue(String xSearch) {
        return issueDAO.searchIssueByTitle(xSearch);
    }
    
    public List<Issue> searchIssue1(String xSearch, int projectid) {
        return issueDAO.searchIssueByTitleAProjectId(xSearch, projectid);
    }
    
    public List<Issue> getIssueListByFilter(Integer xAssignee_id, Integer xStatus) {
        return issueDAO.getIssueListByFilter(xAssignee_id, xStatus);
    }
    
    public List<Issue> getIssueListByFilter1(Integer xAssignee_id, Integer xStatus, int projectid) {
        return issueDAO.getIssueListByFilter1(xAssignee_id, xStatus, projectid);
    }

    public List<Issue> getIssueListByAssignee(int assigneeId) {
        return issueDAO.getIssueListByAssignTo(assigneeId);
    }

    public List<Issue> getIssueListByStatus(int status) {
        return issueDAO.getIssueListByStatus(status);
    }
    
     public Issue getIssueById(int issueId) {
        return issueDAO.getIssueById(issueId);
    }

    public boolean insertIssue(Issue issue) {
        // Validate setting data (if needed)
        return issueDAO.insertIssue(issue) > 0;
    }

    public boolean updateIssue(Issue issue) {
        return issueDAO.updateIssue(issue) > 0;
    }

    public boolean removeIssue(int id) {
        return issueDAO.deleteIssue(id) > 0;
    }
    
    public boolean validateString(String value) {
        return value != null && !value.trim().isEmpty(); 
    }
    
    public boolean validateInt(String value) {
        try {
            Integer.parseInt(value);  
            return true;              
        } catch (NumberFormatException e) {
            return false;             
        }
    }
    
    public boolean validateIssue(Issue issue) {
        return issue != null; 
        
    }
    
    public boolean validateLocalDateTime(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // định dạng mong muốn
        try {
            LocalDateTime.parse(input, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    public boolean validateDate(String input) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setLenient(false); 
        try {
            Date date = formatter.parse(input); 
            return true;
        } catch (ParseException e) {
            return false; 
        }
    }

}
