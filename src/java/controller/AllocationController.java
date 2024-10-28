/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AllocationDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Vector;
import model.Allocation;

/**
 *
 * @author admin
 */
@WebServlet(name = "AllocationController", urlPatterns = {"/AllocationController"})
public class AllocationController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        AllocationDAO dao = new AllocationDAO();
        String service = request.getParameter("service");
        if (service == null) { // gọi trực tiếp servlet
            service = "listAllocation";
        }

        try (PrintWriter out = response.getWriter()) {
            if (service.equals("listAllocation")) { // Liệt kê các allocation
                String submit = request.getParameter("submit");
                Vector<Allocation> vector;
                if (submit == null) { // Liệt kê tất cả
                    vector = dao.getAllocation("SELECT * FROM allocation");
                } else { // Tìm kiếm
                    String memberId = request.getParameter("member_id");
                    vector = dao.getAllocation("SELECT * FROM allocation WHERE member_id LIKE '%" + memberId + "%'");
                }
                request.setAttribute("data", vector);
                request.setAttribute("tableTitle", "Danh sách Allocations");
                request.setAttribute("pageTitle", "Quản lý Allocations");
                RequestDispatcher dispth = request.getRequestDispatcher("/jsp/ListAllocations.jsp");
                dispth.forward(request, response);
            }

            if (service.equals("addAllocation")) {
                String submit = request.getParameter("submit");
                if (submit == null) { // Hiện form thêm
                    ResultSet rsProjects = dao.getData("SELECT * FROM projects");
                    ResultSet rsRoles = dao.getData("SELECT * FROM roles");
                    request.setAttribute("rsProjects", rsProjects);
                    request.setAttribute("rsRoles", rsRoles);
                    dao.dispatch(request, response, "/jsp/insertAllocation.jsp");
                } else {
                    // Xử lý thêm allocation
                    int memberId = Integer.parseInt(request.getParameter("member_id"));
                    int projectId = Integer.parseInt(request.getParameter("project_id"));
                    int roleId = Integer.parseInt(request.getParameter("role_id"));
                    String fromDate = request.getParameter("from_date");
                    String toDate = request.getParameter("to_date");
                    int rate = Integer.parseInt(request.getParameter("rate"));
                    String description = request.getParameter("description");
                    int status = Integer.parseInt(request.getParameter("status"));
                    int createdById = Integer.parseInt(request.getParameter("created_by_id"));

                    Allocation allocation = new Allocation(memberId, projectId, roleId, fromDate, toDate, rate, description, status, LocalDateTime.MAX, createdById, LocalDateTime.MAX, createdById);
                    int n = dao.addAllocation(allocation);
                    response.sendRedirect("URLAllocation");
                }
            }

            if (service.equals("updateAllocation")) {
                String submit = request.getParameter("submit");
                if (submit == null) { // Hiện form cập nhật
                    int memberId = Integer.parseInt(request.getParameter("member_id"));
                    int projectId = Integer.parseInt(request.getParameter("project_id"));
                    int roleId = Integer.parseInt(request.getParameter("role_id"));
                    Vector<Allocation> vector = dao.getAllocation("SELECT * FROM allocation WHERE member_id=" + memberId + " AND project_id=" + projectId + " AND role_id=" + roleId);
                    request.setAttribute("data", vector);
                    ResultSet rsProjects = dao.getData("SELECT * FROM projects");
                    ResultSet rsRoles = dao.getData("SELECT * FROM roles");
                    request.setAttribute("rsProjects", rsProjects);
                    request.setAttribute("rsRoles", rsRoles);
                    dao.dispatch(request, response, "/jsp/updateAllocation.jsp");
                } else {
                    // Xử lý cập nhật allocation
                    int memberId = Integer.parseInt(request.getParameter("member_id"));
                    int projectId = Integer.parseInt(request.getParameter("project_id"));
                    int roleId = Integer.parseInt(request.getParameter("role_id"));
                    String fromDate = request.getParameter("from_date");
                    String toDate = request.getParameter("to_date");
                    int rate = Integer.parseInt(request.getParameter("rate"));
                    String description = request.getParameter("description");
                    int status = Integer.parseInt(request.getParameter("status"));
                    int updatedById = Integer.parseInt(request.getParameter("updated_by_id"));

                    Allocation allocation = new Allocation(memberId, projectId, roleId, fromDate, toDate, rate, description, status, LocalDateTime.MAX, updatedById, LocalDateTime.MAX, updatedById);
                    int n = dao.updateAllocation(allocation);
                    response.sendRedirect("URLAllocation");
                }
            }

            if (service.equals("removeAllocation")) {
                int memberId = Integer.parseInt(request.getParameter("member_id"));
                int projectId = Integer.parseInt(request.getParameter("project_id"));
                int roleId = Integer.parseInt(request.getParameter("role_id"));
                int n = dao.removeAllocation(memberId, projectId, roleId);
                response.sendRedirect("URLAllocation");
            }
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
