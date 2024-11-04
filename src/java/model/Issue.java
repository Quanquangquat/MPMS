package model;

import java.util.Date;

public class Issue {
    private int issueId;
    private String title;
    private int typeId;
    private int reqId;
    private int assignerId;
    private int assigneeId;
    private Date deadline;
    private String status;
    private Date statusDate;
    private String description;

    public Issue() {
    }

    public Issue(int issueId, String title, int typeId, int reqId, int assignerId, int assigneeId, Date deadline, String status, Date statusDate, String description) {
        this.issueId = issueId;
        this.title = title;
        this.typeId = typeId;
        this.reqId = reqId;
        this.assignerId = assignerId;
        this.assigneeId = assigneeId;
        this.deadline = deadline;
        this.status = status;
        this.statusDate = statusDate;
        this.description = description;
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getReqId() {
        return reqId;
    }

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }

    public int getAssignerId() {
        return assignerId;
    }

    public void setAssignerId(int assignerId) {
        this.assignerId = assignerId;
    }

    public int getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(int assigneeId) {
        this.assigneeId = assigneeId;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
}
