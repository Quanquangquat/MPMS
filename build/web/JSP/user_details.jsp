<%-- 
    Document   : user_details
    Created on : Oct 18, 2024, 6:42:37 AM
    Author     : Hello World
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>User Details</title>
</head>
<body>
<h2>User Details</h2>
<p>Email: ${user}</p>
<p><a href="UserServlet?action=logout">Logout</a></p>
</body>
</html>
