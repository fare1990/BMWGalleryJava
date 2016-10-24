package filters;

import entity.User;
import services.ApplicationContext;
import services.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Fare on 18.10.2016.
 */

@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        UserService  userService = ApplicationContext.getInstance().get(UserService.class);
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        Cookie[] cookies = servletRequest.getCookies();
        User user = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user")) {
                    HttpSession userSession = servletRequest.getSession();
                    user = (User)userSession.getAttribute("currentuser");
                    if(user != null) {
                        request.setAttribute("user", user);
                        chain.doFilter(request, response);
                        return;
                    }
                    else{
                        user = userService.getUserByEmail(cookie.getValue());
                        userSession.setAttribute("currentuser", user);
                        request.setAttribute("user", user);
                        chain.doFilter(request, response);
                        return;
                    }
                }
            }
        }
        request.setAttribute("user", user);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
