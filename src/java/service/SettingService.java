/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.SettingDAO;
import java.util.List;
import java.util.Vector;
import model.Setting;

/**
 *
 * @author admin
 */
public class SettingService {



    // Instance of SettingDAO to access data layer
    private final SettingDAO settingDAO;

    // Constructor initializes SettingDAO instance
    public SettingService() {
        settingDAO = new SettingDAO();
    }

    // Business logic to get settings
    public Vector<Setting> getAllSettings() {
        String query = "SELECT * FROM settings";
        return settingDAO.getSetting(query);
    }

    public boolean insertSetting(Setting setting) {
        return settingDAO.insertSetting(setting) > 0;
    }

    public boolean updateSetting(Setting setting) {
        return settingDAO.updateSetting(setting) > 0;
    }

    public boolean removeSetting(int id) {
        return settingDAO.RemoveSetting(id) > 0;
    }

    public boolean deactivateSetting(int id) {
        return settingDAO.changeActive(id, "inactive") > 0;
    }

     public List<Setting> getIssueTypeListById(int typeid) {
        return settingDAO.getIssueTypeListById(typeid);
    }
    
    public Setting getSettingById(int settingid) {
        return settingDAO.getSettingById(settingid);
    }
    public Vector<Setting> getSetting(String sql){
        return settingDAO.getSetting(sql);
    }
}

