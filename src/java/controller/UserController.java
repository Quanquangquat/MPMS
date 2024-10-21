/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import Entity.User;
import Model.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession(false);
        UserDAO userDAO = new UserDAO();

        switch (action) {
            case "login":
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                break;
            case "register":
                request.getRequestDispatcher("/register.jsp").forward(request, response);
                break;
            case "userList":
                if (session != null && "admin".equals(session.getAttribute("role"))) {
                    request.setAttribute("users", userDAO.getAllUsers());
                    request.getRequestDispatcher("/user_list.jsp").forward(request, response);
                } else {
                    response.sendRedirect("/login.jsp");
                }
                break;
            case "userDetails":
                if (session != null && session.getAttribute("email") != null) {
                    String email = (String) session.getAttribute("email");
                    User user = (User) userDAO.getUserByEmail(email);
                    request.setAttribute("user", user);
                    request.getRequestDispatcher("/user_details.jsp").forward(request, response);
                } else {
                    response.sendRedirect("/login.jsp");
                }
                break;
            default:
                response.sendRedirect("/login.jsp");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        UserDAO userDAO = new UserDAO();
        HttpSession session = request.getSession();

        switch (action) {
            case "login":
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                User user = (User) userDAO.validateUser(email, password);
                if (user != null) {
                    session.setAttribute("email", email);
                    session.setAttribute("role", user.getRole());
                    response.sendRedirect("/UserController?action=userList");
                } else {
                    request.setAttribute("message", "Invalid email or password");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
                break;
            case "register":
                String fullName = request.getParameter("fullName");
                email = request.getParameter("email");
                password = request.getParameter("password");
                String confirmPassword = request.getParameter("confirmPassword");
                if (!password.equals(confirmPassword)) {
                    request.setAttribute("message", "Passwords do not match");
                    request.getRequestDispatcher("/register.jsp").forward(request, response);
                } else if (userDAO.isEmailRegistered(email)) {
                    request.setAttribute("message", "Email already in use");
                    request.getRequestDispatcher("/register.jsp").forward(request, response);
                } else {
                    User newUser = new User(fullName, email, password, "user");
                    userDAO.addUser(newUser);
                    request.setAttribute("message", "Registration successful! Please verify your email to log in.");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
                break;
            case "updateUser":
                if (session != null && session.getAttribute("email") != null) {
                    email = (String) session.getAttribute("email");
                    String currentPassword = request.getParameter("currentPassword");
                    String newPassword = request.getParameter("newPassword");
                    String confirmNewPassword = request.getParameter("confirmNewPassword");
                    user = (User) userDAO.validateUser(email, currentPassword);
                    if (user == null) {
                        request.setAttribute("message", "Current password is incorrect");
                        request.getRequestDispatcher("/user_details.jsp").forward(request, response);
                    } else if (!newPassword.equals(confirmNewPassword)) {
                        request.setAttribute("message", "New passwords do not match");
                        request.getRequestDispatcher("/user_details.jsp").forward(request, response);
                    } else {
                        user.setPassword(newPassword);
                        userDAO.updateUser(user);
                        request.setAttribute("message", "Profile updated successfully");
                        request.getRequestDispatcher("/user_details.jsp").forward(request, response);
                    }
                } else {
                    response.sendRedirect("/login.jsp");
                }
                break;
            default:
                response.sendRedirect("/login.jsp");
                break;
        }
    }
}