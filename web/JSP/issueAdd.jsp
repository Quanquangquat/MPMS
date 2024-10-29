<%-- 
    Document   : addIssue
    Created on : Oct 17, 2024, 4:16:29 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "model.*" %>
<%@page import = "model.User" %>
<%@page import = "dao.*" %>
<%@page import = "java.util.*" %>
<%@page import = "java.time.*" %>
<%@page import = "java.time.format.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New Issue</title>
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
            List<Setting> list1 = (List<Setting>) request.getAttribute("list1");
            List<Requirement> list2 = (List<Requirement>) request.getAttribute("list2");
            List<User> list3 = (List<User>) request.getAttribute("list3");
            List<String> statuses = (List<String>) request.getAttribute("statusList");
        %>
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <p></p>
                    <form action="issueadd" method="post">
                        <caption>
                            <h2>
                                Add New Issue
                            </h2>
                        </caption>

                        <fieldset class="form-group">
                            <label>Title*:</label> <input type="text" class="form-control"
                                                          name="txtTitle" required="required" minlength="5" placeholder="Enter title">
                        </fieldset>
                        
                        <fieldset class="form-group">
                            <label>Issue Type*:</label> 
                            <select class="form-control" name="txtStatus">
                                <option value="" disabled selected>Select a status</option>
                                <%for(Setting x: list1) {%>  
                                <option value="<%= x.getSetting_id() %>"> <%= x.getName() %> </option>
                                <%}%>
                            </select>
                        </fieldset>
                        
                        <fieldset class="form-group">
                            <label>Status*:</label> 
                            <select class="form-control" name="txtStatus">
                                <option value="" disabled selected>Select a status</option>
                                <%for(String status : statuses) {
                                    int m = 0;
                                %>  
                                <option value="<%= m%>"> <%= status %> </option>
                                <%
                                    m++;}%>
                            </select>
                        </fieldset>
                            
                        <fieldset class="form-group">
                            <label>Deadline:</label> <input type="datetime-local"
                                                               value="" class="form-control"
                                                               name="txtDeadline">
                        </fieldset>
                            
                        <fieldset class="form-group">
                            <label>Assignee:</label>
                            <select class="form-control" name="slAssignee">
                                <option value="" disabled selected>Select a assignee</option>
                                <%for(User y: list3) {%>
                                <option value="<%= y.getUserId()%>"> <%= y.getFullName() %></option>
                                <%}%>
                            </select>
                        </fieldset>
                            
                        <fieldset class="form-group">
                            <label>Requirement:</label>
                            <select class="form-control" name="slRequirement">
                                <option value="" disabled selected>Select a requirement</option>
                                <%for(Requirement z: list2) {%>
                                <option value="<%= z.getReq_id()%>"> <%= z.getTitle() %></option>
                                <%}%>
                            </select>
                        </fieldset>
                            
                        <fieldset class="form-group">
                            <label>Description:</label> 
                            <textarea type="text" class="form-control" rows="4" placeholder="Enter Description" name="txtDescription"></textarea>

                        </fieldset>
                        
                        
                        
                        
                        <button type="submit" class="btn btn-success">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
