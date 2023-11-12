<%@page import="entidad.Cliente"%>
<%@page import="entidad.Movimiento"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FRGP Bank | Informe Movimientos</title>
</head>
<body>

	 <%if(request.getSession().getAttribute("usuarioAutenticado")!=null){
		Cliente usuario = (Cliente)request.getSession().getAttribute("usuarioAutenticado");
		if(usuario.is_Admin()==true){
		%>
			<jsp:include page="NavbarAdmin.jsp"/>
			<div class="row">
				<div class="col-1"></div>
				<div class="col-10">
					<h1>Lista Movimientos</h1>
					<div class="border-top p-2">
						<form method="post" action="ServletMovimientoXPeriodo">
							<div class="col-5">
					           	     <div class="form-group">
									    <label for="fechaNacimiento">Fecha de inicio:</label>
									    <input type="date" id="fechaInicio" name="fechaInicio" class="form-control" required>
									</div>
									 <div class="form-group">
									    <label for="fechaNacimiento">Fecha de fin:</label>
									    <input type="date" id="fechaFin" name="fechaFin" class="form-control" required>
									</div>
									  <div class="form-group">
							            <label for="ordenarPor">Ordenar por:</label>
							            <select id="ordenarPor" name="ordenarPor" class="form-control">
							                <option value="Fecha">Fecha</option>
							                <option value="Monto">Monto</option>
							            </select>
							        </div>
									<button type="submit" class="btn btn-success">Ver movimientos del periodo</button>
							</div>
					 	</form>
						 <%
				     		ArrayList<Movimiento> listaMov = null;
				      		if(request.getAttribute("listaMovimientosPeriodo")!=null){
				      			listaMov = (ArrayList<Movimiento>) request.getAttribute("listaMovimientosPeriodo");%>		      			
						   <table class="table">
							    <thead>
							        <tr>
							            <th scope="col">Monto</th>
							            <th scope="col">Fecha</th>
							            <th scope="col">Detalles/Tipo Movimiento</th>
							            <th scope="col">IdUsuario</th>
							            <th scope="col">IdCuenta</th>
							        </tr>
							    </thead>
							    <tbody>
							        <% if (listaMov != null) {
							            for (Movimiento mov : listaMov) { %>
							            <tr>
							                <td><%= mov.getMonto() %></td>
							                <td><%= mov.getFecha() %></td>
							                <td><%= mov.getDetalles() %></td>
							                <td><%= mov.getIdCliente() %></td>
							                <td><%= mov.getIdCuenta() %></td>
							            </tr>
							            <% } } %>
							    </tbody>
							</table>
							<form method="get" action="ServletMovimientoXPeriodo">
						    <%
						    int cantPags = (int)request.getAttribute("cantPagsInforme");
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