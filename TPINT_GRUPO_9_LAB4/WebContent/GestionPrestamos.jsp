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
						 <%if (session.getAttribute("errorGsPrestamos") != null){%>
						<div class="alert alert-danger">
							<%= (String)session.getAttribute("errorGsPrestamos")%>
						</div>
						<hr>
						<%}else if(session.getAttribute("exitoGsPrestamos") != null){ %>
						 <div class="alert alert-success">
							<%= (String)session.getAttribute("exitoGsPrestamos")%>
						</div>
						<hr>
						 <%}
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
							            <th scope="col">Cantidad de cuotas</th>
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
							                <td><%= pr.getCantidadCuotas() %></td>
							                <td><%= pr.getEstado() %></td>
							                <td>
							                    
							                        <input type="hidden" name="Id_Prestamo" value="<%= pr.getId_Prestamo() %>">
							                        <% if (pr.getEstado().equalsIgnoreCase("Pendiente")) { %>
							                            <!-- Button trigger modal + Aprobar prestamo-->
							                            <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#AprobarModal">
														  Aprobar
														</button>
							                            
							                        	<!-- Modal aprobar prestamo-->
													    <div class="modal fade" id="AprobarModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
													        <div class="modal-dialog">
													        <div class="modal-content">
													            <div class="modal-header">
														            <h1 class="modal-title fs-5" id="exampleModalLabel">Aprobar</h1>
														            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
														            </div>
														            <div class="modal-body">
														            ¿Está seguro que desea aprobar el prestamo?
														            </div>
														            <div class="modal-footer">
														            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
														            <form action="ServletGestionarPrestamos" method="post">
												                        <input type="hidden" name="Id_Prestamo" value="<%= pr.getId_Prestamo() %>">
												                        <input type="submit" name="submitValue" value="Aprobar" class="btn btn-success">
														            </form>
													            </div>
													        </div>
													        </div>
													    </div>
							                        
							                        	
														<!-- Button trigger modal + Rechazar prestamo-->
							                            <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#RechazarModal">
														  Rechazar
														</button>
							                            
							                        	<!-- Modal rechazar prestamo-->
													    <div class="modal fade" id="RechazarModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
													        <div class="modal-dialog">
													        <div class="modal-content">
													            <div class="modal-header">
														            <h1 class="modal-title fs-5" id="exampleModalLabel">Rechazar</h1>
														            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
														            </div>
														            <div class="modal-body">
														            ¿Está seguro que desea rechazar el prestamo?
														            </div>
														            <div class="modal-footer">
														            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
														            <form action="ServletGestionarPrestamos" method="post">
												                        <input type="hidden" name="Id_Prestamo" value="<%= pr.getId_Prestamo() %>">
												                        <input type="submit" name="submitValue" value="Rechazar" class="btn btn-danger">
														            </form>
													            </div>
													        </div>
													        </div>
													    </div>
							                        <% } else { %>
							                        	<label>Sin Acciones</label>
							                        <% } %>
							                    
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