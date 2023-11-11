<%@page import="entidad.Cliente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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

	<div class="container">
        <h2>Solicitá tu cuenta</h2>
        <form action="ServletRegistroCuenta" method="post">
            <div class="row align-items-start">
	        <div class="col">
	        </div>
	        <div>
	             <div class="form-group">
				    <label>Tipo de Cuenta:</label>
				    <div class="form-check">
				        <input type="radio" id="CajaAhorro" name="tipoCuenta" class="form-check-input" value="Ahorros" checked> 
				        <label for="CajaAhorro" class="form-check-label">Caja de ahorro</label>
				    </div>
				    <div class="form-check">
				        <input type="radio" id="Corriente" name="tipoCuenta" class="form-check-input" value="Corriente">
				        <label for="Corriente" class="form-check-label">Cuenta corriente</label>
				    </div>
				</div>             
	        </div>
            </div>
            <br>
            <button type="submit" class="btn btn-success">Pedir Cuenta</button>
            <button type="button" class="btn btn-danger">Cancelar</button>
           <% if (session.getAttribute("errorRegistroCuenta") != null) { %>
			<div class="alert alert-danger">
			    <%= (String)session.getAttribute("errorRegistroCuenta")%> 
			</div>
			<% session.removeAttribute("errorRegistroCuenta"); %>
			<% } %>
			<% if (session.getAttribute("noErrorRegistroCuenta") != null) { %>
			<div class="alert alert-success">
			    <%= (String)session.getAttribute("noErrorRegistroCuenta")%> 
			</div>
			<% session.removeAttribute("noErrorRegistroCuenta"); %>
			<% } %>
        </form>
    </div>
<% } %>
</body>
</html>