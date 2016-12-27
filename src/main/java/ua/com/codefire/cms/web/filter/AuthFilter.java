package ua.com.codefire.cms.web.filter;

import ua.com.codefire.cms.model.AttributeNames;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by human on 12/1/16.
 */
@WebFilter("/*")
public class AuthFilter implements Filter {

    private FilterConfig filterConfig;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getServletPath().indexOf("/res") > 0) {
            chain.doFilter(request, response);
            return;
        }

        if (req.getServletPath().startsWith("/admin")) {
            Boolean isAuth = (Boolean) req.getSession().getAttribute(AttributeNames.SESSION_AUTHENTICATED);

            if (isAuth != null && isAuth) {
                chain.doFilter(request, response);
            } else {
                ((HttpServletResponse)  response).sendRedirect("/auth");
                //req.getRequestDispatcher("/auth").forward(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }

//        req.getSession().removeAttribute("flash_message");
    }

    public void destroy() {
        this.filterConfig = null;
    }
}
