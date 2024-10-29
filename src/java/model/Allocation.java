/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author admin
 */
public class Allocation {
    private int memberId;
    private int projectId;
    private int roleId;
    private Date fromDate;
    private Date toDate;
    private int rate;
    private String description;
    private int status;
    private LocalDateTime createdAt;
    private int createdById;
    private LocalDateTime updatedAt;
    private int updatedById;

    public Allocation() {
    }

    public Allocation(int memberId, int projectId, int roleId, Date fromDate, Date toDate, int rate, String description, int status, LocalDateTime createdAt, int createdById, LocalDateTime updatedAt, int updatedById) {
        this.memberId = memberId;
        this.projectId = projectId;
        this.roleId = roleId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.rate = rate;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.createdById = createdById;
        this.updatedAt = updatedAt;
        this.updatedById = updatedById;
    }
    
    public Allocation(int memberId, int projectId, int roleId, int status) {
        this.memberId = memberId;
        this.projectId = projectId;
        this.roleId = roleId;
        this.status = status;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getCreatedById() {
        return createdById;
    }

    public void setCreatedById(int createdById) {
        this.createdById = createdById;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(int updatedById) {
        this.updatedById = updatedById;
    }

    @Override
    public String toString() {
        return "Allocation{" + "memberId=" + memberId + ", projectId=" + projectId + ", roleId=" + roleId + ", fromDate=" + fromDate + ", toDate=" + toDate + ", rate=" + rate + ", description=" + description + ", status=" + status + ", createdAt=" + createdAt + ", createdById=" + createdById + ", updatedAt=" + updatedAt + ", updatedById=" + updatedById + '}';
    }
    
}
