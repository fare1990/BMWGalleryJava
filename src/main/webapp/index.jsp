<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="view.MainView" %>
<% MainView view = (MainView)request.getAttribute("view"); %>
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
    		<script src="js/my.js" type="text/javascript"></script>
    		<script src="js/bootstrap.min.js"></script> <!-- bootsrap libraries -->
</head>
<body>
<nav class="navbar navbar-default navbar-inverse" role="navigation">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
     <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
     <!-- <a class="navbar-brand" href="#">Login dropdown</a> -->
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="/home">Home page</a></li>
        <li><a href="/gallery?name=classic">Classic BMW Gallery</a></li>
		<li><a href="classicWheelsGal.html">Classic BMW Wheels Gallery</a></li>
		<li><a href="modernGallery.html">Modern BMW Gallery</a></li>
		<li><a href="modernWheelsGal.html">Modern BMW Wheels Gallery</a></li>
        <!--
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li class="divider"></li>
            <li><a href="#">Separated link</a></li>
            <li class="divider"></li>
            <li><a href="#">One more separated link</a></li>
          </ul>
        </li>
        -->
      </ul>
      <!--
	  <form class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
	  -->
      <ul class="nav navbar-nav navbar-right">
        <li><p class="navbar-text"><%=view.getUserName()%></p></li>

        <% if (view.isLogoutFormVisible()) {
                %>
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><b>Logout</b> <span class="caret"></span></a>
            <ul id="login-dp" class="dropdown-menu">
            				<li>
            					 <div class="row">
            							<div class="col-md-12">
            								 <form class="form" role="form" method="post" action="logout" accept-charset="UTF-8" id="login-nav">
            										<div class="form-group">
            											 <button type="submit" class="btn btn-primary btn-block">Logout</button>
            										</div>
            								 </form>
            							</div>
            					 </div>
            				</li>
            </ul>
        </li>
        <%
            }
         %>


        <% if (view.isLoginFormVisible()) {
        %>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown"><b>Login</b> <span class="caret"></span></a>
			<ul id="login-dp" class="dropdown-menu">
				<li>
					 <div class="row">
							<div class="col-md-12">
							<!--
								Login via
								<div class="social-buttons">
									<a href="#" class="btn btn-fb"><i class="fa fa-facebook"></i> Facebook</a>
									<a href="#" class="btn btn-tw"><i class="fa fa-twitter"></i> Twitter</a>
								</div>
                                or
                            -->
								 <form class="form" role="form" method="post" action="login" accept-charset="UTF-8" id="login-nav">
										<div class="form-group">
											 <label class="sr-only" for="exampleInputEmail2">Email address</label>
											 <input type="email" name = "email" class="form-control" id="exampleInputEmail2" placeholder="Email address" required>
										</div>
										<div class="form-group">
											 <label class="sr-only" for="exampleInputPassword2">Password</label>
											 <input type="password" name = "password" class="form-control" id="exampleInputPassword2" placeholder="Password" required>
                                             <div class="help-block text-right"><a href="">Forget the password ?</a></div>
										</div>
										<div class="form-group">
											 <button type="submit" class="btn btn-primary btn-block">Sign in</button>
										</div>
										<div class="checkbox">
											 <label>
											 <input type="checkbox" name = "keeplogin" value="keeplogin"> keep me logged-in
											 </label>
										</div>
								 </form>
							</div>
							<div class="bottom text-center">
								New here ? <a href="/register"><b>Join Us</b></a>
							</div>
					 </div>
				</li>
			</ul>
        </li>
        <%
        }
        %>


      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<div class="container">
	<h1 id="textInterval">${textH1}</h1>
	<div class="row interactiveSmallImgs">
		<div class="col-md-3 borders">
			<a href="/gallery?name=classic">
			<img  	id="image1"
					class="crop-img"
					src="classic_BMW_img/bmw-classic1.jpg"
					alt="Classic BMW"
			/>
			<p> Classic BMW Photo</p>
			</a>
		</div>
		<div class="col-md-3 borders">
			<a href="someshit1.html">
			<img
					id="image2"
					class="crop-img"
					src="classic_wheels_img/classic-wheels4.jpg"
					alt="Classic BMW Wheels"
			/>
			<p> Classic BMW Wheels</p>
			</a>
		</div>
		<div class="col-md-3 borders">
			<a href="someshit1.html">
			<img
					id="image3"
					class="crop-img"
					src="modern_BMW_img/BMW-Modern3.jpg"
					alt="Modern BMW"
			/>
			<p> Modern BMW Photo</p>
			</a>
		</div>
		<div class="col-md-3 borders">
			<a href="someshit1.html">
			<img
					id="image4"
					class="crop-img"
					src="modern_wheels_img/modern-wheels1.jpg"
					alt="Modern BMW Wheels"
			/>
			<p> Modern BMW Wheels</p>
			</a>
		</div>
		<% if (view.isUserGalleryVisible()) {
                        %>
        <h3>Your gallery</h3>
		<div class="col-md-3 borders">
        			<a href="/gallery?name=user">
        			<img
        					id="image5"
        					class="crop-img"
        					src=<%=view.getUserGalleryFirstImagePath()%>
        					alt=<%=view.getUserGalleryName()%>
        			/>
        			<p> <%=view.getUserGalleryName()%></p>
        			</a>
        </div>
        <%
        	}
        %>

         <% if (view.isGalleryCreateFormVisible()) {
                %>
		<div class="col-md-3 borders">
		    <p>Create your own gallery!</p>
		    <form class="form" role="form" method="post" action="home" accept-charset="UTF-8">
		        <input type="text" name="galleryname" placeholder="Galleryname" />
		        <input type="submit" value="Create" class="btn btn-success btn-sm" />
		    </form>
		</div>
		<%
		    }
		%>

	</div>
<!--
	<div class="row">
			<div class="col-md-1 arrow-navigation">
				<i class="fa fa-angle-left" id="backward"></i>
			</div>
			<div class="col-md-10">
				<img id="bigImage"
				  class="large-img"
				  src="classic_BMW_img/bmw-classic1.jpg"
				  alt="Classic"
				  />

			</div>
			<div class="col-md-1 arrow-navigation">
				<i class="fa fa-angle-right" id="forward"></i>
			</div>
	</div>
-->
</div>
</body>
</html>