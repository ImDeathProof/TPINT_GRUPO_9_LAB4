<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:include page="Header.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FRGP BANK | Log-In</title>
</head>
<body>
	<% if (session.getAttribute("usuarioAutenticado") != null) { 
	response.sendRedirect("Inicio.jsp");
	} else { %>
	<jsp:include page="Navbar.jsp" />
	<!-- DEMAS CONTENIDO DE LA PAGINA -->
	<div class="row mt-3">
		<div class="col-1"></div>
		<div class="col-10">
		<div class="col-1"></div>
        <h2>Iniciar Sesión</h2>
         <% if (session.getAttribute("error") != null) { %>
        <div class="alert alert-danger">
             <%= (String)session.getAttribute("error")%> 
        </div>
    	<% } %>		    
        <form action="ServletLogin" method="post">
            <div class="form-group">
                <label for="usuario">Usuario:</label>
                <input type="text" id="user" name="user" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="password">Contraseña:</label>
                <input type="password" id="password" name="password" class="form-control" required>
            </div>
            <br>
            <button type="submit" class="btn btn-success" name="btnAceptar">Iniciar Sesión</button>
        </form>
		</div>
    </div>
    <% } %>	
</body>
</html>