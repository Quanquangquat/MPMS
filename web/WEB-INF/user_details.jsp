<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="model.User" %>
<%
    User user = (User) request.getAttribute("user");
%>
<html>
<head>
    <title>User Details</title>
</head>
<body>
    <h2>User Details</h2>
    <p>ID: <%= user.getId() %></p>
    <p>Username: <%= user.getUsername() %></p>
    <p>Email: <%= user.getEmail() %></p>
</body>
</html>
