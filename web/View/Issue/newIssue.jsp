<%-- 
    Document   : newIssue
    Created on : 18 thg 10, 2024, 09:30:16
    Author     : mituz
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create New Issue</title>
</head>
<body>
<h2>Create New Issue</h2>
<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>
<form action="${pageContext.request.contextPath}/new-issue" method="post">
    Title: <input type="text" name="title" required /><br/>
    Type ID: <input type="text" name="typeId" required /><br/>
    Requirement ID: <input type="text" name="reqId" required /><br/>
    Assigner ID: <input type="text" name="assignerId" required /><br/>
    Assignee ID: <input type="text" name="assigneeId" required /><br/>
    Deadline: <input type="date" name="deadline" required /><br/>
    Status: <input type="text" name="status" required /><br/>
    Description: <textarea name="description" rows="4" cols="50"></textarea><br/>
    <input type="submit" value="Create Issue" />
    <a href="issue-list">Back Issue list</a>
</form>
</body>
</html>

