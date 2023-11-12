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
		response.sendRedirect("Inicio.jsp");
	} else { 
		Cliente cliente = (Cliente) session.getAttribute("usuarioAutenticado"); 	
		if(cliente.is_Admin()){%>
			<jsp:include page="NavbarAdmin.jsp"/>
		<% }else{ %>
			<jsp:include page="NavbarClientes.jsp" />
		<% } %>
	<!-- DEMAS CONTENIDO DE LA PAGINA-->
	<h1>Ver transferencias realizadas</h1>
	 <% if (session.getAttribute("errorTransfer") != null) { %>
        <div class="alert alert-danger">
             <%= (String)session.getAttribute("errorTransfer")%> 
        </div>
    	<% } %>	
	 <form action="ServletTransferencias" method="post">
	           <input type="hidden" name="userID" value="<%= cliente.get_IDCliente() %>">
	           <div class="form-group">
	               <label for="nombre">Cuenta desde la que transferir:</label>
		           <select name="tipoCuenta" class="form-control">
	                    <option value="ahorros">Caja de ahorro</option>
	                    <option value="corriente">Cuenta corriente</option>
	                </select>
                </div>
                <div class="form-group">
	                <label for="nombre">CBU:</label>
	                <input type="text" name="CBU" class="form-control" required oninput="this.value = this.value.replace(/[^0-9]/g, '')">
	            </div>
	             <div class="form-group">
	                <label for="nombre">MONTO:</label>
	                <input type="text" name="monto" class="form-control" required oninput="this.value = this.value.replace(/[^0-9]/g, '')">
	            </div>
           <button type="submit" class="btn btn-primary mt-2" name="btnFiltrar">Transferir</button>
            <% if (session.getAttribute("errorTransfer") != null) { %>
	        <div class="alert alert-danger">
	             <%= (String)session.getAttribute("errorTransfer")%> 
	        </div>
	    	<% } %>	
       </form>
	
	
	
	
	<% } %>

</body>
</html>