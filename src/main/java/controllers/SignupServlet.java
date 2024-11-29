package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.UserDao;

import java.io.IOException;

@WebServlet(name = "SignupServlet",
        urlPatterns = "/signup")
public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SignupServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("sign-up.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");
        String name = request.getParameter("fullname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Boolean isRegistered = false;
        UserDao userDao = new UserDao();
        if (operation.equals("register")) {
            try {
                isRegistered = userDao.signup(email, name, password);
            } catch (Exception e) {
                System.out.println("Something went wrong during the registration process." + e.getMessage());
                e.printStackTrace();
            }

            if (isRegistered) {
                request.setAttribute("signupMessage", "¡Registro exitoso!\n" +
                        "Tu cuenta ha sido creada exitosamente. Por favor, inicia sesión para continuar.\n");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

        }
    }
}
