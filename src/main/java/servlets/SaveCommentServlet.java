package servlets;

import entity.User;
import org.springframework.web.context.support.WebApplicationContextUtils;
import services.CommentsService;
import services.ImageService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Fare on 07.09.2016.
 */

@WebServlet("/comment")
public class SaveCommentServlet extends HttpServlet {

    CommentsService commentsService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        commentsService = WebApplicationContextUtils
                .getRequiredWebApplicationContext(config.getServletContext()).getBean(CommentsService.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //CommentsService commentsService = AppContext.getInstance().get(CommentsService.class);
        User user = (User)request.getAttribute("user");
        if (user != null) {
            String pictureId = request.getParameter("pictureId");
            String replyTo = request.getParameter("replyTo");
            commentsService.saveComment(pictureId,
                    replyTo,
                    request.getParameter("commentText"),
                    user.getUserId());

            response.sendRedirect("/showimage?id=" + pictureId);
        }
        else {
            response.sendRedirect("/home");
        }
    }
}
