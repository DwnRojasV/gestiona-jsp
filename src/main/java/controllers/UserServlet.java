package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.UserDao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import static constants.AppConstants.*;

@WebServlet("/users/")
public class UserServlet extends HttpServlet {
    private static final String URI_EDIT = "/users/edit";
    private static final String URI_DELETE = "/users/delete";

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        UserDao userDao = new UserDao();

        PrintWriter writer = response.getWriter();
        writer.println("<h1>Respuesta</h1>");
        writer.println(String.format("<p>URI %s</p>", requestURI));
        int id = 0;

        switch (requestURI) {
            case URI_EDIT:
                int userId = Integer.parseInt(request.getParameter(EDIT_USER_ID_PARAMETER));
                String userName = request.getParameter(EDIT_USER_NAME_PARAMETER);
                String userEmail = request.getParameter(EDIT_USER_EMAIL_PARAMETER);
                String userRole = request.getParameter(EDIT_USER_ROLE_PARAMETER);
                String userPermission = request.getParameter(EDIT_USER_PERMISSION_PARAMETER);

                Boolean isUpdated = userDao.updateUser(userId, userName, userEmail, userRole, userPermission);
                if (isUpdated) {
                    System.out.println("Updated successfully");
                } else {
                    System.out.println("Something went wrong");
                }
                break;

            case URI_DELETE:
                id = Integer.parseInt(request.getParameter("id"));
                writer.println(String.format("<p>Metodo= %s</p>", "Delete"));
                writer.println(String.format("<p>Id= %d</p>", id));

                try {
                    Boolean isDeleted = userDao.deleteUserById(id);
                    if (isDeleted) {
                        System.out.println("Deleted Succesfully");
                    } else {
                        System.out.println("Something went wrong");
                    }
                } catch (SQLException e) {
                    System.out.println("Something went wrong " + e.getMessage());
                    e.printStackTrace();
                }


                break;
        }
        response.sendRedirect("/account");
    }
}
