package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logic.CustomException;
import logic.Password;
import logic.User;
import logic.UserDao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import static constants.AppConstants.*;

@WebServlet(name = "AccountServlet",
        urlPatterns = ACCOUNT_PATH)
public class AccountServlet extends HttpServlet {

    public AccountServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ArrayList<User> users = null;
        try {
            users = this.getUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute(USER_LIST_ATRIBUTE, users);
        request.getRequestDispatcher(PROFILE_PAGE).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String operation = (request.getParameter(SUBMIT_OPERATION) != null)
                ? request.getParameter(SUBMIT_OPERATION)
                : "";
        if (operation.equals(USER_UPDATE_ATRIBUTE)) this.updateUser(request, response);

    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        Boolean isUpdated = false;
        UserDao userDao = new UserDao();
        String userName = request.getParameter(USER_NAME_PARAMETER);
        String phone = request.getParameter(USER_PHONE_PARAMETER);
        String currentPass = request.getParameter(USER_CURRENT_PASS_PARAMETER);
        String newPass = request.getParameter(USER_NEW_PASS_PARAMETER);
        String confimationPass = request.getParameter(USER_CONFIRM_PASS_PARAMETER);

        User user = (User) session.getAttribute(USER_SESSION_ATRIBUTE);
        Boolean isCorrectPassword = Password.validatePassword(currentPass, user.getHashedPassword());

        if (!isCorrectPassword) {
            request.setAttribute(UPDATE_MESSAGE_ATRIBUTE, INCORRECT_PASSWORD_MESSAGE);
            return;
        }
        if (!newPass.equals(confimationPass)) {
            request.setAttribute(UPDATE_MESSAGE_ATRIBUTE, NOT_MATCH_PASSWORD_MESSAGE);
            return;
        }
        String newPassHash = Password.encryptPassword(newPass);
        try {
            isUpdated = userDao.updateUser(user, userName, newPassHash, phone);
        } catch (SQLException e) {
            request.setAttribute(UPDATE_MESSAGE_ATRIBUTE, e.getMessage());
        } catch (CustomException e) {
            request.setAttribute(UPDATE_MESSAGE_ATRIBUTE, e.getMessage());
        }

        if (isUpdated) {
            request.setAttribute(UPDATE_MESSAGE_ATRIBUTE, USER_UPDATED_SUCCES);
        }
        String msg = (String) request.getAttribute(UPDATE_MESSAGE_ATRIBUTE);
        PrintWriter writer = response.getWriter();
        writer.println("<script type='text/javascript'>");
        writer.println(String.format("alert(%s)", msg));
        writer.println("</script>");
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }

    private ArrayList<User> getUsers() throws SQLException {
        UserDao userDao = new UserDao();
        return userDao.getAllUsers();
    }
}
