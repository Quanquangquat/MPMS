<%-- 
    Document   : RequirementAdd
    Created on : Nov 5, 2024, 7:51:18 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "model.*" %>
<%@page import = "dao.*" %>
<%@page import = "java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New Requirement</title>
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
                    <a href="requirementcontroller?requirementservice=" class="navbar-brand">Requirement Management</a>
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
            List<User> list3 = (List<User>) request.getAttribute("list3");
            List<String> statuses = (List<String>) request.getAttribute("statusList");
        %>
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <p></p>
                    <form action="requirement">
                        <caption>
                            <h2>
                                Add New Requirement
                            </h2>
                        </caption>

                        <fieldset class="form-group">
                            <label>Title*:</label> 
                            <input type="text" class="form-control"
                                   name="txtTitle" required="required" minlength="5" placeholder="Enter title">
                        </fieldset>
                        
                        <fieldset class="form-group">
                            <label>Owner*:</label> 
                            <input type="number" value="" class="form-control"
                                   name="txtOwner">
                        </fieldset>
                        
                        <fieldset class="form-group">
                            <label>Complexity*:</label> 
                            <input type="number" value="" class="form-control"
                                   name="txtComplexity">
                        </fieldset>
                            
                        <fieldset class="form-group">
                            <label>Status:</label> 
                            <input type="number" value="" class="form-control"
                                   name="txtStatus">
                        </fieldset>
   
                        <fieldset class="form-group">
                            <label>Description:</label> 
                            <textarea type="text" class="form-control" rows="4" placeholder="Enter Description" name="txtDescription"></textarea>
                        </fieldset>
                            
                        <input type="hidden" value="requirementadd" name="requirementservice">
                       
                        <button type="submit" class="btn btn-success">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>