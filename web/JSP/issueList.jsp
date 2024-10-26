<%-- 
    Document   : issueList
    Created on : Oct 17, 2024, 4:15:48 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Issue List</title>
        <style>
            body {
                display: flex; /* Sử dụng Flexbox cho căn giữa */
                justify-content: center; /* Căn giữa theo chiều ngang */
                margin: 0; /* Bỏ khoảng cách mặc định */
            }

            .container {
                width: 70%; /* Đặt chiều rộng container là 70% */
                margin: 20px auto; /* Căn giữa container với khoảng cách phía trên và dưới */
            }

            .filter-container {
                display: flex; /* Sử dụng Flexbox cho filter và nút Add */
                align-items: center; /* Căn giữa theo chiều dọc */
                margin-bottom: 20px; /* Khoảng cách dưới cùng với bảng */
            }

            .filter-group {
                display: flex; /* Để có thể xếp hàng các phần tử */
                align-items: center; /* Căn giữa theo chiều dọc */
                margin-right: 20px; /* Khoảng cách giữa các nhóm lọc */
            }

            .add-button {
                padding: 3px 10px;
                cursor: pointer;
                background-color: white; /* Màu nền trắng */
                color: black; /* Màu chữ đen */
                border: 1px solid black; /* Viền màu xám sáng */
                border-radius: 4px; /* Bo góc */
                margin-left: auto; /* Đẩy nút Add ra phía bên phải */
            }

            .action-button {
                padding: 5px 8px; /* Giảm padding để các nút nhỏ hơn */
                cursor: pointer;
                background-color: white; /* Màu nền trắng */
                color: black; /* Màu chữ đen */
                border: 1px solid #D3D3D3; /* Viền màu xám sáng */
                border-radius: 4px; /* Bo góc */
                margin: 0; /* Bỏ khoảng cách giữa các nút */
                font-size: 12px; /* Kích thước chữ nhỏ hơn */
            }

            .select-button {
                padding: 3px 10px;
                cursor: pointer;
                background-color: white; /* Màu nền trắng */
                color: black; /* Màu chữ đen */
                border: 1px solid black; /* Viền màu đen */
                border-radius: 4px; /* Bo góc */
                margin-left: 5px; /* Khoảng cách bên trái */
                width: 120px; /* Điều chỉnh chiều rộng ô lọc */
            }

            table {
                width: 100%;

            }

            th, td {
                padding: 8px; /* Khoảng cách trong ô */
                text-align: left; /* Căn trái cho văn bản */
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2> Issue List </h2>

            <!-- Phần Filter By -->
            <div class="filter-container">
                <div class="filter-group">
                    <label for="colName">Status:</label>
                    <select name="colName" id="colName" class="select-button">
                        <option>All</option>
                        <option>High Priority</option>
                        <option>Medium Priority</option>
                        <option>Low Priority</option>
                    </select>
                </div>

                <div class="filter-group">
                    <label for="assignedTo">Assigned To:</label>
                    <select name="assignedTo" id="assignedTo" class="select-button">
                        <option>All</option>
                        <option>John Doe</option>
                        <option>Jane Smith</option>
                        <option>Michael Brown</option>
                    </select>
                </div>

                <button class="add-button">Add</button> <!-- Nút Add nằm ở cuối cùng -->
            </div>

            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Status</th>
                        <th>Priority</th> <!-- Trường mới: Priority -->
                        <th>Assigned To</th> <!-- Trường mới: Assigned To -->
                        <th>Created Date</th> <!-- Trường mới: Created Date -->
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td>Issue One</td>
                        <td>Open</td>
                        <td>High</td> <!-- Giá trị Priority -->
                        <td>John Doe</td> <!-- Giá trị Assigned To -->
                        <td>2024-01-10</td> <!-- Giá trị Created Date -->
                        <td>
                            <button class="action-button">View Detail</button> <!-- Nút Xem Chi Tiết -->
                            <button class="action-button">Delete</button> <!-- Nút Delete -->
                        </td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>Issue Two</td>
                        <td>Closed</td>
                        <td>Medium</td> <!-- Giá trị Priority -->
                        <td>Jane Smith</td> <!-- Giá trị Assigned To -->
                        <td>2024-02-15</td> <!-- Giá trị Created Date -->
                        <td>
                            <button class="action-button">View Detail</button> <!-- Nút Xem Chi Tiết -->
                            <button class="action-button">Delete</button> <!-- Nút Delete -->
                        </td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>Issue Three</td>
                        <td>In Progress</td>
                        <td>Low</td> <!-- Giá trị Priority -->
                        <td>Michael Brown</td> <!-- Giá trị Assigned To -->
                        <td>2024-03-20</td> <!-- Giá trị Created Date -->
                        <td>
                            <button class="action-button">View Detail</button> <!-- Nút Xem Chi Tiết -->
                            <button class="action-button">Delete</button> <!-- Nút Delete -->
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </body>
</html>
