<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector"%>
<%@page import="model.Setting"%>
<%@page import="service.SettingService"%>
<%@page import="javax.servlet.RequestDispatcher"%>
<%@page import="javax.servlet.ServletException"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@page import="java.io.IOException"%>

<%
    Setting setting = (Setting) request.getAttribute("data");

    if (setting == null) {
        response.sendRedirect("SettingController");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cập nhật Setting</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .container {
            width: 50%;
            margin: 30px auto;
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        form {
            display: flex;
            flex-direction: column;
            gap: 10px;
        }
        label {
            font-weight: bold;
            margin-bottom: 5px;
        }
        input, select, textarea {
            padding: 10px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 5px;
            width: 100%;
        }
        .btn {
            width: 100px;
            padding: 10px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Edit Setting</h2>
        <form action="SettingController?service=updateSetting" method="POST">
            <input type="hidden" name="setting_id" value="<%= setting.getSetting_id() %>">
            
            <label for="name">Name</label>
            <input type="text" id="name" name="name" value="<%= setting.getName() %>" maxlength="20" pattern="[A-Za-z\s]+" required title="Non-digit string with max length of 20 characters">

            <label for="type">Type</label>
            <select id="type" name="type_id">
                <option value="">--Select Type--</option>
                <option value="1" <%= setting.getType_id() == 1 ? "selected" : "" %>>Type 1</option>
                <option value="2" <%= setting.getType_id() == 2 ? "selected" : "" %>>Type 2</option>
            </select>

            <label for="value">Value</label>
            <input type="text" id="value" name="value" value="<%= setting.getValue() %>" maxlength="100" required title="Any string with max length of 100 characters">

            <label for="priority">Priority</label>
            <input type="number" id="priority" name="priority" value="<%= setting.getPriority() %>" min="1" required title="A positive integer">

            <label>Status:</label>
            <div>
                <input type="radio" id="active" name="status" value="active" <%= setting.getStatus().equals("active") ? "checked" : "" %> required>
                <label for="active">Active</label>
                <input type="radio" id="inactive" name="status" value="inactive" <%= setting.getStatus().equals("inactive") ? "checked" : "" %> required>
                <label for="inactive">Inactive</label>
            </div>

            <label for="description">Description:</label>
            <textarea id="description" name="description" maxlength="200" rows="4" title="Any string with max length of 200 characters"><%= setting.getDescription() %></textarea>

            <button type="submit" class="btn">submit</button>
        </form>
    </div>
</body>
</html>
