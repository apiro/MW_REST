<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    	               "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    	<title>GlassFish JSP Page</title>
    	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
  </head>
  <body>
  <nav class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">BoardGameREST</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="./index.jsp">Home</a></li>
      <li><a href="./register.html">Register</a></li>
      <li><a href="./login.html" >Login</a></li>
      <li><a href="http://localhost:8080/BroadGamesREST/jaxrs/init" >Init with sample data</a></li>
      <li><a href="./guest.html" >Guest Area</a></li>
    </ul>
  </div>
</nav>
  
<div class="container" style="margin-top:50px">
  <h3>BoardGameREST</h3>
  <p>First of all you have to fill the database with sample data, then you can register new users or log in with as an existing user.
	If you don't want to surf as a Power User you can also navigate as a Guest.</p>
</div>
  </body>
</html> 
