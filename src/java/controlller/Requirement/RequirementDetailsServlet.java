/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controlller.Requirement;

import dal.RequirementDAO;
import model.Requirement;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author mituz
 */
public class RequirementDetailsServlet extends HttpServlet {
    RequirementDAO requirementDAO = new RequirementDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int reqId = Integer.parseInt(request.getParameter("reqId"));
        Requirement requirement = requirementDAO.getRequirementById(reqId);
        if (requirement != null) {
            request.setAttribute("requirement", requirement);
            request.getRequestDispatcher("/View/Requirement/requirementDetails.jsp").forward(request, response);
        } else {
            response.sendRedirect("requirement-list");  // Redirect if no such requirement exists
        }
    }
}