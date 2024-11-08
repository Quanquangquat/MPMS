<%-- 
    Document   : issueList
    Created on : Oct 17, 2024, 4:15:48 PM
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
        <title>Issue List</title>
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            * {
                box-sizing: border-box;
            }

            /* Style the search field */
            form.example input[type=text] {
                padding: 5px;
                font-size: 17px;
                border: 1px solid grey;
                float: left;
                width: 80%;
                background: #f1f1f1;
            }

            /* Style the submit button */
            form.example button {
                float: left;
                width: 20%;
                padding: 5px;
                background: #2196F3;
                color: white;
                font-size: 17px;
                border: 1px solid grey;
                border-left: none; /* Prevent double borders */
                cursor: pointer;
            }

            form.example button:hover {
                background: #0b7dda;
            }

            /* Clear floats */
            form.example::after {
                content: "";
                clear: both;
                display: table;
            }
        </style>
    </head>
    <body>
        <%
            List<Issue> list = (List<Issue>) request.getAttribute("list");
        %>
        <header>
            <nav class="navbar navbar-expand-md navbar-dark"
                 style="background-color: tomato">
                <div>
                    <a href="issuecontroller?issueservice=" class="navbar-brand"> Issue
                        Management</a>
                </div>

                <ul class="navbar-nav">
                    <li><a href="home"
                           class="nav-link">HomePage</a></li>
                </ul>

                <form class="form-inline" action="issuecontroller">
                    <input type="text" class="form-control mr-2" placeholder="Search.." name="txtSearch" style="width: 80%;">
                    <input type="hidden" value="issuesearch" name="issueservice">
                    <button type="submit" class="btn btn-primary"><i class="fa fa-search"></i></button>
                </form>

                <ul class="navbar-nav navbar-collapse justify-content-end">
                    <li><a href=""
                           class="nav-link">Logout</a></li>
                </ul>
            </nav>
        </header>

        <div class="row">
            <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

            <div class="container">
                <h3 class="text-center">Issue List</h3>
                <hr>
                <div class="row align-items-center">
                    <!-- Filter section on the left -->
                    <div class="col-md-9">
                        <form class="form-inline" action="issuecontroller" method="get">

                            <select class="form-control mr-2" name="statusFilter">
                                <option value="">Select Status</option>
                                <option value="0">Pending</option>
                                <option value="1">To Do</option>
                                <option value="2">Doing</option>
                                <option value="3">Done</option>
                                <option value="4">Closed</option>
                            </select>

<!--                            <select class="form-control mr-2" name="assigneeFilter">
                                <option value="">Select Assign To</option>
                                <option value="High">High</option>
                                <option value="Medium">Medium</option>
                                <option value="Low">Low</option>
                            </select>-->
                            <input type="hidden" value="issuefilter" name="issueservice">
                            <button type="submit" class="btn btn-primary">Filter</button>
                        </form>
                    </div>

                    <!-- Add Issue button on the right -->
                    <div class="col-md-3 text-right">
                        <a href="issuecontroller?issueservice=addform" class="btn btn-success">Add Issue</a>
                    </div>
                </div>
                <br>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Title</th>
                            <th>Status</th>
                            <th>Assigned To</th> <!-- Trường mới: Priority -->
                            <th>Deadline</th> <!-- Trường mới: Assigned To -->
                            <th>Created Date</th> <!-- Trường mới: Created Date -->
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                                if(!list.isEmpty()){
                                    int m = 1;
                                    for(Issue x: list) {
                                    LocalDateTime xCreated_at = x.getCreated_at();
                                    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                    String formattedCreate_at = xCreated_at.format(formatter2);
                                    UserDAO a = new UserDAO();
                                    User y = a.getUserById1(x.getAssignee_id());
                        %>
                        <tr>
                            <td><%=m %></td>
                            <td><%= x.getTitle()%></td>
                            <%
                                int xStatus = x.getStatus();
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
                            <td><%= sStatus%></td>
                            <td><%= y.getFullName()%></td> 
                            <td><%= x.getDeadline()%></td> 
                            <td><%= formattedCreate_at%></td>

                            <td>
                                <a href="issuecontroller?issueservice=issuedetail&issue_id=<%= x.getIssue_id()%>">View Detail</a>
                                <a href="issuecontroller?issueservice=issuedelete&issue_id=<%= x.getIssue_id()%>">Delete</a>
                            </td>
                        </tr>
                        <% 
                                    m++;
                                }
                            }else{
                        %>
                        <tr>
                            <td colspan="7" class="text-center">No issues found.</td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>

                </table>
            </div>
        </div>     
    </body>
</html>
