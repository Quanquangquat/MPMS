/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Allocation;
import model.Issue;
import model.Requirement;
import model.Setting;
import model.User;
import service.AllocationService;
import service.IssueService;
import service.ProjectService;
import service.RequirementService;
import service.SettingService;
import service.UserService;

/**
 *
 * @author DELL
 */
public class IssueController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private IssueService issueService;
    private RequirementService requirementService;
    private UserService userService;
    private AllocationService allocationService;
    private ProjectService projectService;
    private SettingService settingService;

    @Override
    public void init() throws ServletException {
        issueService = new IssueService();
        requirementService = new RequirementService();
        userService = new UserService();
        allocationService = new AllocationService();
        projectService = new ProjectService();
        settingService = new SettingService();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        int xUser_id = 1;

        String iService = request.getParameter("issueservice");

        if (iService == null || iService.isEmpty()) {
            displayIssueList(request, response);

        } else if (iService.equals("issuesearch")) {
            searchIssueList(request, response);

        } else if (iService.equals("issuefilter")) {
            filterIssueList(request, response);

        } else if (iService.equals("issuedetail")) {
            displayIssueDetail(request, response);

        } else if (iService.equals("addform")) {
            displayAddForm(request, response);

        } else if (iService.equals("issueadd")) {
            handleIssueAdd(request, response);

        } else if (iService.equals("updateform")) {
            displayUpdateForm(request, response);

        } else if (iService.equals("issueupdate")) {
            handleIssueUpdate(request, response);

        } else if (iService.equals("issuedelete")) {
            handleIssueDelete(request, response);

        } else {
            response.sendRedirect("error.jsp");
        }
    }

    private void displayIssueList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/JSP/issueList.jsp";
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("user");
        if (account == null || account.getUserId() <= 0) {
            response.sendRedirect("error.jsp");
            return;
        } else {
            if (account.getRoleId() == 4) {
                int uId = account.getUserId();
                int xProjectId = allocationService.getProjectIdByUser(uId);
                List<Issue> issues = issueService.getIssueListByPId(xProjectId);
                request.setAttribute("list", issues);
                request.getRequestDispatcher(url).forward(request, response);
            } else {
                List<Issue> issues = issueService.getIssueList();
                request.setAttribute("list", issues);
                request.getRequestDispatcher(url).forward(request, response);
            }
        }

    }

    private void searchIssueList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/JSP/issueList.jsp";
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("user");
        if (account == null || account.getUserId() <= 0) {
            response.sendRedirect("error.jsp");
            return;
        } else {
            if (account.getRoleId() == 4) {
                int uId = account.getUserId();
                int xProjectId = allocationService.getProjectIdByUser(uId);
                String sSearch = request.getParameter("txtSearch");
                List<Issue> issues = issueService.searchIssue1(sSearch, xProjectId);
                request.setAttribute("list", issues);
                request.getRequestDispatcher(url).forward(request, response);
            } else {
                String sSearch = request.getParameter("txtSearch");
                List<Issue> issues = issueService.searchIssue(sSearch);
                request.setAttribute("list", issues);
                request.getRequestDispatcher(url).forward(request, response);
            }
        }
    }

    private void filterIssueList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/JSP/issueList.jsp";
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("user");
        String sStatus = request.getParameter("statusFilter");
        String sAssignee_id = request.getParameter("assigneeFilter");

        Integer xStatus = (sStatus != null && !sStatus.isEmpty()) ? Integer.parseInt(sStatus) : null;
        Integer xAssignee_id = (sAssignee_id != null && !sAssignee_id.isEmpty()) ? Integer.parseInt(sAssignee_id) : null;

        if (account == null || account.getUserId() <= 0) {
            response.sendRedirect("error.jsp");
            return;
        } else {
            if (account.getRoleId() == 4) {
                int uId = account.getUserId();
                int xProjectId = allocationService.getProjectIdByUser(uId);
                List<Issue> issues = issueService.getIssueListByFilter1(xAssignee_id, xStatus, xProjectId);
                request.setAttribute("list", issues);
                request.getRequestDispatcher(url).forward(request, response);
            } else {
                List<Issue> issues = issueService.getIssueListByFilter(xAssignee_id, xStatus);
                request.setAttribute("list", issues);
                request.getRequestDispatcher(url).forward(request, response);
            }
        }
    }

    private void displayIssueDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pr = response.getWriter();
        String url = "/JSP/issueDetail.jsp";
        String sIssue_id = request.getParameter("issue_id");
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("user");
        if (account == null || account.getUserId() <= 0) {
            response.sendRedirect("error.jsp");
            return;
        } else {
            if (issueService.validateInt(sIssue_id)) {
                int xIssue_id = Integer.parseInt(sIssue_id);
                Issue issue = issueService.getIssueById(xIssue_id);
                //pr.print(issue.getUpdated_by_id());
                if (issueService.validateIssue(issue)) {
                    Requirement requirement = requirementService.getRequirementById(issue.getReq_id());
                    Setting setting = settingService.getSettingById(issue.getType_id());

                    request.setAttribute("setting", setting);
                    request.setAttribute("issue", issue);
                    request.setAttribute("requirement", requirement);
                    request.getRequestDispatcher(url).forward(request, response);
                } else {
                    response.sendRedirect("error.jsp");
                }
            } else {
                response.sendRedirect("error.jsp");
            }
        }

    }

    private void displayAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/JSP/issueAdd.jsp";
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("user");

        List<String> statuses = Arrays.asList("Pending", "To do", "Doing", "Done", "Closed");
        List<Requirement> requirements = requirementService.getRequirementList();
        List<Setting> issueTypes = settingService.getIssueTypeListById(6);

        if (account == null || account.getUserId() <= 0) {
            response.sendRedirect("error.jsp");
            return;
        } else {
            List<Allocation> allocations = new ArrayList<>();

            int xProjectId = allocationService.getProjectIdByUser(account.getUserId());
            allocations = allocationService.getUserIdListByPro(xProjectId);

            if (allocations != null && !allocations.isEmpty()) {
                List<User> users = new ArrayList<>();
                for (Allocation allo : allocations) {
                    User user = userService.getUserById(allo.getMemberId());
                    if (user != null) {
                        users.add(user);
                    }
                }

                request.setAttribute("list1", issueTypes);
                request.setAttribute("list2", requirements);
                request.setAttribute("list3", users);
                request.setAttribute("statusList", statuses);
                request.getRequestDispatcher(url).forward(request, response);
            } else {
                response.sendRedirect("error.jsp");
            }
        }
    }

    private void handleIssueAdd(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException {
        String sTitle = request.getParameter("txtTitle");
        String sStatus = request.getParameter("txtStatus");
        String sType = request.getParameter("txtType");
        String sDeadline = request.getParameter("txtDeadline");
        String sAssignee = request.getParameter("slAssignee");
        String sRequirement = request.getParameter("slRequirement");
        String sDescription = request.getParameter("txtDescription");

        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("user");
        if (account == null || account.getUserId() <= 0) {
            response.sendRedirect("error.jsp");
            return;
        } else {
            int uId = account.getUserId();
            int xProjectId = allocationService.getProjectIdByUser(uId);
            int xCreatedBy = account.getUserId();
            int xAssigner = account.getUserId();
            if (issueService.validateString(sTitle) && issueService.validateInt(sStatus)
                    && issueService.validateInt(sType) && issueService.validateDate(sDeadline)
                    && issueService.validateInt(sAssignee)) {
                int xType = Integer.parseInt(sType);
                int xAssignee = Integer.parseInt(sAssignee);
                int xStatus = Integer.parseInt(sStatus);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date xDeadline = formatter.parse(sDeadline);

                Integer reqId = (sRequirement != null && !sRequirement.isEmpty()) ? Integer.parseInt(sRequirement) : null;

                Issue issue = new Issue(sTitle, xType, reqId, xAssigner, xAssignee,
                        xDeadline, xStatus, sDescription, xCreatedBy, null, xProjectId);
                issueService.insertIssue(issue);

                response.sendRedirect("issuecontroller?issueservice=");
            } else {
                response.sendRedirect("error.jsp");
            }
        }

    }

    private void handleIssueDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sIssue_id = request.getParameter("issue_id");
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("user");
        if (account == null || account.getUserId() <= 0) {
            response.sendRedirect("error.jsp");
            return;
        } else {
            if (issueService.validateInt(sIssue_id)) {
                int xIssue_id = Integer.parseInt(sIssue_id);
                issueService.removeIssue(xIssue_id);
                response.sendRedirect("issuecontroller?issueservice=");
            } else {
                response.sendRedirect("error.jsp");
            }
        }
    }

    private void displayUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/JSP/issueUpdate.jsp";
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("user");
        String sIssue_id = request.getParameter("issue_id");
        List<String> statuses = Arrays.asList("Pending", "To do", "Doing", "Done", "Closed");
        List<Requirement> requirements = requirementService.getRequirementList();
        List<Setting> issueTypes = settingService.getIssueTypeListById(6);
        List<User> users = new ArrayList<>();
        Issue issue = new Issue();
        if (account == null || account.getUserId() <= 0) {
            response.sendRedirect("error.jsp");
            return;
        } else {
            if (issueService.validateInt(sIssue_id)) {
                int xIssue_id = Integer.parseInt(sIssue_id);
                issue = issueService.getIssueById(xIssue_id);

            } else {
                response.sendRedirect("error.jsp");
            }

            int xProjectId = allocationService.getProjectIdByUser(account.getUserId());

            List<Allocation> allocations = allocationService.getUserIdListByPro(xProjectId);

            if (allocations != null && !allocations.isEmpty()) {
                for (Allocation allo : allocations) {
                    User user = userService.getUserById(allo.getMemberId());
                    if (user != null) {
                        users.add(user);
                    }
                }

            }

            if (issueTypes != null && requirements != null && users != null && statuses != null && issue != null) {
                request.setAttribute("list1", issueTypes);
                request.setAttribute("list2", requirements);
                request.setAttribute("list3", users);
                request.setAttribute("statusList", statuses);
                request.setAttribute("issue", issue);
                request.getRequestDispatcher(url).forward(request, response);
            }
        }

    }

    private void handleIssueUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("user");
        String sTitle = request.getParameter("txtTitle");
        String sStatus = request.getParameter("txtStatus");
        String sType = request.getParameter("txtType");
        String sDeadline = request.getParameter("txtDeadline");
        String sAssignee = request.getParameter("slAssignee");
        String sRequirement = request.getParameter("slRequirement");
        String sDescription = request.getParameter("txtDescription");
        String sCreatedBy = request.getParameter("txtCreatedBy");
        String sIssue_id = request.getParameter("issue_id");
        if (account == null || account.getUserId() <= 0) {
            response.sendRedirect("error.jsp");
            return;
        } else {
            int uId = account.getUserId();
            int xUpdatedBy = account.getUserId();
            int xProjectId = allocationService.getProjectIdByUser(uId);
            int xAssigner = account.getUserId();
            int xIssue_id = 0;
            if (issueService.validateInt(sIssue_id)) {
                xIssue_id = Integer.parseInt(sIssue_id);
            }

            PrintWriter pr = response.getWriter();
            if (issueService.validateString(sTitle) && issueService.validateInt(sStatus)
                    && issueService.validateInt(sType) && issueService.validateDate(sDeadline)
                    && issueService.validateInt(sAssignee)
                    && issueService.validateInt(sCreatedBy)) {
                int xType = Integer.parseInt(sType);
                int xAssignee = Integer.parseInt(sAssignee);
                int xStatus = Integer.parseInt(sStatus);
                int xCreatedBy = Integer.parseInt(sCreatedBy);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date xDeadline = formatter.parse(sDeadline);
//                pr.print("AAAAAAAAAAAAA");
                Integer reqId = (sRequirement != null && !sRequirement.isEmpty()) ? Integer.parseInt(sRequirement) : null;

                Issue issue = new Issue(xIssue_id, sTitle, xType, reqId, xAssigner, xAssignee,
                        xDeadline, xStatus, sDescription, xCreatedBy, xUpdatedBy);
                issueService.updateIssue(issue);

                response.sendRedirect("issuecontroller?issueservice=");
            } else {
//                pr.print("BBBBBBBB");
                response.sendRedirect("error.jsp");
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
        try {
            processRequest(request, response);

        } catch (ParseException ex) {
            Logger.getLogger(IssueController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);

        } catch (ParseException ex) {
            Logger.getLogger(IssueController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
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
