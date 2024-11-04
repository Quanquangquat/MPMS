<%-- 
    Document   : requirementIssue
    Created on : 18 thg 10, 2024, 03:06:19
    Author     : mituz
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Requirement Issues</title>
    </head>
    <body>
        <h2>Issues for Requirement ID: ${reqId}</h2>
        <c:if test="${not empty issues}">
            <ul>
                <c:forEach var="issue" items="${issues}">
                    <li>${issue.title} - ${issue.description} (<a href="issue-details?issueId=${issue.issueId}">Details</a>)</li>
                    </c:forEach>
            </ul>
        </c:if>
        <a href="requirement-list">Back to Requirements</a>
    </body>
</html>

