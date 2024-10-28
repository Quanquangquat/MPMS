/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;


import dao.SettingDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import java.sql.SQLException;
import model.Setting;

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

        if (service == null) { // Nếu không có tham số "service", gọi listSetting mặc định
            service = "listSetting";
        }

        try (PrintWriter out = response.getWriter()) {
            if (service.equals("removeSetting")) {
                int setting_id = Integer.parseInt(request.getParameter("setting_id"));
                dao.RemoveSetting(setting_id);  // Xoá setting
                response.sendRedirect("listSetting");  // Điều hướng về danh sách setting
            }

            if (service.equals("updateSetting")) {
                String submit = request.getParameter("submit");
                if (submit == null) { // Hiển thị form cập nhật
                    int setting_id = Integer.parseInt(request.getParameter("setting_id"));
                    Vector<Setting> vector = dao.getSetting("SELECT * FROM setting WHERE setting_id=" + setting_id);
                    request.setAttribute("data", vector);
                    dao.forwardToJSP(request, response, "/jsp/updateSetting.jsp");
                } else { // Xử lý cập nhật
                    int setting_id = Integer.parseInt(request.getParameter("setting_id"));
                    String name = request.getParameter("name"),
                            value = request.getParameter("value"),
                            status = request.getParameter("status");
                    int type_id = Integer.parseInt(request.getParameter("type_id"));
                    int priority = Integer.parseInt(request.getParameter("priority"));

                    Setting setting = new Setting(setting_id, name, value, type_id, priority, status, null, setting_id, setting_id);
                    dao.updateSetting(setting);  // Cập nhật setting
                    response.sendRedirect("listSetting");  // Điều hướng về danh sách setting
                }
            }

            if (service.equals("addSetting")) {
                String submit = request.getParameter("submit");
                if (submit == null) { // Hiển thị form thêm setting mới
                    dao.forwardToJSP(request, response, "/jsp/insertSetting.jsp");
                } else { // Xử lý thêm mới setting
                    String name = request.getParameter("name"),
                            value = request.getParameter("value"),
                            status = request.getParameter("status");
                    int type_id = Integer.parseInt(request.getParameter("type_id"));
                    int priority = Integer.parseInt(request.getParameter("priority"));

                    Setting setting = new Setting(0, name, value, type_id, priority, status, null, 0, 0);
                    dao.addSetting(setting);  // Thêm setting mới
                    response.sendRedirect("listSetting");  // Điều hướng về danh sách setting
                }
            }

            if (service.equals("listSetting")) { // Xử lý yêu cầu liệt kê
                String submit = request.getParameter("submit");
                Vector<Setting> vector;
                if (submit == null) { // Liệt kê tất cả
                    vector = dao.getSetting("SELECT * FROM setting");
                } else { // Tìm kiếm theo tên
                    String name = request.getParameter("name");
                    vector = dao.getSetting("SELECT * FROM setting WHERE name LIKE '%" + name + "%'");
                }

                // Gửi dữ liệu đến view
                request.setAttribute("data", vector);
                request.setAttribute("tableTitle", "List of Settings");
                request.setAttribute("pageTitle", "Settings Management");

                // Gọi trang JSP (view)
                RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/ListSettings.jsp");
                dispatcher.forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
