/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlller.Issue;

import dal.IssueDAO;
import model.Issue;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Date;

/**
 *
 * @author mituz
 */
public class NewIssueServlet extends HttpServlet {

    IssueDAO issueDAO = new IssueDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Display the form for creating a new issue
        request.getRequestDispatcher("/View/Issue/newIssue.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle form submission to create a new issue
        Issue issue = new Issue();
        issue.setTitle(request.getParameter("title"));
        issue.setTypeId(Integer.parseInt(request.getParameter("typeId")));
        issue.setReqId(Integer.parseInt(request.getParameter("reqId"))); // Assumes reqId is passed from a dropdown or form
        issue.setAssignerId(Integer.parseInt(request.getParameter("assignerId")));
        issue.setAssigneeId(Integer.parseInt(request.getParameter("assigneeId")));
        issue.setDeadline(Date.valueOf(request.getParameter("deadline")));
        issue.setStatus(request.getParameter("status"));
        issue.setDescription(request.getParameter("description"));

        if (issueDAO.addIssue(issue)) {
            // Redirect to the issue list if the issue is successfully added
            response.sendRedirect("issue-list");
        } else {
            // If there's an error, display an error message
            request.setAttribute("error", "Unable to create issue");
            doGet(request, response);  // Redisplay the form with the error message
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
