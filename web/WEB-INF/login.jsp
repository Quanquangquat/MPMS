<%-- 
    Document   : login
    Created on : Oct 18, 2024, 6:41:05 AM
    Author     : Hello World
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
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
        .login-container {
            width: 350px;
            padding: 30px;
            border: 1px solid #ccc;
            border-radius: 10px;
            box-shadow: 0px 0px 15px 0px #aaa;
            background-color: #fff;
        }
        .login-container h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        .login-container input[type="text"],
        .login-container input[type="password"] {
            width: calc(100% - 20px);
            padding: 10px;
            margin: 10px 0;
            border-radius: 5px;
            border: 1px solid #ccc;
        }
        .login-container input[type="submit"],
        .login-container input[type="button"] {
            width: 48%;
            padding: 12px;
            margin: 10px 1%;
            border-radius: 5px;
            background-color: #007BFF;
            color: white;
            border: none;
            cursor: pointer;
            text-align: center;
        }
        .login-container input[type="submit"]:hover,
        .login-container input[type="button"]:hover {
            background-color: #0056b3;
        }
        .login-container a {
            display: block;
            text-align: center;
            margin-top: 15px;
            color: #007BFF;
            text-decoration: none;
        }
        .login-container a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="login-container">
    <h2>User Login</h2>
    <form action="<%=request.getContextPath()%>/user/login" method="post">

        <input type="hidden" name="action" value="login"/>
        <label for="email">Email*:</label>
        <input type="text" id="email" name="email" required/><br/>
        <label for="password">Password*:</label>
        <input type="password" id="password" name="password" required/><br/>
        <input type="submit" value="Login"/>
        <a href="${pageContext.request.contextPath}/JSP/register.jsp">Don't have an account? Register here</a>


    </form>
    <a href="#">Forgot Password?</a>
    <c:if test="${not empty message}">
        <p style="color:red; text-align: center;">${message}</p>
    </c:if>
</div>
</body>
</html>
