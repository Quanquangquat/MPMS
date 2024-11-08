<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mini Project Management System</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .notlogin {
                min-height: 100vh;
                background-image: url("https://media.istockphoto.com/id/1427631515/vi/anh/m%E1%BA%B7t-b%C3%AAn-tr%C3%AAn-ph%C3%B2ng-h%E1%BB%8Dp-v%E1%BB%9Bi-%C3%A1nh-s%C3%A1ng-n%E1%BB%99i-th%E1%BA%A5t-hi%E1%BB%87n-%C4%91%E1%BA%A1i-v%C3%A0-view-th%C3%A0nh-ph%E1%BB%91-t%E1%BB%AB-t%C6%B0%E1%BB%9Dng-k%C3%ADnh-ph%C3%ADa-sau.jpg?s=612x612&w=0&k=20&c=RcpqgkjepYy7lbEQxOonjtSGwo5opilc7y5KEb_LRP4=");
                background-size: cover; /* Ensures the image covers the entire background */
                background-position: center; /* Centers the background image */
            }
            .overlay {
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5);
            }
            .sec {
                min-height: 100vh;
            }
            .sidebar {
                height: 100vh; /* Chiều cao của sidebar */
                position: fixed; /* Giữ sidebar cố định */
                top: 0; /* Đặt ở phía trên cùng */
                left: 0; /* Đặt ở phía bên trái */
                width: 250px; /* Chiều rộng của sidebar */
                background-color: #343a40; /* Màu nền của sidebar */
                padding: 20px; /* Padding bên trong sidebar */
                color: white; /* Màu chữ trong sidebar */
                display: none; /* Bắt đầu ẩn sidebar */
            }
            .sidebar a {
                color: white; /* Màu chữ các liên kết */
                text-decoration: none; /* Bỏ gạch chân */
            }
            .sidebar a:hover {
                text-decoration: underline; /* Hiệu ứng khi hover */
            }
            .content {
                margin-left: 270px; /* Đẩy nội dung sang phải để không bị che bởi sidebar */
                padding: 20px; /* Padding cho nội dung */
            }
            .toggle-button {
                margin: 10px; /* Khoảng cách cho nút toggle */
            }
        </style>
        <script>
            function toggleSidebar() {
                const sidebar = document.getElementById("sidebar");
                if (sidebar.style.display === "none" || sidebar.style.display === "") {
                    sidebar.style.display = "block"; // Hiện sidebar
                } else {
                    sidebar.style.display = "none"; // Ẩn sidebar
                }
            }
        </script>
    </head>
    <body>
        <section class="sec">
            <c:if test="${sessionScope.user == null}">
                <div class="container-fluid notlogin">
                    <div class="overlay d-flex justify-content-center align-items-center" style="flex-direction: column;">
                        <div>
                            <p style="color: white; font-size: 60px">Mini Project Management System</p><br>
                        </div>
                        <div>
                            <a type="button" class="btn btn-success" href="login">Login</a>    
                            <a type="button" class="btn btn-warning" href="Register">Register</a>    
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${sessionScope.user != null}">
                <%@include file="header.jsp" %>
                <c:choose>
                    <c:when test="${sessionScope.user.roleId == 1}">
                        <button class="btn btn-primary toggle-button" onclick="toggleSidebar()">Toggle Sidebar</button>
                        <div id="sidebar" class="sidebar">
                            <h4>Dashboard</h4>
                            <ul class="list-unstyled">
                                <li><a href="ListUser">Manage User</a></li>
                                <li><a href="requirement">Requirement List</a></li>
                                <li><a href="issuecontroller?issueservice=">Issue List</a></li>
                                <li><a href="SettingController?SettingService=">Setting List</a></li>
                                <li><a href="project-list">Project List</a></li>
                            </ul>
                        </div>
                        <div class="content">
                            <h2>Welcome to the Dashboard</h2>
                            <p>As an Admin, you can manage users and view all items.</p>
                        </div>
                    </c:when>
                    <c:when test="${sessionScope.user.roleId == 2}">
                        <button class="btn btn-primary toggle-button" onclick="toggleSidebar()">Toggle Sidebar</button>
                        <div id="sidebar" class="sidebar">
                            <h4>Dashboard</h4>
                            <ul class="list-unstyled">
                                <li><a href="requirement-list">Requirement List</a></li>
                                <li><a href="issuecontroller?issueservice=">Issue List</a></li>
                                <li><a href="SettingController?SettingService=">Setting List</a></li>
                                <li><a href="project-list">Project List</a></li>
                            </ul>
                        </div>
                        <div class="content">
                            <h2>Welcome Manager</h2>
                            <p>As a Manager, you can manage the project allocation.</p>
                        </div>
                    </c:when>
                    <c:when test="${sessionScope.user.roleId == 3}">
                        <button class="btn btn-primary toggle-button" onclick="toggleSidebar()">Toggle Sidebar</button>
                        <div id="sidebar" class="sidebar">
                            <h4>Dashboard</h4>
                            <ul class="list-unstyled">
                                <li><a href="requirement-list">Requirement List</a></li>
                                <li><a href="issuecontroller?issueservice=">Issue List</a></li>
                                <li><a href="SettingController?SettingService=">Setting List</a></li>
                                <li><a href="project-list">Project List</a></li>
                            </ul>
                        </div>
                        <div class="content">
                            <h2>Welcome Project Leader</h2> 
                            <p>As a Project Leader, you can manage issues, requirements.</p>
                        </div>
                    </c:when>
                    <c:when test="${sessionScope.user.roleId == 4}">
                        <button class="btn btn-primary toggle-button" onclick="toggleSidebar()">Toggle Sidebar</button>
                        <div id="sidebar" class="sidebar">
                            <h4>Dashboard</h4>
                            <ul class="list-unstyled">
                                <li><a href="requirement">Requirement List</a></li>
                                <li><a href="issuecontroller?issueservice=">Issue List</a></li>
                            </ul>
                        </div>
                        <div class="content">
                            <h2>Welcome Team Member</h2>
                            <p>As a Team Member, you can view, add, edit issues and requirement.</p>
                        </div>
                    </c:when>
                </c:choose>
            </c:if>
        </section>
        <%@include file="footer.html" %>
    </body>
</html>
