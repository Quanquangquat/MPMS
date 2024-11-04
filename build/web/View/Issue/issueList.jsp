<%-- 
    Document   : issueList
    Created on : 18 thg 10, 2024, 09:25:42
    Author     : mituz
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Issue List</title>
</head>
<body>
<h2>Issue List</h2>
<table border="1">
    <tr>
        <th>Title</th>
        <th>Description</th>
        <th>Status</th>
        <th>Deadline</th>
        <th>Details</th>
    </tr>
    <c:forEach var="issue" items="${issues}">
        <tr>
            <td>${issue.title}</td>
            <td>${issue.description}</td>
            <td>${issue.status}</td>
            <td>${issue.deadline}</td>
            <td><a href="issue-details?issueId=${issue.issueId}">View Details</a></td>
        </tr>
    </c:forEach>
        <a href="new-issue">New Issue</a>
</table>
</body>
</html>
