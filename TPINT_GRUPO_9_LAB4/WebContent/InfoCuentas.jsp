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
	            <h2>Cuentas</h2>
				<hr>
				<p>Solicitá tu cuenta en 3 simples pasos!</p>
				
				<ol class="list-group list-group-numbered">
				  <li class="list-group-item d-flex justify-content-between align-items-start">
				    <div class="ms-2 me-auto">
				      <div class="fw-bold">Solicita tu usuario</div>
				      	<p>Dirigite a tu sucursal mas cercana y te lo dan al instante</p>
				
						<b><p>Si ya sos cliente, hace clic en el boton de abajo.</p></b>
				    </div>
				    
				  </li>
				  <li class="list-group-item d-flex justify-content-between align-items-start">
				    <div class="ms-2 me-auto">
				      <div class="fw-bold">Completa los datos on-line</div>
				      <p>Hace clic en el boton de abajo y elegí que tipo de cuenta queres, caja de ahorros o cuenta corriente.</p>
				    </div>
				    
				  </li>
				  <li class="list-group-item d-flex justify-content-between align-items-start">
				    <div class="ms-2 me-auto">
				      <div class="fw-bold">Listo</div>
				      <p>En menos de 24 horas tenes activa tu cuenta para empezar a operar.</p>
				    </div>
				    
				  </li>
				</ol>
				
				<br>
				<a class="btn btn-info" href="RegistroCuenta.jsp">Solicitá tu cuenta haciendo click acá</a>				
				
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