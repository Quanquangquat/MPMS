/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controlller.Requirement;

import dal.IssueDAO;
import model.Issue;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author mituz
 */
public class RequirementIssueServlet extends HttpServlet {
   
    IssueDAO issueDAO = new IssueDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int reqId = Integer.parseInt(request.getParameter("reqId"));
        List<Issue> issues = issueDAO.getIssuesByRequirementId(reqId);
        System.out.println(  issues);
        request.setAttribute("issues", issues);
        request.setAttribute("reqId", reqId);  // Pass reqId to create new issues
        request.getRequestDispatcher("/View/Requirement/requirementIssue.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Assuming form data is correctly filled and sent
    }
}