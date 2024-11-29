package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logic.User;
import logic.UserDao;

import java.io.IOException;

@WebServlet(name = "LoginServlet",
        urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");
        System.out.println(operation);

        if (operation.equals("login")) {
            this.login(request, response);
        }

    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Obtener los datos del formulario
        String email = (String) request.getParameter("email");
        String password = (String) request.getParameter("password");

        //realizar la consulta del usuario
        UserDao userDao = new UserDao();
        User user = userDao.Login(email, password);

        if (user == null) {
            request.setAttribute("mensaje", "Error en correo o contraseña");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("Usuario", user);
            response.sendRedirect("home.jsp");
        }

    }
}

