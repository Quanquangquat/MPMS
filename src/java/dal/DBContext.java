/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * object to access database
 * @author Admin
 */
public class DBContext {
    public Connection connection;
    protected PreparedStatement ps;
    protected ResultSet rs;
    public DBContext() {
        try {
            String user = "root",
                    password = "qazplm1234567890",
                    url = "jdbc:mysql://localhost:3306/mpms";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            if(connection==null){
                System.out.println("Loi");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        new DBContext();
    }
}
