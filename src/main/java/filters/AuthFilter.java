package filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthFilter implements Filter {
    public static final String LOGIN_PATH = "/login";
    public static final String SIGNUP_PATH = "/signup";
    public static final String INDEX_PATH = "index.jsp";
    public static final String HOME_PATH = "home.jsp";
    public static final String USER_SESSION_ATRIBUTE = "Usuario";

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        String uri = request.getRequestURI();

        if (uri.endsWith(LOGIN_PATH) || uri.endsWith(SIGNUP_PATH) || uri.endsWith(INDEX_PATH) || uri.endsWith("/")) {
            if (session == null || session.getAttribute(USER_SESSION_ATRIBUTE) == null) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                response.sendRedirect(HOME_PATH);
            }
        } else {
            if (session == null || session.getAttribute(USER_SESSION_ATRIBUTE) == null) {
                response.sendRedirect(INDEX_PATH);
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
