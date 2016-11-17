package servlets;

import org.springframework.web.context.support.WebApplicationContextUtils;
import services.ImageService;
import services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Fare on 22.07.2016.
 */

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = WebApplicationContextUtils
                .getRequiredWebApplicationContext(config.getServletContext()).getBean(UserService.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //todo: check if email or username exists
        userService.createUser(request.getParameter("user"), request.getParameter("email"), request.getParameter("password"));
        response.sendRedirect("/home");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/registrationpage.jsp");
        dispatcher.forward(request, response);
    }
}
