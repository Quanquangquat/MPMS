/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Entity.Issue;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import service.DBContext;

/**
 *
 * @author DELL
 */
public class IssueDAO extends DBContext{
    DBContext db = new DBContext();
    
    public List<Issue> getIssueList(){
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
}
