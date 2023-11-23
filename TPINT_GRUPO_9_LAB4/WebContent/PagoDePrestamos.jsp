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
<title>FRGP Bank | Pagar Prestamos</title>
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
					<h1>Pago de Pr�stamos</h1>
					<form method="post" action="ServletListarCuotas">
							<input class="btn btn-success m-2" type="submit" name="btnListarCuotas" value="Ver Cuotas">
					</form>
					<%
					if(session.getAttribute("error") != null) {%>
							<div class="alert alert-danger">
					             <%= (String)session.getAttribute("error")%> 
					        </div>
					<%}
				     		ArrayList<Cuota> listaCuotas = null;
				     		 ArrayList<Cuenta> listaCuentas = null;
				      		if(request.getAttribute("listaCuotas")!= null){
				      			listaCuotas = (ArrayList<Cuota>) request.getAttribute("listaCuotas");
				      		if(request.getAttribute("listaCuentas")!= null){
				      			listaCuentas =(ArrayList<Cuenta>) request.getAttribute("listaCuentas");
				      		}%>
				      			
						   <table class="table">
							    <thead>
							        <tr>
							             <th scope="col">Fecha de pago</th>	
							            <th scope="col">Monto a pagar</th>
							            <th scope="col">N� de cuota</th>
							            <th scope="col">Cuotas totales</th>
							            <th scope="col">Pagar</th>
							        </tr>
							    </thead>
							    <tbody>
							        <% if (listaCuotas != null && !listaCuotas.isEmpty()) {
							            for (Cuota cta : listaCuotas) { %>
							            
								            <tr>
								                
								                 <td><%= cta.getFechaDePago() %></td>							                
								                <td><%= cta.getMontoAPagar() %></td>
								                <td><%= cta.getNro_Cuota() %></td>
								                <td><%= cta.getCuotas_Totales() %></td>
								                <td>
								                   <form action="ServletPagarCuota" method="post">
								                        <% if (cta.getEstado().equalsIgnoreCase("No Pagado")) { %>
								                        <select name="SelectCuentas" class="form-control p-2">
										                   <option value="Seleccionar">Seleccionar cuenta</option>
										                    <%if(listaCuentas != null && !listaCuentas.isEmpty()){
										                    for(Cuenta ct : listaCuentas){%>
										                    	<option value="<%=ct.getIdCuenta()%>"><%= ct.getNumeroCuenta() %></option>
										                    <%}} %>
							    	            		</select>
								                        <input type="hidden" name="IDCuota" value="<%= cta.getIDCuota() %>">
								                            <input type="submit" name="PagarCuota" value="Pagar" class="btn btn-success">
	
								                        <% }else{ %>
								                        	Cuota pagada
								                        	<%}%>
								                   </form>
								                </td>
								            </tr>
							            
							            <% } }%>
							    </tbody>
							</table>
							
						<% }%>
				</div>
				<div class="col-1"></div>
		</div>
				
	<!-- FINAL DEL CONTENIDO DE LA PAGINA-->
	<% } %>

</body>
</html>