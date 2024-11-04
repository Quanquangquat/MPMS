<%-- 
    Document   : requirementList
    Created on : 18 thg 10, 2024, 02:58:57
    Author     : mituz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Requirement List</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <%@ include file="../../header.jsp" %>
        <div class="container mt-5">
            <h2 class="mb-4">Requirement List</h2>
            <!-- Bootstrap styled table -->
            <table class="table table-striped table-bordered table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Details</th>
                        <th>Issue</th> <!-- Thêm tiêu đề cho cột Issue -->
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="requirement" items="${requirements}">
                        <tr>
                            <td>${requirement.title}</td>
                            <td>${requirement.description}</td>
                            <td><a href="requirement-details?reqId=${requirement.reqId}" class="btn btn-primary btn-sm">View Details</a></td>
                            <td><a href="requirement-issue?reqId=${requirement.reqId}" class="btn btn-warning btn-sm">Issue</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <!-- Bootstrap JS and dependencies -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js"></script>
    </body>
</html>

