<%-- 
    Document   : issueDetail
    Created on : Oct 17, 2024, 4:16:55 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "model.*" %>
<%@page import = "dao.*" %>
<%@page import = "java.util.*" %>
<%@page import = "java.time.*" %>
<%@page import = "java.time.format.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Issue Detail</title>
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-md navbar-dark"
                 style="background-color: tomato">
                <div>
                    <a href="issuelist" class="navbar-brand"> Issue
                        Management</a>
                </div>

                <ul class="navbar-nav">
                    <li><a href=""
                           class="nav-link">HomePage</a></li>
                </ul>

                <ul class="navbar-nav navbar-collapse justify-content-end">
                    <li><a href=""
                           class="nav-link">Logout</a></li>
                </ul>
            </nav>
        </header>
        <%
            Issue issue = (Issue) request.getAttribute("issue");
            Requirement requirement = (Requirement) request.getAttribute("requirement");
            Setting setting = (Setting) request.getAttribute("setting");
        %>
        <p></p>
        <div class="container col-md-8">
            <div class="card">
                <div class="card-body">
                    <caption>
                        <h2>View Issue Detail</h2>
                    </caption>

                    <div class="form-row">
                        <fieldset class="form-group col-md-7">
                            <label>Title*:</label>
                            <input type="text" class="form-control" value="<%= issue.getTitle()%>" disabled>
                        </fieldset>
                        
                        <fieldset class="form-group col-md-4">
                            <label>Deadline*:</label> 
                            <input type="text" value="<%= issue.getDeadline()%>" class="form-control" disabled>
                        </fieldset>

                        <fieldset class="form-group col-md-7">
                            <label>Issue Type*:</label> 
                            <input type="text" class="form-control" value="<%= setting.getName()%>" disabled>

                        </fieldset>
                        <%
                            int xAssigner = issue.getAssigner_id();
                            UserDAO a = new UserDAO();
                            User y = a.getUserById(xAssigner);
                        %>
                        <fieldset class="form-group col-md-4">
                            <label>Assigner*:</label> <input type="text"
                                                            value="<%= y.getFullName()%>" class="form-control" disabled>
                        </fieldset>

                        <%
                            int xStatus = issue.getStatus();
                            String sStatus = null;
                            if(xStatus == 0){
                                sStatus = "Pending";
                            } else if(xStatus == 1){
                                sStatus = "To Do";
                            } else if(xStatus == 2){
                                sStatus = "Doing";
                            } else if(xStatus == 3){
                                sStatus = "Done";
                            } else if(xStatus == 4){
                                sStatus = "Closed";
                            }
                        %>
                        <fieldset class="form-group col-md-7">
                            <label>Status*:</label> 
                            <input type="text" class="form-control" value="<%= sStatus%>" disabled>

                        </fieldset>

                        <%
                            int xAssignee = issue.getAssignee_id();
                            User z = a.getUserById(xAssignee);
                        %>
                        <fieldset class="form-group col-md-4">
                            <label>Assignee*:</label> <input type="text"
                                                            value="<%= z.getFullName()%>" class="form-control" disabled>
                        </fieldset>
                        <%
                            String sReqTitle = "None";
                            if(requirement != null){
                                sReqTitle = requirement.getTitle();
                            }
                            %>
                        <fieldset class="form-group col-md-7">
                            <label>Requirement Name:</label>
                            <input type="text" class="form-control" value="<%= sReqTitle%>" minlength="5" disabled>
                        </fieldset>     

                        <fieldset class="form-group col-md-4">
                            <%
                            int xUpdatedBy = issue.getUpdated_by_id();
                            User h = a.getUserById(xUpdatedBy);
                            String sUpdatedBy = "None";
                            if(h != null){
                                sUpdatedBy = h.getFullName();
                            }
                            %>
                            <label>Updated By:</label> <input type="text"
                                                              value="<%= sUpdatedBy%>" class="form-control" disabled>

                        </fieldset>

                        <fieldset class="form-group col-md-7">
                            <label>Description:</label> 
                            <textarea type="text" class="form-control" rows="4" disabled=""><%= issue.getDescription() %></textarea>

                        </fieldset>


                        <fieldset class="form-group col-md-4">
                            <label>Created At:</label> <input type="datetime-local"
                                                              value="<%= issue.getCreated_at()%>" class="form-control" disabled>
                            <label>Updated At:</label> <input type="datetime-local"
                                                              value="<%= issue.getUpdated_at()%>" class="form-control" disabled>
                        </fieldset>

                    </div>
                    <div>
                        <button type="submit" class="btn btn-success" style="background-color: gray; border-color: black;">Back</button>
                        <a href="issuecontroller?issueservice=updateform&issue_id=<%= issue.getIssue_id()%>" class="btn btn-success">Edit</a>
                    </div>
                </div>
            </div>
    </body>
</html>
