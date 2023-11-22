<%@page import="entidad.Cliente"%>
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
	<% if (session.getAttribute("usuarioAutenticado") == null) { 
		%>
		<jsp:include page="Navbar.jsp"/>
	<%
	} else { 
		Cliente cliente = (Cliente) session.getAttribute("usuarioAutenticado"); 	
		if(cliente.is_Admin()){%>
			<jsp:include page="NavbarAdmin.jsp"/>
		<% }else{ %>
			<jsp:include page="NavbarClientes.jsp" />
		<% }} %>
	<!-- DEMAS CONTENIDO DE LA PAGINA-->
	<div class="container mt-4">
	    <div class="row">
	        <div class="col-lg-8">
	            <h2>Prestamos</h2>
				<hr>
				<p>En <strong>FRGP BANK</strong> podrás encontrar las mejores oportunidades con la mejor financiación.</p>
				
				<p>Tene eso que tanto queres! Solicita tu prestamo on-line y recibi el monto en menos de 24 horas, y pagalo en hasta 36 cuotas a tasa 0%! </p>
				
				<h5>Si, leiste bien! Con 0 intereses.</h5>
				<br>
				<a class="btn btn-info" href="SolicitarPrestamo.jsp">Solicitá tu prestamo haciendo click acá</a>				
				
	        </div>
	        <div class="col-lg-4">
	            <div class="row">
		            <img src="https://drive.google.com/uc?export=download&id=1-V8fvvy6L8oYXsqdY9FoecZcGHoj3YwM" class="img-fluid rounded" alt="Imagen Sobre Nosotros">
	            </div>
	        </div>
	    </div>
	</div>
	<!-- FINAL DEL CONTENIDO DE LA PAGINA-->

</body>
</html>