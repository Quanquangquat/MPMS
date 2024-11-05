<%-- 
    Document   : ListSettings
    Created on : Oct 28, 2024, 4:16:52 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector" %>
<%@page import="model.Setting"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<%
    String papeTitle = (String) request.getAttribute("papeTitle");
    String tableTitle = (String) request.getAttribute("tableTitle");
    Vector<Setting> vector = (Vector<Setting>) request.getAttribute("data");
%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="styles.css"> 
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-md navbar-dark"
                 style="background-color: tomato">
                <div>
                    <a href="issuelist" class="navbar-brand"> Setting List </a>
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
        <form action="SettingController" method="get">
            <input type="text" name="name" placeholder="Search Settings by Name/Value">
            <select name="status">
                <option value="">All Status</option>
                <option value="active">Active</option>
                <option value="inactive">Inactive</option>
            </select>
            <select name="type">
                <option value="">All Types</option>
                <option value="type1">Type 1</option>
                <option value="type2">Type 2</option>
                <option value="type3">Type 3</option>
                <option value="type4">Type 4</option>
                <option value="type5">Type 5</option>
                <option value="type5">Type 6</option>
            </select>
            <input type="submit" name="submit" value="Filter">
            <a href="SettingController?service=addSetting">New Setting</a>
        </form>
        <table border="1">
            <thead>
                <tr>
                    <th onclick="sortTable(0)">Setting id</th>
                    <th onclick="sortTable(1)">Setting Name</th>
                    <th onclick="sortTable(2)">Setting Type id</th>
                    <th onclick="sortTable(3)">Value</th>
                    <th onclick="sortTable(4)">Priority</th>
                    <th onclick="sortTable(5)">Status</th>
                    <th onclick="sortTable(6)">description</th>
                    <th onclick="sortTable(7)">created_By_Id</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="setting" items="${data}">
                    <tr>
                        <td>${setting.setting_id}</td>
                        <td>${setting.name}</td>
                        <td>${setting.type_id}</td>
                        <td>${setting.value}</td>
                        <td>${setting.priority}</td>
                        <td>${setting.status}</td>
                        <td>${setting.description}</td>
                        <td>${setting.createdById}</td>
                        <td>
                            <a href="SettingController?service=updateSetting&setting_id=${setting_id}">Edit</a>
                            <a href="SettingController?service=removeSetting&setting_id=${setting_id}">Delete</a>
                            <c:choose>
                                <c:when test="${setting.status == 'active'}">
                                    <a href="SettingController?service=deactivateSetting&setting_id=${setting_id}">Deactivate</a>
                                </c:when>
                                <c:when test="${setting.status == 'inactive'}">
                                    <a href="SettingController?service=activateSetting&setting_id=${setting_id}">Activate</a>
                                </c:when>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
