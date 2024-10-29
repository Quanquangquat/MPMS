/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.AllocationDAO;
import dao.RequirementDAO;
import dao.SettingDAO;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Allocation;
import model.Requirement;
import model.Setting;
import model.User;

/**
 *
 * @author DELL
 */
public class IssueAddServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        UserDAO d = new UserDAO();

        RequirementDAO b = new RequirementDAO();
        List<Requirement> list2 = b.getRequirementList();
        SettingDAO a = new SettingDAO();
        List<Setting> list1 = a.getIssueTypeListById(6);

        AllocationDAO c = new AllocationDAO();
        List<Allocation> list3 = c.getUserListByPro(1);//thêm điều kiện thuộc dự án nào
        
        List<String> statuses = Arrays.asList("Pending", "To do", "Doing", "Done", "Closed");
        if (list3 != null) {
            List<User> lst = new ArrayList<>(); 

            for (Allocation allo : list3) {
                User x = d.getUserById(allo.getMemberId());
                if (x != null) { 
                    lst.add(x);
                }
            }

            if (!lst.isEmpty()) { 
                request.setAttribute("list1", list1); 
                request.setAttribute("list2", list2); 
                request.setAttribute("list3", lst);
                request.setAttribute("statusList", statuses);
                request.getRequestDispatcher("issueAdd.jsp").forward(request, response);
            } else {
                response.sendRedirect("error.jsp"); 
            }
        } else {
            response.sendRedirect("error.jsp");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
        String sTitle = request.getParameter("txtTitle");
        String sStatus = request.getParameter("txtStatus");
        String sType = request.getParameter("");
        String sDeadline = request.getParameter("txtDeadline");
        String sAssignee = request.getParameter("slAssignee");
        String sRequirement = request.getParameter("slRequirement");
        String sDescription = request.getParameter("txtDescription");
        
        if(sTitle != null && sStatus != null){
            int xStatus = Integer.parseInt(sTitle);
            
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
