<%-- 
    Document   : issueDetails
    Created on : 18 thg 10, 2024, 09:33:02
    Author     : mituz
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Issue Details</title>
    </head>
    <body>
    
    <h2>Issue Details</h2>
    <table border="1">
        <tr>
            <th>Title</th>
            <td>${issue.title}</td>
        </tr>
        <tr>
            <th>Description</th>
            <td>${issue.description}</td>
        </tr>
        <tr>
            <th>Status</th>
            <td>${issue.status}</td>
        </tr>
        <tr>
            <th>Type</th>
            <td>${issue.typeId}</td> <!-- Optional: map this ID to a type name -->
        </tr>
        <tr>
            <th>Requirement ID</th>
            <td>${issue.reqId}</td> <!-- Optional: link to the related requirement -->
        </tr>
        <tr>
            <th>Assigner ID</th>
            <td>${issue.assignerId}</td> <!-- Optional: link to the assigner -->
        </tr>
        <tr>
            <th>Assignee ID</th>
            <td>${issue.assigneeId}</td> <!-- Optional: link to the assignee -->
        </tr>
        <tr>
            <th>Deadline</th>
            <td>${issue.deadline}</td>
        </tr>
    </table>

    <a href="issue-list">Back to Issue List</a>
    <a href="requirement-list">Back to Requirements</a>

</body>
</html>

