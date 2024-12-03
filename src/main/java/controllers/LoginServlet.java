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
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if (operation.equals("login")) {
            this.login(request, response);
        } else if (operation.equals("logout")) {
            this.logout(request, response);
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Obtener los datos del formulario
        String email = (String) request.getParameter("email");
        String password = (String) request.getParameter("password");

        //realizar la consulta del usuario
        UserDao userDao = new UserDao();
        User user = userDao.login(email, password);

        if (user == null) {
            request.setAttribute("mensaje", "Correo o Contraseña incorrectos");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("Usuario", user);
            response.sendRedirect("home.jsp");
        }

    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        session.invalidate();
        request.setAttribute("message", "Log in");
        request.getRequestDispatcher("home.jsp").forward(request,response);
/*        response.sendRedirect("index.jsp");*/
    }
}

