<%@page import="Dominio.Cliente"%>
<%@ page import="java.util.ArrayList, Dominio.Cuenta" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mis Cuentas</title>
</head>
<body>
	<% if (session.getAttribute("usuarioAutenticado") == null) { 
	response.sendRedirect("Login.jsp");
	} else { 
		Cliente cliente = (Cliente) session.getAttribute("usuarioAutenticado"); 	
		if(cliente.is_Admin()){%>
			<jsp:include page="NavbarAdmin.jsp"/>
		<% }else{ %>
			<jsp:include page="NavbarClientes.jsp" />
		<% } %>
	<div class="row">
		<div class="col-1"></div>
		<div class="col-10">
		<h2>Mis Cuentas</h2>
	    
	   
		<form action="ServletCuenta" method="get">
		    <label for="filtroCuentas">Seleccione el tipo de cuenta:</label>
		    <select name="filtroCuentas" id="filtroCuentas">
		        <option value="todos">Todos los tipos</option>
		        <option value="ahorros">Caja de ahorro</option>
		        <option value="corrientes">Cuentas corriente</option>
		    </select>
		    <button type="submit" class="btn btn-primary" name="btnFiltrar">Ver cuentas</button>
	    	<h4>Pedí tu cuenta haciendo clic<a class="btn" type="submit" href="RegistroCuenta.jsp">ACA</a></h4>
		</form>
	
	    <%
	   
	    ArrayList<Cuenta> cuentas = (ArrayList<Cuenta>) request.getAttribute("cuentas");
	    String filtroCuentas = request.getParameter("filtroCuentas");
	    
	    if (cuentas != null) {
	        for (Cuenta cuenta : cuentas) {
	            // Verifica si se aplica un filtro
	            if (filtroCuentas == null || filtroCuentas.equals("todos") || (filtroCuentas.equals("ahorros") && cuenta.getTipoCuenta().equals("Ahorros")) || (filtroCuentas.equals("corrientes") && cuenta.getTipoCuenta().equals("Corriente"))) {
				%>
				              
				                <div>
				                    <p>ID Cuenta: <%= cuenta.getIdCuenta() %></p>
				                    <p>Tipo de cuenta: <%= cuenta.getTipoCuenta() %></p>
				                    <p>Número de Cuenta: <%= cuenta.getNumeroCuenta() %></p>
				                    <p>CBU: <%= cuenta.getCBU() %></p>
				                    <p>Saldo: <%= cuenta.getSaldo() %></p>
				                    <p>Fecha de creación: <%= cuenta.getFechaCreacion() %></p>
				                    <% if (!cuenta.isEstado()) { %>
									    <p>Estado: En proceso</p>
									<% } %>
				                </div>
				<%
				            }
				        } 
				    }
				%>
		<% } %>
		</div>
		<div class="col-1"></div>
	</div>

</body>
</html>
