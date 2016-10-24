
$(document).ready(function(){
	commentForm();
});


var commentFormVisible = false;

function commentForm(){
	$(".replyClick").click(function(e){
		if(commentFormVisible===false){
			$(e.target).parents(".injectform").append($("#hiddenCommentForm"));
			$("#hiddenCommentForm").find(".replyid").attr("value", $(e.target).parents(".injectform").find(".commentId").html());
			$("#hiddenCommentForm").fadeIn();
			$(".maincommentform").fadeOut();
			commentFormVisible = !commentFormVisible;
			return;
		}
		else {
			$(e.target).parents(".injectform").append($("#hiddenCommentForm"));
			$("#hiddenCommentForm").find(".replyid").attr("value", $(e.target).parents(".injectform").find(".commentId").html());
		}
	});
}


