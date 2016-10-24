<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.Image" %>
<%@ page import="view.GalleryView" %>
<% GalleryView view = (GalleryView)request.getAttribute("view"); %>
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
    		<script src="js/myGallery.js" type="text/javascript"></script>
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
        <% switch (view.getUserGalleryName()) {
            case "classic": out.println(
                "<li><a href=\"/home\">Home page</a></li>" +
                "<li class=\"active\"><a href=\"/gallery?name=classic\">Classic BMW Gallery</a></li>" +
		        "<li><a href=\"/gallery?name=classicwheels\">Classic BMW Wheels Gallery</a></li>" +
                "<li><a href=\"/gallery?name=modern\">Modern BMW Gallery</a></li>" +
                "<li><a href=\"/gallery?name=modernWheels\">Modern BMW Wheels Gallery</a></li>");
            break;

            case "classicwheels": out.println(
                "<li><a href=\"/home\">Home page</a></li>"+
                "<li><a href=\"/gallery?name=classic\">Classic BMW Gallery</a></li>"+
        		"<li class=\"active\"><a href=\"/gallery?name=classicwheels\">Classic BMW Wheels Gallery</a></li>"+
                "<li><a href=\"/gallery?name=modern\">Modern BMW Gallery</a></li>"+
                "<li><a href=\"/gallery?name=modernWheels\">Modern BMW Wheels Gallery</a></li>");
            break;

            case "modern":  out.println(
                "<li><a href=\"/home\">Home page</a></li>"+
                "<li><a href=\"/gallery?name=classic\">Classic BMW Gallery</a></li>"+
                "<li><a href=\"/gallery?name=classicwheels\">Classic BMW Wheels Gallery</a></li>"+
                "<li class=\"active\"><a href=\"/gallery?name=modern\">Modern BMW Gallery</a></li>"+
                "<li><a href=\"/gallery?name=modernWheels\">Modern BMW Wheels Gallery</a></li>");
            break;

            case "modernWheels": out.println(
                "<li><a href=\"/home\">Home page</a></li>"+
                "<li><a href=\"/gallery?name=classic\">Classic BMW Gallery</a></li>"+
                "<li><a href=\"/gallery?name=classicwheels\">Classic BMW Wheels Gallery</a></li>"+
                "<li><a href=\"/gallery?name=modern\">Modern BMW Gallery</a></li>"+
                "<li class=\"active\"><a href=\"/gallery?name=modernWheels\">Modern BMW Wheels Gallery</a></li>");
            break;

            default: out.println(
                 "<li><a href=\"/home\">Home page</a></li>"+
                 "<li><a href=\"/gallery?name=classic\">Classic BMW Gallery</a></li>"+
                 "<li><a href=\"/gallery?name=classicwheels\">Classic BMW Wheels Gallery</a></li>"+
                 "<li><a href=\"/gallery?name=modern\">Modern BMW Gallery</a></li>"+
                 "<li><a href=\"/gallery?name=modernWheels\">Modern BMW Wheels Gallery</a></li>");

        }

     %>

      </ul>

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
	<h1 id="textInterval"><%=view.getUserGalleryName()%></h1>
	<div class="row">

	<% if (view.isImageAddFormVisible()) {
                    %>
	<div class="col-md-3 borders">
    		    <p>Add image!</p>
    		    <form class="form" role="form" method="post" action="upload" enctype="multipart/form-data">
    		        <input type="text" name="description" placeholder="imagename" />
    		        <input type="file" name="file" />
    		        <input type="submit" value="Add" class="btn btn-success btn-sm" />
    		    </form>
    </div>
    <%
       }
    %>

	<%
      ArrayList<Image> images = (ArrayList)view.getImagelist();
      if (images != null) {
      int id;
        for (int i = 0; i < images.size(); i++) {
            id = images.get(i).getImageId();

      %>
    		<div class="col-md-3 borders">
    		    <a href=<%="/showimage?id="+id%> >
    			<img  	id=<%=id%>
    					class="crop-img"
    					src=<%=images.get(i).getImagePath()%>
    					alt="Classic BMW"
    			/>
    			</a>
    		</div>
    		<%
    		}
    	}
    %>
    </div>

<!--
    <div class="row" id="hidableBigImage">

			<div class="col-md-11">
				<img id="bigImage"
				  class="large-img"
				  src="classic_BMW_img/bmw-classic1.jpg"
				  alt="Classic"
				  />
			</div>

	</div>
	-->
</div>

<!--
<div id="shadow">
</div>
-->

</body>
</html>