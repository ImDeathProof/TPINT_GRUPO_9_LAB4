<%@page import="entidad.Cliente"%>
<%@page import="entidad.Cuota"%>
<%@page import="entidad.Cuenta"%>
<%@page import="entidad.Prestamo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:include page="Header.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FRGP Bank | Préstamos Aprobados</title>
</head>
<body>
	<% if (session.getAttribute("usuarioAutenticado") == null) { 
			response.sendRedirect("Inicio.jsp");
		} else { 
			Cliente cliente = (Cliente) session.getAttribute("usuarioAutenticado"); 	
			if(cliente.is_Admin()){%>
				<jsp:include page="NavbarAdmin.jsp"/>
			<% }else{ %>
				<jsp:include page="NavbarClientes.jsp" />
			<% } %>
		<!-- DEMAS CONTENIDO DE LA PAGINA-->
			<div class="row">
					<div class="col-1"></div>
					<div class="col-10">
						<h1>Préstamos Aprobados</h1>
						<form method="post" action="ServletPrestamosPorUsuario">
								<input class="btn btn-success m-2" type="submit" name="btnListarPrestamos" value="Ver mis Préstamos">
						</form>
						<%
					     		ArrayList<Prestamo> lista = new ArrayList<Prestamo>();
					      		if(request.getAttribute("listaPrestamos")!=null){
					      			lista = (ArrayList<Prestamo>) request.getAttribute("listaPrestamos");%>
					      			
							   <table class="table">
								    <thead>
								        <tr>
								            <th scope="col">Fecha</th>
								            <th scope="col">Monto Solicitado</th>
								            <th scope="col">Monto Aprobado</th>
								        </tr>
								    </thead>
								    <tbody>
								        <% if (lista != null) {
								            for (Prestamo pr : lista) { %>
								            <tr>
								                <td><%= pr.getFechaPedido() %></td>
								                <td><%= pr.getMonto() %></td>
								                <td><%= pr.getMontoAprobado() %></td>
								            </tr>
								            <% } } %>
								    </tbody>
								</table>
								
							
							<%} %>
					</div>
					<div class="col-1"></div>
			</div>
				
	<!-- FINAL DEL CONTENIDO DE LA PAGINA-->
	<% } %>
</body>
</html>