<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, model.User" %>
<%
    List<User> userList = (List<User>) request.getAttribute("userList");
%>
<html>
<head>
    <title>User List</title>
</head>
<body>
    <h2>User List</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Email</th>
            <th>Actions</th>
        </tr>
        <%
            for (User user : userList) {
        %>
        <tr>
            <td><%= user.getId() %></td>
            <td><%= user.getUsername() %></td>
            <td><%= user.getEmail() %></td>
            <td><a href="UserController?action=view&id=<%= user.getId() %>">View</a></td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
