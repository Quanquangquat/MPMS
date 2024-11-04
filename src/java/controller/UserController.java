package controller;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.User;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession(false);
        UserDAO userDAO = new UserDAO();

        switch (action) {
            case "login":
                request.getRequestDispatcher("/JSP/login.jsp").forward(request, response);
                break;
            case "register":
                request.getRequestDispatcher("/JSP/register.jsp").forward(request, response);
                break;
            case "userList":
                if (session != null && "admin".equals(session.getAttribute("role"))) {
                    request.setAttribute("users", userDAO.getAllUsers());
                    request.getRequestDispatcher("/JSP/user_list.jsp").forward(request, response);
                } else {
                    response.sendRedirect("/JSP/login.jsp");
                }
                break;
            case "userDetails":
                if (session != null && session.getAttribute("email") != null) {
                    String email = (String) session.getAttribute("email");
                    User user = (User) userDAO.getUserByEmail(email);
                    request.setAttribute("user", user);
                    request.getRequestDispatcher("/JSP/user_details.jsp").forward(request, response);
                } else {
                    response.sendRedirect("/JSP/login.jsp");
                }
                break;
            default:
                response.sendRedirect("/JSP/login.jsp");
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
                User user = userDAO.validateUser(email, password);
                if (user != null) {
                    session.setAttribute("email", email);
                    session.setAttribute("role", user.getRoleId());
                    response.sendRedirect("/UserController?action=userList");
                } else {
                    request.setAttribute("message", "Invalid email or password");
                    request.getRequestDispatcher("/JSP/login.jsp").forward(request, response);
                }
                break;
            case "register":
                String fullName = request.getParameter("fullName");
                email = request.getParameter("email");
                password = request.getParameter("password");
                String confirmPassword = request.getParameter("confirmPassword");
                if (!password.equals(confirmPassword)) {
                    request.setAttribute("message", "Passwords do not match");
                    request.getRequestDispatcher("/JSP/register.jsp").forward(request, response);
                } else if (userDAO.isEmailRegistered(email)) {
                    request.setAttribute("message", "Email already in use");
                    request.getRequestDispatcher("/JSP/register.jsp").forward(request, response);
                } else {
                    User newUser = new User(fullName, email, password, "user");
                    userDAO.addUser(newUser);
                    request.setAttribute("message", "Registration successful! Please log in.");
                    request.getRequestDispatcher("/JSP/login.jsp").forward(request, response);
                }
                break;
            case "updateUser":
                if (session != null && session.getAttribute("email") != null) {
                    email = (String) session.getAttribute("email");
                    String currentPassword = request.getParameter("currentPassword");
                    String newPassword = request.getParameter("newPassword");
                    String confirmNewPassword = request.getParameter("confirmNewPassword");
                    user = userDAO.validateUser(email, currentPassword);
                    if (user == null) {
                        request.setAttribute("message", "Current password is incorrect");
                        request.getRequestDispatcher("/JSP/user_details.jsp").forward(request, response);
                    } else if (!newPassword.equals(confirmNewPassword)) {
                        request.setAttribute("message", "New passwords do not match");
                        request.getRequestDispatcher("/JSP/user_details.jsp").forward(request, response);
                    } else {
                        user.setPassword(newPassword);
                        userDAO.updateUser(user);
                        request.setAttribute("message", "Profile updated successfully");
                        request.getRequestDispatcher("/JSP/user_details.jsp").forward(request, response);
                    }
                } else {
                    response.sendRedirect("/JSP/login.jsp");
                }
                break;
            default:
                response.sendRedirect("/JSP/login.jsp");
                break;
        }
    }
}
