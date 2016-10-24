package servlets;


import services.ApplicationContext;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by Fare on 20.07.2016.
 */

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService userService = ApplicationContext.getInstance().get(UserService.class);
        String email = request.getParameter("email");
        if (userService.checkLogin(email, request.getParameter("password"))) {
            Cookie loginCookie = new Cookie("user", email);
            if (request.getParameter("keeplogin") != null)
                loginCookie.setMaxAge(60 * 60 * 24 * 3);
            else
                loginCookie.setMaxAge(10 * 60);
            response.addCookie(loginCookie);
            HttpSession session = request.getSession();
            session.setAttribute("currentuser", userService.getUserByEmail(email));
            session.setMaxInactiveInterval(5*60);
            response.sendRedirect("/home");
        } else {
            response.sendRedirect("/incorrectlogin");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doGet(request, response);
    }
}
