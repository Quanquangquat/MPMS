<%-- 
    Document   : requirementDetails
    Created on : 18 thg 10, 2024, 03:02:06
    Author     : mituz
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Requirement Details</title>
</head>
<body>
<h2>Requirement Details</h2>
<table border="1">
    <tr>
        <th>Title</th>
        <td>${requirement.title}</td>
    </tr>
    <tr>
        <th>Description</th>
        <td>${requirement.description}</td>
    </tr>
    <tr>
        <th>Status</th>
        <td>${requirement.statusId}</td> <!-- Optionally, resolve to a human-readable status -->
    </tr>
    <tr>
        <th>Complexity</th>
        <td>${requirement.complexityId}</td> <!-- Optionally, resolve to a human-readable complexity -->
    </tr>
</table>
<a href="requirement-list">Back to Requirements</a>
</body>
</html>
