/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controlller.Requirement;

import dal.RequirementDAO;
import model.Requirement;
import model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author mituz
 */
public class RequirementListServlet extends HttpServlet {
    RequirementDAO requirementDAO = new RequirementDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        if (user != null && (user.getRoleId() == 18 || user.getRoleId() == 15)) {  // Assuming role ID 4 is for Project Leaders
            List<Requirement> requirements = requirementDAO.getAllRequirements();
            request.setAttribute("requirements", requirements);
            request.getRequestDispatcher("/View/Requirement/requirementList.jsp").forward(request, response);
        } else {
            response.sendRedirect("login");  // Redirect to login or not authorized page
        }
    }
}