/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Issue;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mituz
 */
public class IssueDAO extends DBContext {

    public List<Issue> getIssuesByRequirementId(int requirementId) {
        List<Issue> issues = new ArrayList<>();

        try {
            String sql = "SELECT * FROM issue WHERE req_id = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, requirementId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Issue issue = new Issue();
                issue.setIssueId(rs.getInt("issue_id"));
                issue.setTitle(rs.getString("title"));
                issue.setTypeId(rs.getInt("type_id"));
                issue.setReqId(rs.getInt("req_id"));
                issue.setAssignerId(rs.getInt("assigner_id"));
                issue.setAssigneeId(rs.getInt("assignee_id"));
                issue.setDeadline(rs.getDate("deadline"));
                issue.setStatusDate(rs.getDate("status_date"));
                issue.setDescription(rs.getString("description"));
                issues.add(issue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return issues;
    }

    public List<Issue> getAllIssues() {
        List<Issue> issues = new ArrayList<>();

        try {
            String sql = "SELECT * FROM issue";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Issue issue = new Issue();
                issue.setIssueId(rs.getInt("issue_id"));
                issue.setTitle(rs.getString("title"));
                issue.setDescription(rs.getString("description"));
                issue.setDeadline(rs.getDate("deadline"));
                issues.add(issue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return issues;
    }

    public boolean addIssue(Issue issue) {
        String sql = "INSERT INTO issue (title, type_id, req_id, assigner_id, assignee_id, deadline, status, description) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, issue.getTitle());
            ps.setInt(2, issue.getTypeId());
            ps.setInt(3, issue.getReqId());
            ps.setInt(4, issue.getAssignerId());
            ps.setInt(5, issue.getAssigneeId());
            ps.setDate(6, new java.sql.Date(issue.getDeadline().getTime()));
            ps.setString(7, issue.getStatus());
            ps.setString(8, issue.getDescription());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Issue getIssueById(int issueId) {
        Issue issue = null;
        try {
            String sql = "SELECT * FROM issue WHERE issue_id = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, issueId);
            rs = ps.executeQuery();
            if (rs.next()) {
                issue = new Issue();
                issue.setIssueId(rs.getInt("issue_id"));
                issue.setTitle(rs.getString("title"));
                issue.setDescription(rs.getString("description"));
                issue.setDeadline(rs.getDate("deadline"));
                issue.setTypeId(rs.getInt("type_id"));
                issue.setReqId(rs.getInt("req_id"));
                issue.setAssignerId(rs.getInt("assigner_id"));
                issue.setAssigneeId(rs.getInt("assignee_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return issue;
    }

    public static void main(String[] args) {
        IssueDAO dao = new IssueDAO();
        System.out.println(dao.getIssuesByRequirementId(11));
    }

}
