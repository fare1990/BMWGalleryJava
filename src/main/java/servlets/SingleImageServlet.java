package servlets;

import services.ApplicationContext;
import services.CommentsService;
import services.ImageService;
import services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Fare on 05.08.2016.
 */

@WebServlet("/showimage")
public class SingleImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CommentsService commentsService = ApplicationContext.getInstance().get(CommentsService.class);
        ImageService imageService = ApplicationContext.getInstance().get(ImageService.class);
        int pictureId = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("commentslist", commentsService.getCommentsToPicture(pictureId));
        request.setAttribute("imagepath", imageService.getImagePathById(pictureId));
        request.setAttribute("pictureId", pictureId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/showimage.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
