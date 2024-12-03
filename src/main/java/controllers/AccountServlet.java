package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logic.User;

import java.io.IOException;

import static constants.AppConstants.*;

@WebServlet(name = "AccountServlet",
        urlPatterns = ACCOUNT_PATH)
public class AccountServlet extends HttpServlet {

    public AccountServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            request.getRequestDispatcher(PROFILE_PAGE).forward(request,response);
    }
}
