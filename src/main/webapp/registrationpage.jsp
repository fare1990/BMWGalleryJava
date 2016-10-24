<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<link rel="stylesheet"
    				type="text/css"
    				href="css/bootstrap.css"
    		/>
    		<link rel="stylesheet"
    				type="text/css"
    				href="css/incorrectlogin.css"
    		/>
</head>
<body>
<h1 id="header">Welcome to the registration page!</h1>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="wrap">
                <p class="form-title">Registration</p>
                <form class="login" method="post" action="register">
                    <input type="text" name="user" placeholder="Username" />
                    <input type="text" name="email" placeholder="Email" />
                    <input type="password" name="password" placeholder="Password" />
                    <input type="submit" value="Register" class="btn btn-success btn-sm" />
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>