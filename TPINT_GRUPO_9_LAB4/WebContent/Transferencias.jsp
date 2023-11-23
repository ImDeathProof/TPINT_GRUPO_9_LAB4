<%@page import="entidad.Cliente"%>
<%@page import="entidad.Cuenta"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:include page="Header.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Trasferencias</title>
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
					<h1>Trasferir</h1>
					 <% if (session.getAttribute("error") != null) { %>
				        <div class="alert alert-danger">
				             <%= (String)session.getAttribute("error")%> 
				        </div>
				    	<% } %>	
					 <form action="ServletTransferencias" method="post">
					           <input type="hidden" name="userID" value="<%= cliente.get_IDCliente() %>">
					           <div class="form-group">
					               <label for="nombre">Cuenta desde la que transferir:</label>
						           <!-- <select name="tipoCuenta" class="form-control">
					                    <option value="ahorros">Caja de ahorro</option>
					                    <option value="corriente">Cuenta corriente</option>
					                </select>
					                -->
									<select name="SelectCuentas" class="form-control p-2">
										<option value="Seleccionar">Seleccionar</option>
					                <%if(request.getAttribute("listaCuentas")!= null){
										ArrayList<Cuenta> listaCuentas = null;
						  				listaCuentas =(ArrayList<Cuenta>) request.getAttribute("listaCuentas"); %>
												<%
												if(listaCuentas != null && !listaCuentas.isEmpty()){
												for(Cuenta ct : listaCuentas){%>
													<option value="<%=ct.getIdCuenta()%>"><%= ct.getNumeroCuenta() %></option>
												<%}} %>
									<%}%>
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
					            <%if(request.getAttribute("listaCuentas")== null){%>
				           			<input type="submit" class="btn btn-primary mt-2" name="btnTransferir" value="Transferir" disabled>
				           			<hr>
				            		<div class="alert alert-danger">Usted no posee cuentas activas, solicitela o intente de nuevo mas tarde</div>
				            	<%}else{%>
				            		<input type="submit" class="btn btn-primary mt-2" name="btnTransferir" value="Transferir">
				            	<%} %>
				      </form>
				            <% if (session.getAttribute("errorTransfer") != null) { %>
				            <hr>
					        <div class="alert alert-danger">
					             <%= (String)session.getAttribute("errorTransfer")%> 
					        </div>
					    	<% }else if(session.getAttribute("successTransfer")!=null){%>
					    		<hr>
						        <div class="alert alert-success">
						             <%= (String)session.getAttribute("successTransfer")%> 
						        </div>
								<%
								    String base64Image = (String) request.getSession().getAttribute("base64Image");
								    if (base64Image != null && !base64Image.isEmpty()) {
								%>
								        <!-- Muestra el comprobante -->
								        <img src="data:image/png;base64, <%=base64Image%>" alt="Comprobante de pago">
								<%
								    }
								%>
						        
						     <%} %>
				</div>
				<div class="col-1"></div>
	</div>
	
	
	
	<% } %>

	<script>
	function abrirNuevaPestana(url) {
	    window.open(url, '_blank');
	}
	</script>

</body>
</html>