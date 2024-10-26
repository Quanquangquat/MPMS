<%-- 
    Document   : user_list
    Created on : Oct 18, 2024, 6:42:06 AM
    Author     : Hello World
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>User List</title>
    <style>
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: center;
        }
        .action-button {
            padding: 5px 10px;
            background-color: #007BFF;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            border: none;
            cursor: pointer;
        }
        .action-button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<h2 style="text-align: center;">User List</h2>
<table>
    <tr>
        <th>Email</th>
        <th>Role</th>
        <th>Actions</th>
    </tr>
    <c:forEach items="${users}" var="entry">
        <tr>
            <td>${entry.key}</td>
            <td>${entry.value.role}</td>
            <td>
                <form action="UserServlet" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="deleteUser"/>
                    <input type="hidden" name="userEmail" value="${entry.key}"/>
                    <input type="submit" value="Delete" class="action-button"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<p style="text-align: center;"><a href="UserServlet?action=logout">Logout</a></p>
<c:if test="${not empty message}">
    <p style="color:red; text-align: center;">${message}</p>
</c:if>
</body>
</html>
