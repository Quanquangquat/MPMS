<%-- 
    Document   : insertSetting
    Created on : Oct 28, 2024, 4:16:22 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
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
            gap: 40px;
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
            margin-bottom: 15px;
        }

        .btn {
            width: 100px;
            padding: 10px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 15px;
        }

        .btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Setting Details</h2>
        <form action="SettingController" method="POST">
            <table>
                <tr>
                    <td>
                        <label for="name">Name</label>
                        <input type="text" id="name" name="name" maxlength="20" pattern="[A-Za-z\s]+" required
                               title="Non-digit string with max length of 20 characters">
                    </td>
                    <td><label for="type">Type</label>
                        <select id="type" name="type">
                            <option value="">--Select Type--</option>
                            <option value="user">Type 1</option>
                            <option value="issue">Type 2</option>
                            <option value="project">Type 3</option>
                            <option value="allocation">Type 4</option>
                            <option value="requirement">Type 5</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="value">Value</label>
                        <input type="text" id="value" name="value" maxlength="100" required
                               title="Any string with max length of 100 characters">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="priority">Priority</label>
                        <input type="number" id="priority" name="priority" min="1" required title="A positive integer">
                    </td>
                    <td>
                        <label>Status:</label>
                        <div class="radio-group">
                            <input type="radio" id="active" name="status" value="active" required>
                            <label for="active">Active</label>
                            <input type="radio" id="inactive" name="status" value="inactive" required>
                            <label for="inactive">Inactive</label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="description">Description:</label>
                        <textarea id="description" name="description" maxlength="200" rows="4"
                                  title="Any string with max length of 200 characters"></textarea>
                    </td>
                </tr>
                <tr>
                    <td><button type="submit" class="btn">Submit</button></td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
