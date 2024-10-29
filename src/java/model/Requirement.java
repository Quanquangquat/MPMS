/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author DELL
 */
public class Requirement {
    private int req_id;
    private String title;
    private int owner_id;
    private int complexity_id;
    private int status_id;
    private String description;
    private LocalDateTime created_at;
    private int created_by_id;

    public Requirement(int req_id, String title, int owner_id, int complexity_id, int status_id, String description, LocalDateTime created_at, int created_by_id) {
        this.req_id = req_id;
        this.title = title;
        this.owner_id = owner_id;
        this.complexity_id = complexity_id;
        this.status_id = status_id;
        this.description = description;
        this.created_at = created_at;
        this.created_by_id = created_by_id;
    }
    
    public Requirement(int req_id, String title) {
        this.req_id = req_id;
        this.title = title;
    }

    public int getReq_id() {
        return req_id;
    }

    public void setReq_id(int req_id) {
        this.req_id = req_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public int getComplexity_id() {
        return complexity_id;
    }

    public void setComplexity_id(int complexity_id) {
        this.complexity_id = complexity_id;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
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
    
    
}
