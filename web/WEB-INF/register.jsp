<%-- 
    Document   : register
    Created on : Oct 18, 2024, 6:41:30 AM
    Author     : Hello World
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #f7f7f7;
        }
        .register-container {
            width: 350px;
            padding: 30px;
            border: 1px solid #ccc;
            border-radius: 10px;
            box-shadow: 0px 0px 15px 0px #aaa;
            background-color: #fff;
        }
        .register-container h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        .register-container input[type="text"],
        .register-container input[type="password"] {
            width: calc(100% - 20px);
            padding: 10px;
            margin: 10px 0;
            border-radius: 5px;
            border: 1px solid #ccc;
        }
        .register-container input[type="submit"] {
            width: 100%;
            padding: 12px;
            border-radius: 5px;
            background-color: #28a745;
            color: white;
            border: none;
            cursor: pointer;
            text-align: center;
        }
        .register-container input[type="submit"]:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
<div class="register-container">
    <h2>User Registration</h2>
    <!-- Sử dụng pageContext.request.contextPath để đảm bảo điều hướng chính xác -->
    <form action="<%=request.getContextPath()%>/user/register" method="post">

        <input type="hidden" name="action" value="register"/>
        
        <label for="fullName">Full Name*:</label>
        <input type="text" id="fullName" name="fullName" required/><br/>
        
        <label for="email">Email*:</label>
        <input type="text" id="email" name="email" required/><br/>
        
        <label for="password">Password*:</label>
        <input type="password" id="password" name="password" required/><br/>
        
        <label for="confirmPassword">Confirm Password*:</label>
        <input type="password" id="confirmPassword" name="confirmPassword" required/><br/>
        
        <input type="submit" value="Register"/>
    </form>
    
    <!-- Hiển thị thông báo lỗi nếu có -->
    <c:if test="${not empty message}">
        <p style="color:red; text-align: center;">${message}</p>
    </c:if>
</div>
</body>
</html>