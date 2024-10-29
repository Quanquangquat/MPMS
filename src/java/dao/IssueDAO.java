/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Issue;
import service.DBContext;

/**
 *
 * @author DELL
 */
public class IssueDAO extends DBContext{
    DBContext db = new DBContext();

    public List<Issue> getIssueList() {
        List<Issue> t = new ArrayList<>();
        int xIssue_id, xType_id, xReq_id, xAssigner_id, xAssignee_id, xStatus,
                xCreated_by_id, xUpdated_by_id;
        String xTitle, xDescription;
        Date xDeadline;
        LocalDateTime xStatus_date, xCreated_at, xUpdated_at;
        String xSql = "select * from issue ";
        try {
            java.sql.PreparedStatement ps = conn.prepareStatement(xSql);
            //ps.setInt(1, xDebtor_id);
            ResultSet rs = ps.executeQuery();
            Issue x;
            while (rs.next()) {
                xIssue_id = rs.getInt("issue_id");
                xType_id = rs.getInt("type_id");
                xReq_id = rs.getInt("req_id");
                xAssigner_id = rs.getInt("assigner_id");
                xAssignee_id = rs.getInt("assignee_id");
                xStatus = rs.getInt("status");
                xCreated_by_id = rs.getInt("created_by_id");
                xUpdated_by_id = rs.getInt("updated_by_id");
                xTitle = rs.getString("title");
                xDescription = rs.getString("description");
                xDeadline = rs.getTimestamp("deadline");
                xStatus_date = rs.getTimestamp("status_date").toLocalDateTime();
                xCreated_at = rs.getTimestamp("created_at").toLocalDateTime();
                xUpdated_at = rs.getTimestamp("updated_at").toLocalDateTime();
                x = new Issue(xIssue_id, xTitle, xType_id, xReq_id, xAssigner_id,
                        xAssignee_id, xDeadline, xStatus, xStatus_date, xDescription,
                        xCreated_at, xCreated_by_id, xUpdated_at, xUpdated_by_id);
                t.add(x);
            }
            db.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public static void main(String[] args) {
        IssueDAO x = new IssueDAO();
        Issue a = x.getIssueById(3);
        if (a != null) {
            System.out.println("Oke");

        } else {
            System.out.println("None");
        }
    }

    public Issue getIssueById(int xIssue_id) {
        int xType_id, xReq_id, xAssigner_id, xAssignee_id, xStatus,
                xCreated_by_id, xUpdated_by_id;
        String xTitle, xDescription;
        Date xDeadline;
        LocalDateTime xStatus_date, xCreated_at, xUpdated_at;
        Issue x = null;
        String xSql = "select * from issue where issue_id = ?";
        ResultSet rs = null;
        java.sql.PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(xSql);
            ps.setInt(1, xIssue_id);

            rs = ps.executeQuery();
            if (rs.next()) {
                xType_id = rs.getInt("type_id");
                xReq_id = rs.getInt("req_id");
                xAssigner_id = rs.getInt("assigner_id");
                xAssignee_id = rs.getInt("assignee_id");
                xStatus = rs.getInt("status");
                xCreated_by_id = rs.getInt("created_by_id");
                xUpdated_by_id = rs.getInt("updated_by_id");
                xTitle = rs.getString("title");
                xDescription = rs.getString("description");
                xDeadline = rs.getTimestamp("deadline");
                xStatus_date = rs.getTimestamp("status_date").toLocalDateTime();
                xCreated_at = rs.getTimestamp("created_at").toLocalDateTime();
                xUpdated_at = rs.getTimestamp("updated_at").toLocalDateTime();
                x = new Issue(xIssue_id, xTitle, xType_id, xReq_id, xAssigner_id,
                        xAssignee_id, xDeadline, xStatus, xStatus_date, xDescription,
                        xCreated_at, xCreated_by_id, xUpdated_at, xUpdated_by_id);
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

    public int insertIssue(String xTitle, int xType_id, int xReq_id, int xAssigner_id,
            int xAssignee_id, Date xDeadline, int xStatus, LocalDateTime xStatus_date,
            String xDescription, LocalDateTime xCreated_at, int xCreated_by_id,
            LocalDateTime xUpdated_at, int xUpdated_by_id) {
        int a = 0;
        try {
            String xSql = "insert into Issue (title, type_id, req_id, "
                    + "assigner_id, assignee_id, deadline, description, "
                    + "created_by_id, updated_by_id)"
                    + " values (?,?,?,?,?,?,?,?,?)";
            java.sql.PreparedStatement ps = conn.prepareStatement(xSql);
            ps.setString(1, xTitle);
            ps.setInt(2, xType_id);
            ps.setInt(3, xReq_id);
            ps.setInt(4, xAssigner_id);
            ps.setInt(5, xAssignee_id);
            ps.setString(6, xDeadline.toString());
            ps.setInt(7, xStatus);
            ps.setString(8, xStatus_date.toString());
            ps.setString(9, xDescription);
            ps.setString(10, xCreated_at.toString());
            ps.setInt(11, xCreated_by_id);
            ps.setString(12, xUpdated_at.toString());
            ps.setInt(13, xUpdated_by_id);
            ps.executeUpdate();
            a = 1;
            db.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    public List<Issue> getIssueListByAssignTo(int xAssignee_id) {
        List<Issue> t = new ArrayList<>();
        String xTitle;
        Date xDeadline;
        int xStatus;
        LocalDateTime xCreated_at;

        String xSql = "SELECT * FROM issue\n"
                + "WHERE assignee_id = ?\n";

        try {
            java.sql.PreparedStatement ps = conn.prepareStatement(xSql);
            ps.setInt(1, xAssignee_id);

            ResultSet rs = ps.executeQuery();
            Issue x;
            while (rs.next()) {
                xTitle = rs.getString("title");
                xDeadline = rs.getTimestamp("deadline");
                xStatus = rs.getInt("status");
                xCreated_at = rs.getTimestamp("create_at").toLocalDateTime();
                x = new Issue(xTitle, xAssignee_id, xDeadline, xStatus, xCreated_at);
                t.add(x);
            }
            db.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public List<Issue> getIssueListByStatus(int xStatus) {
        List<Issue> t = new ArrayList<>();
        String xTitle;
        Date xDeadline;
        int xAssignee_id;
        LocalDateTime xCreated_at;

        String xSql = "SELECT * FROM issue\n"
                + "WHERE status = ?\n";

        try {
            java.sql.PreparedStatement ps = conn.prepareStatement(xSql);
            ps.setInt(1, xStatus);

            ResultSet rs = ps.executeQuery();
            Issue x;
            while (rs.next()) {
                xTitle = rs.getString("title");
                xDeadline = rs.getTimestamp("deadline");
                xAssignee_id = rs.getInt("assignee_id");
                xCreated_at = rs.getTimestamp("create_at").toLocalDateTime();
                x = new Issue(xTitle, xAssignee_id, xDeadline, xStatus, xCreated_at);
                t.add(x);
            }
            db.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public void updateIssue(int xIssue_id, String xTitle, int xType_id,
            int xReq_id, int xAssigner_id, int xAssignee_id, Date xDeadline,
            int xStatus, int xUpdated_by_id) {
        String xSql = "update issue\n"
                + "set title = ?, type_id = ?,"
                + "req_id = ?, assigner_id = ?, asignee_id = ?, deadline = ?,"
                + "status = ?, update_at=CURRENT_TIMESTAMP, updated_by_id = ? "
                + "where issue_id = ?";
        try {
            java.sql.PreparedStatement ps = conn.prepareStatement(xSql);
            ps.setString(1, xTitle);
            ps.setInt(2, xType_id);
            ps.setInt(3, xReq_id);
            ps.setInt(4, xAssigner_id);
            ps.setInt(5, xAssignee_id);
            ps.setString(6, xDeadline.toString());
            ps.setInt(7, xStatus);
            ps.setInt(8, xUpdated_by_id);
            ps.setInt(9, xIssue_id);
            ps.executeUpdate();
            db.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteIssue(int xIssue_id) {
        String xSql = "delete from issue where issue_id = ?";
        try {
            java.sql.PreparedStatement ps = conn.prepareStatement(xSql);
            ps.setInt(1, xIssue_id);
            ps.executeUpdate();
            db.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
