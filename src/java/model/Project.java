/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class Project {
    private int project_id;
    private String name;
    private String code;
    private Date start_date;
    private Date end_date;
    private int dept_id;
    private int status;
    private String description;
    private LocalDateTime created_at;
    private int created_by_id;
    private LocalDateTime updated_at;
    private int updated_by_id;

    public Project(int project_id, String name, int status) {
        this.project_id = project_id;
        this.name = name;
        this.status = status;
    }

    public Project(int project_id, String name, String code, Date start_date, Date end_date, int dept_id, int status, String description, LocalDateTime created_at, int created_by_id, LocalDateTime updated_at, int updated_by_id) {
        this.project_id = project_id;
        this.name = name;
        this.code = code;
        this.start_date = start_date;
        this.end_date = end_date;
        this.dept_id = dept_id;
        this.status = status;
        this.description = description;
        this.created_at = created_at;
        this.created_by_id = created_by_id;
        this.updated_at = updated_at;
        this.updated_by_id = updated_by_id;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public int getDept_id() {
        return dept_id;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public int getCreated_by_id() {
        return created_by_id;
    }

    public void setCreated_by_id(int created_by_id) {
        this.created_by_id = created_by_id;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public int getUpdated_by_id() {
        return updated_by_id;
    }

    public void setUpdated_by_id(int updated_by_id) {
        this.updated_by_id = updated_by_id;
    }
    
    
}
