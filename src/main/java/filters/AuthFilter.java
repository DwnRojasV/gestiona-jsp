package filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static constants.AppConstants.*;

public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        String uri = request.getRequestURI();

        if(uri.startsWith(request.getContextPath()+"/assets/")){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (uri.endsWith(LOGIN_PATH) || uri.endsWith(SIGNUP_PATH) || uri.endsWith(INDEX_PAGE) || uri.endsWith("/")) {

            if (session == null || session.getAttribute(USER_SESSION_ATRIBUTE) == null) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else if (session != null && uri.endsWith(LOGIN_PATH)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                response.sendRedirect(HOME_PAGE);
            }
        } else {
            if (session == null || session.getAttribute(USER_SESSION_ATRIBUTE) == null) {
                response.sendRedirect(INDEX_PAGE);
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
