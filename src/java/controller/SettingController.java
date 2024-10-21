/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Entity.Setting;
import Model.SettingDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;

/**
 *
 * @author admin
 */
@WebServlet(name = "SettingController", urlPatterns = {"/SettingController"})
public class SettingController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        SettingDAO dao = new SettingDAO();
        String service = request.getParameter("service");
        
        if (service == null) { // gọi trực tiếp servlet
            service = "listSetting";
        }

        try (PrintWriter out = response.getWriter()) {
            if (service.equals("removeSetting")) {
                int setting_id = Integer.parseInt(request.getParameter("settingId"));
                int n = dao.RemoveSetting(setting_id);
                response.sendRedirect("URLSetting");
            }

            if (service.equals("updateSetting")) {
                String submit = request.getParameter("submit");
                if (submit == null) { // hiển thị form
                    int setting_id = Integer.parseInt(request.getParameter("settingId"));
                    Vector<Setting> vector = dao.getSetting("SELECT * FROM settings WHERE setting_id=" + setting_id);
                    request.setAttribute("data", vector);
                    dao.dispatch(request, response, "/jsp/updateSetting.jsp");
                } else { // xử lý cập nhật
                    int setting_id = Integer.parseInt(request.getParameter("setting_id"));
                    String name = request.getParameter("name"),
                           value = request.getParameter("value"),
                           type_id = request.getParameter("type_id"),
                           status = request.getParameter("status");
                    int priority = Integer.parseInt(request.getParameter("priority"));
                    
                    Setting setting = new Setting(setting_id, name, value, setting_id, priority, status, service, setting_id, setting_id);
                    int n = dao.updateSetting(setting);
                    response.sendRedirect("URLSetting");
                }
            }

            if (service.equals("addSetting")) {
                String submit = request.getParameter("submit");
                if (submit == null) { // hiển thị form
                    dao.dispatch(request, response, "/jsp/insertSetting.jsp");
                } else { // xử lý thêm
                    String name = request.getParameter("name"),
                           value = request.getParameter("value"),
                           type_id = request.getParameter("type_id"),
                           status = request.getParameter("status");
                    int priority = Integer.parseInt(request.getParameter("priority"));
                    
                    Setting setting = new Setting(priority, name, value, priority, priority, status, service, priority, priority); // ID sẽ được tự động tăng
                    int n = dao.addSetting(setting);
                    response.sendRedirect("URLSetting");
                }
            }

            if (service.equals("listSetting")) { // xử lý yêu cầu liệt kê
                String submit = request.getParameter("submit");
                Vector<Setting> vector;
                if (submit == null) { // liệt kê tất cả
                    vector = dao.getSetting("SELECT * FROM setting");
                } else { // tìm kiếm
                    String name = request.getParameter("name");
                    vector = dao.getSetting("SELECT * FROM setting WHERE name LIKE '%" + name + "%'");
                }

                // gửi dữ liệu đến view
                request.setAttribute("data", vector);
                request.setAttribute("tableTitle", "List of Settings");
                request.setAttribute("pageTitle", "Settings Management");
                
                // gọi jsp (view)
                RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/ListSettings.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
