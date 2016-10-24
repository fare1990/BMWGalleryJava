<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.Comment" %>
<% ArrayList<Comment> comments = (ArrayList) request.getAttribute("commentslist"); %>
<%! public String printComment (Comment comment) {

        String res = "<li><div class=\"container\"><div class=\"row injectform\"><div class=\"col-md-3\"><header><span>User_Nickname</span><span>"
        +comment.getCreatedDate()
        +"</span><span class=\"commentId\" hidden >"
        +comment.getCommentId()
        +"</span></header><p class=\"commentText\">"
        +comment.getCommentText()
        +"</p></div><div class=\"col-md-3\"><a href=\"#\" class=\"replyClick\"><span>Reply</span></a></div></div></div>";
        if(comment.getSubcomments() != null) {
            for (Comment subComment : comment.getSubcomments()) {
                res = res + "<ul>"+printComment(subComment)+ "</ul>";
            }
        }
        res += "</li>";
        return res;
}
%>
<html>
<head>
    <title>
    			BMW Gallery
    		</title>
    		<link rel="stylesheet"
    				type="text/css"
    				href="font-awesome-4.5.0/css/font-awesome.css"
    		/>
    		<link rel="stylesheet"
    				type="text/css"
    				href="css/bootstrap.css"
    		/>
    		<link rel="stylesheet"
    				type="text/css"
    				href="css/style.css"
    		/>

    		<script src="js/jquery-2.2.2.js"></script>
    		<script src="js/jcollapsibleComments.js" type="text/javascript"></script>
    		<script src="js/myGallery.js" type="text/javascript"></script>
    		<script src="js/bootstrap.min.js"></script> <!-- bootsrap libraries -->
</head>
<body>

<div class="container">
    <div class="row">

			<div class="col-md-11">
				<img id="bigImage"
				  class="large-img"
				  src=${imagepath}
				  alt="Classic"
				  />
			</div>

    </div>
    <div class="row maincommentform">
        <div class="col-md-8">
            <form class="addcomment" method="post" action="comment">
                        <textarea name="commentText" cols="40" rows="3"></textarea>
                        <input type="hidden" name="pictureId" value=${pictureId} />
                        <input class="replyid" type="hidden" name="replyTo" value=""/>
                        <input type="submit" value="Comment" class="btn btn-success btn-sm" />
            </form>
        </div>
    </div>
    <div class="row">
        <ul id="comments">
        <% for (Comment c : comments) { %>
        <%=printComment(c) %>
        <% } %>
        </ul>
    </div>
</div>
<div class="col-md-8" id="hiddenCommentForm">
            <form class="addcomment" method="post" action="comment">
                        <textarea name="commentText" cols="40" rows="3"></textarea>
                        <input type="hidden" name="pictureId" value=${pictureId} />
						<input class="replyid" type="hidden" name="replyTo" />
                        <input type="submit" value="Comment" class="btn btn-success btn-sm" />
            </form>
</div>

</body>