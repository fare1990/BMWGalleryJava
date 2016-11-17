
$(document).ready(function(){
	smallToBigImage("#image1");
	changingColors();
	getViewModel();
	/*$('a').click(function() {
	var selector = $(this).find("img").get();
	smallToBigImage(selector);
	return false; });*/
});




var counter =0;
var imagecounter = 1;

function getViewModel(){
	 $.ajax({
			type: 'GET',
			url : "home",
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				display(data);
			}
		});

}

function display(view){
	$("h1").html(view.headerText);
	$('#userName').html(view.userName);
	var loginForm = '<a href="#" class="dropdown-toggle" data-toggle="dropdown"><b>Login</b> <span class="caret"></span></a>';
		loginForm +=	'<ul id="login-dp" class="dropdown-menu">';
		loginForm +=	'<li>';
		loginForm +=	'<div class="row">';
		loginForm +=	'<div class="col-md-12">';
		loginForm +=	'<form class="form" role="form" method="post" action="login" accept-charset="UTF-8" id="login-nav">';
		loginForm +=	'<div class="form-group">';
		loginForm +=	'<label class="sr-only" for="exampleInputEmail2">Email address</label>';
		loginForm +=	'<input type="email" name = "email" class="form-control" id="exampleInputEmail2" placeholder="Email address" required>';
		loginForm +=	'</div>';
		loginForm +=	'<div class="form-group">';
		loginForm +=	'<label class="sr-only" for="exampleInputPassword2">Password</label>';
		loginForm +=	'<input type="password" name = "password" class="form-control" id="exampleInputPassword2" placeholder="Password" required>';
        loginForm +=	'<div class="help-block text-right"><a href="">Forget the password ?</a></div>';
		loginForm +=	'</div>';
		loginForm +=	'<div class="form-group">';
		loginForm +=	'<button type="submit" class="btn btn-primary btn-block">Sign in</button>';
		loginForm +=	'</div>';
		loginForm +=	'<div class="checkbox">';
		loginForm +=	'<label>';
		loginForm +=	'<input type="checkbox" name = "keeplogin" value="keeplogin"> keep me logged-in';
		loginForm +=	'</label>';
		loginForm +=	'</div>';
		loginForm +=	'</form>';
		loginForm +=	'</div>';
		loginForm +=	'<div class="bottom text-center">';
		loginForm +=	'New here ? <a href="/register"><b>Join Us</b></a>';
		loginForm +=	'</div>';
		loginForm +=	'</div>';
		loginForm +=	'</li>';
		loginForm +=	'</ul>';

	var logoutForm = '<a href="#" class="dropdown-toggle" data-toggle="dropdown"><b>Logout</b> <span class="caret"></span></a>';
        logoutForm += '<ul id="login-dp" class="dropdown-menu">';
        logoutForm += '<li>';
        logoutForm += '<div class="row">';
        logoutForm += '<div class="col-md-12">';
        logoutForm += '<form class="form" role="form" method="post" action="logout" accept-charset="UTF-8" id="login-nav">';
        logoutForm += '<div class="form-group">';
        logoutForm += '<button type="submit" class="btn btn-primary btn-block">Logout</button>';
        logoutForm += '</div>';
        logoutForm += '</form>';
        logoutForm += '</div>';
        logoutForm += '</div>';
        logoutForm += '</li>';
        logoutForm += '/ul>';

	if(view.loginFormVisible){
		$('.view-login').html(loginForm);
		$('.hide-navigation').fadeIn();
	}
	else{
		$('.view-login').html(logoutForm);
		$('.hide-navigation').fadeIn();
	}

	if(view.userGalleryVisible){
		$('.userGallery').find('img').attr('src', view.userGalleryFirstImagePath);
		$('.userGallery').find('img').attr('alt', view.userGalleryName);
		$('.userGallery').find('p').html(view.userGalleryName);
		$('.userGallery').fadeIn();
	}
	else{
		$('.createGallery').fadeIn();
	}
}

function smallToBigImage(selector){
	//$(".crop-img").click(function(){
			$("#bigImage").attr('src',$(selector).attr('src'));
			//});
}

function changingColors(){
	$("#hren").hover(function(){$("#hren").css('color', 'red');},
		function(){$("#hren").css('color', 'blue');});
}

