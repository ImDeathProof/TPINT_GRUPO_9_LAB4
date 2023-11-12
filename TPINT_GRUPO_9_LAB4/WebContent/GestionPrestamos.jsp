<%@page import="entidad.Prestamo"%>
<%@page import="entidad.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:include page="Header.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FRGP Bank | Gaestión Prestamos</title>
</head>
<body>
	<%if(request.getSession().getAttribute("usuarioAutenticado")!=null){
		Cliente usuario = (Cliente) request.getSession().getAttribute("usuarioAutenticado");
		if(usuario.is_Admin()==true){
		%>
			<jsp:include page="NavbarAdmin.jsp"/>
			<div class="row">
				<div class="col-1"></div>
				<div class="col-10">
					<h1>Gestión de Préstamos</h1>
					<div class="border-top p-2">
						<form method="post" action="ServletListarPrestamos">
							<input class="btn btn-success m-2" type="submit" name="btnListarPrestamos" value="Prestamos">
					 	</form>
						 <%
				     		ArrayList<Prestamo> listaPrestamos = null;
				      		if(request.getAttribute("listaPrestamos")!=null){
				      			listaPrestamos = (ArrayList<Prestamo>) request.getAttribute("listaPrestamos");%>
				      			
						   <table class="table">
							    <thead>
							        <tr>
							            <th scope="col">Fecha</th>
							            <th scope="col">Usuario</th>
							            <th scope="col">Monto Solicitado</th>
							            <th scope="col">Monto Aprobado</th>
							            <th scope="col">Estado</th>
							            <th scope="col">Acción</th>
							        </tr>
							    </thead>
							    <tbody>
							        <% if (listaPrestamos != null) {
							            for (Prestamo pr : listaPrestamos) { %>
							            <tr>
							                <td><%= pr.getFechaPedido() %></td>
							                <td><%= pr.getCliente().get_Usuario() %></td>
							                <td><%= pr.getMonto() %></td>
							                <td><%= pr.getMontoAprobado() %></td>

							                <td><%= pr.getEstado() %></td>
							                <td>
							                    <form action="ServletGestionarPrestamos" method="post">
							                        <input type="hidden" name="Id_Prestamo" value="<%= pr.getId_Prestamo() %>">
							                        <% if (pr.getEstado().equalsIgnoreCase("Pendiente")) { %>
							                            <input type="submit" name="submitValue" value="Aprobar" class="btn btn-success">
							                            <input type="submit" name="submitValue" value="Rechazar" class="btn btn-danger">
							                        <% } else { %>
							                        	<label>Sin Acciones</label>
							                        <% } %>
							                    </form>
							                </td>
							            </tr>
							            <% } } %>
							    </tbody>
							</table>
							<form method="get" action="ServletListarPrestamos">
						    <%
						    int cantPags = (int)request.getAttribute("cantPags");
						    if (cantPags != 0) {
						        for (int i = 1; i <= cantPags; i++) {
						    %>
						        <button class="btn btn-primary" type="submit" name="pagina" value="<%= i %>">
						            Página <%= i %>
						        </button>
						    <%
						        } 
						    }
						    %>
						</form>
						<% } %>
					</div>
				</div>
			</div>
		<%
		
		}else{
			response.sendRedirect("Inicio.jsp");
		}} %>
</body>
</html>