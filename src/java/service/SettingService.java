/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.SettingDAO;
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

    // Business logic to insert new setting
    public boolean insertSetting(Setting setting) {
        // Validate setting data (if needed)
        return settingDAO.insertSetting(setting) > 0;
    }

    // Business logic to update a setting
    public boolean updateSetting(Setting setting) {
        return settingDAO.updateSetting(setting) > 0;
    }

    // Business logic to remove a setting
    public boolean removeSetting(int id) {
        return settingDAO.RemoveSetting(id) > 0;
    }

    // Other business logic methods specific to settings
    public boolean deactivateSetting(int id) {
        return settingDAO.changeActive(id, "inactive") > 0;
    }
}

