<%@page import="Dominio.Cliente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:include page="Header.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body>
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
		  <div class="container-fluid">
		    <a class="navbar-brand" href="Inicio.jsp">FRGP BANK</a>
		    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		      <span class="navbar-toggler-icon"></span>
		    </button>
		    <div class="collapse navbar-collapse" id="navbarSupportedContent">
		      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
		        <li class="nav-item">
		          <a class="nav-link active" aria-current="page" href="MisCuentas.jsp">Cuentas</a>
		         </li>
		        <li class="nav-item">
		          	<a class="nav-link active" aria-current="page" href="Transferencias.jsp">Transferencias</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link active" aria-current="page" href="SolicitarPrestamo.jsp">Prestamos</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link active" aria-current="page" href="PagoDePrestamos.jsp">Ayuda</a>
		        </li>
		      </ul>
		      	<a class="nav-link active" aria-current="page" href="PerfilUsuario.jsp">Mi Perfil</a>
				<a class="btn" type="submit" href="ServletCerrarSesion">Cerrar sesi�n</a>
		    </div>
		  </div>
	</nav>
</body>
</html>