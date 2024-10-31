/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import model.Allocation;
import model.Issue;
import model.Requirement;
import model.Setting;
import model.User;

/**
 *
 * @author DELL
 */
@WebServlet(name="Controller", urlPatterns={"/controller"})
public class Controller extends HttpServlet {
   
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
        String url = "/JSP/login.jsp";
        String controller = request.getParameter("controller");
        if(controller.equals("")){
            
        } else if(controller.equals("")){
            
        }
        
        request.getRequestDispatcher(url).forward(request, response);
        
        
        //---------------------------------------------------------------------------------
//        //Thiếu session account để lấy user id
//        int xUser_id = 1;
//
//        PrintWriter pr = response.getWriter();
//        String iService = request.getParameter("issueservice");
//        String url = "error.jsp";
//        //try {
//
//        if (iService == null || iService.isEmpty()) {
//            url = "/JSP/issueList.jsp";
//            List<Issue> a = issueService.getIssueList();
//
//            request.setAttribute("list", a);
//            request.getRequestDispatcher(url).forward(request, response);
//
//        } else if (iService.equals("issuedetail")) {
//            url = "/JSP/issueDetail.jsp";
//            String sIssue_id = request.getParameter("issue_id");
//
//            if (issueService.validateInt(sIssue_id)) {
//                int xIssue_id = Integer.parseInt(sIssue_id);
//                Issue x = issueService.getIssueById(xIssue_id);
//                if (issueService.validateIssue(x)) {
//                    Requirement y = requirementService.getRequirementById(x.getReq_id());
//                    request.setAttribute("issue", x);
//                    request.setAttribute("requirement", y);
//                    request.getRequestDispatcher(url).forward(request, response);
//                } else {
//                    url = "error.jsp";
//                    request.setAttribute("error", "Invalid issue ID format.");
//                    request.getRequestDispatcher(url).forward(request, response);
//                }
//            } else {
//                url = "error.jsp";
//                request.setAttribute("error", "Invalid issue ID format.");
//                request.getRequestDispatcher(url).forward(request, response);
//            }
//
//            //IssueAddServlet
//        } else if (iService.equals("addform")) {
//            //Khởi tạo list status của issue 
//            List<String> statuses = Arrays.asList("Pending", "To do", "Doing", "Done", "Closed");
//            //Lay du leu ten cua cac REQUIREMENT
//            List<Requirement> lst2 = requirementService.getRequirementList();
//            //Lay du tieu ten cua cac ISSSUE
//            List<Setting> lst1 = settingService.getIssueTypeListById(6);
//            //Lay allocation de lay PROJECT ID
//            int xProjectId = allocationService.getProjectIdByUser(xUser_id);
//
//            List<Allocation> lst3 = allocationService.getUserIdListByPro(xProjectId);
//            if (lst3 != null) {
//                List<User> lst = new ArrayList<>();
//
//                for (Allocation allo : lst3) {
//                    User x = userService.getUserById(allo.getMemberId());
//                    if (x != null) {
//                        lst.add(x);
//                    }
//                }
//
//                if (!lst.isEmpty()) {
//                    request.setAttribute("list1", lst1);// Tên các requirement
//                    request.setAttribute("list2", lst2);//Tên các loại issue
//                    request.setAttribute("list3", lst);//Tên người có trong dự án
//                    request.setAttribute("statusList", statuses);//Status của issue
//                    request.getRequestDispatcher("/JSP/issueAdd.jsp").forward(request, response);
//                } else {
//                    url = "error.jsp";
//                    request.setAttribute("error", "Invalid issue ID format.");
//                    request.getRequestDispatcher(url).forward(request, response);
//                }
//            } else {
//                url = "error.jsp";
//                request.setAttribute("error", "Invalid issue ID format.");
//                request.getRequestDispatcher(url).forward(request, response);
//            }
//
//        } else if (iService.equals("issueadd")) {
//            url = "/JSP/issueList.jsp";
//            String sTitle = request.getParameter("txtTitle");
//            String sStatus = request.getParameter("txtStatus");
//            String sType = request.getParameter("txtType");
//            String sDeadline = request.getParameter("txtDeadline");
//            String sAssignee = request.getParameter("slAssignee");
//            String sRequirement = request.getParameter("slRequirement");
//            String sDescription = request.getParameter("txtDescription");
//            //String sProjectId = request.getParameter("txtProjectId");
//            String sProjectId = "1";
//            //account tạo trực tiếp lúc đó
//            //String sCreatedBy = request.getParameter("Assigner");
//            String sCreatedBy = "1";
//            //String sAssigner = request.getParameter("Assigner");
//            String sAssigner = "1";
//
//
//            if (issueService.validateString(sTitle) && issueService.validateInt(sStatus)
//                    && issueService.validateInt(sType) && issueService.validateDate(sDeadline)
//                    && issueService.validateInt(sAssignee) && issueService.validateInt(sAssigner)
//                    && issueService.validateInt(sCreatedBy) && issueService.validateInt(sProjectId)) {
//                int xType = Integer.parseInt(sType);
//                int xAssignee = Integer.parseInt(sAssignee);
//                int xAssigner = Integer.parseInt(sAssigner);
//                int xStatus = Integer.parseInt(sStatus);
//                int xCreatedBy = Integer.parseInt(sCreatedBy);
//                int xProjectId = Integer.parseInt(sProjectId);
//                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//                Date xDeadline = formatter.parse(sDeadline);
//                
//                Integer reqId = null; // Khởi tạo reqId là null
//                Integer updateById = null;
//
//                if (sRequirement != null && !sRequirement.isEmpty()) {
//                    reqId = Integer.parseInt(sRequirement); // Chuyển đổi nếu có giá trị
//                } 
//
//                Issue a = new Issue(sTitle, xType, reqId, xAssigner, xAssignee,
//                        xDeadline, xStatus, sDescription, xCreatedBy, updateById, 1);
//                issueService.insertIssue(a);
//                
//                List<Issue> b = issueService.getIssueList();
//
//                request.setAttribute("list", b);
//                request.getRequestDispatcher(url).forward(request, response);
//            } else {
//                pr.print("BBBBBBBBBBBBB");
//            }
//        } else if (iService.equals("issueupdate")) {
//
//        } else if (iService.equals("issuedelete")) {
//            url = "/JSP/issueList.jsp";
//            String sIssue_id = request.getParameter("issue_id");
//
//            if (issueService.validateInt(sIssue_id)) {
//                int xIssue_id = Integer.parseInt(sIssue_id);
//                issueService.removeIssue(xIssue_id);
//                List<Issue> a = issueService.getIssueList();
//
//                request.setAttribute("list", a);
//                request.getRequestDispatcher(url).forward(request, response);
//            } else {
//                url = "error.jsp";
//                request.setAttribute("error", "Invalid issue ID format.");
//                request.getRequestDispatcher(url).forward(request, response);
//            }
//        }
////        } catch (Exception e) {
////            e.printStackTrace();
////            url = "error.jsp";
////            String xError = "\nKindly go back to the main page to proceed with using the system.";
////            request.setAttribute("error", xError);
////            request.getRequestDispatcher("errorFields.jsp").forward(request, response);
////        }
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
