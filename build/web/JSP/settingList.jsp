<%-- 
    Document   : settingList
    Created on : Oct 21, 2024, 10:25:05 PM
    Author     : admin
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Vector" %>
<%@ page import="model.Setting" %>
<%@ page import="javax.servlet.jsp.jstl.core.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Setting List</title>
    <link rel="stylesheet" type="text/css" href="styles.css"> <!-- link đến tệp CSS nếu có -->
</head>
<body>
    <h1>Setting List</h1>
    <form action="URLSetting" method="get">
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
            <!-- Add other types as needed -->
        </select>
        <input type="submit" name="submit" value="Filter">
        <a href="URLSetting?service=addSetting">New Setting</a>
    </form>

    <table border="1">
        <thead>
            <tr>
                <th onclick="sortTable(0)">Setting Name</th>
                <th onclick="sortTable(1)">Setting Type</th>
                <th onclick="sortTable(2)">Value</th>
                <th onclick="sortTable(3)">Priority</th>
                <th onclick="sortTable(4)">Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="setting" items="${data}">
                <tr>
                    <td>${setting.name}</td>
                    <td title="Setting Type">${setting.type}</td>
                    <td>${setting.value}</td>
                    <td>${setting.priority}</td>
                    <td>${setting.status}</td>
                    <td>
                        <a href="URLSetting?service=updateSetting&settingId=${setting.id}">Edit</a>
                        <a href="URLSetting?service=removeSetting&settingId=${setting.id}">Delete</a>
                        <c:choose>
                            <c:when test="${setting.status == 'active'}">
                                <a href="URLSetting?service=deactivateSetting&settingId=${setting.id}">Deactivate</a>
                            </c:when>
                            <c:when test="${setting.status == 'inactive'}">
                                <a href="URLSetting?service=activateSetting&settingId=${setting.id}">Activate</a>
                            </c:when>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <script>
        function sortTable(columnIndex) {
            // Chức năng sắp xếp bảng
            // Bạn có thể triển khai thuật toán sắp xếp ở đây
        }
    </script>
</body>
</html>
