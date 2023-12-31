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
	<div class="row mt-3 min-vh-100">
		<div class="col-1"></div>
		<div class="col-10">
		<div class="col-1"></div>
        <h2>Iniciar Sesi�n</h2>
        
        
         <% if (session.getAttribute("error") != null) { %>
        <div id="errorAlert" class="alert alert-danger">
             <%= (String)session.getAttribute("error")%> 
        </div>
    	<% } %>		  
    	
        <form action="ServletLogin" method="post">
            <div class="form-group">
                <label for="usuario">Usuario:</label>
                <input type="text" id="user" name="user" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="password">Contrase�a:</label>
                <input type="password" id="password" name="password" class="form-control" required>
            </div>
            <br>
            <button type="submit" class="btn btn-success" name="btnAceptar">Iniciar Sesi�n</button>
        </form>
        <br>
        <p>Si no ten�s cuenta, pod�s solicitarla en tu sucursal m�s cercana!</p>
		</div>
    </div>
    <jsp:include page="Footer.jsp"/>
    <% } %>	
</body>

<script>
    setTimeout(function() {
        var errorAlert = document.getElementById('errorAlert');
        if (errorAlert) {
            errorAlert.style.display = 'none';
        }
    }, 2500);
    
    setTimeout(function() {
        var successAlert = document.getElementById('successAlert');
        if (successAlert) {
            successAlert.style.display = 'none';
        }
    }, 2500);
</script>

</html>