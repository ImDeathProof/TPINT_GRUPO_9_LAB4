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
<title>Insert title here</title>
</head>
<body>
	<% if (session.getAttribute("usuarioAutenticado") != null) { 
		Cliente cliente = (Cliente) session.getAttribute("usuarioAutenticado");
		if(cliente.is_Admin()){%>
			<jsp:include page="NavbarAdmin.jsp"/>
		<% }else{ %>
			<jsp:include page="NavbarClientes.jsp" />

		<% }%>
	<!-- DEMAS CONTENIDO DE LA PAGINA-->
	<div class="row">
		<div class="col-1"></div>
		<div class="col-10">
			<h1>Préstamos</h1>
			<div class="row">
				<form action="ServletPrestamos" method="post">
					<div class="col-12">
						<label for="text" id="lbl_monto">Monto solicitado:</label>
						<div class="input-group p-2">
						    <div class="input-group-text" id="btnGroupAddon">$</div>
						    <input type="text" name="txtMonto" class="form-control" required placeholder="0" aria-label="Input group example" aria-describedby="btnGroupAddon">
						</div>
						<label for="text" id="lbl_cuotas">Cantidad de cuotas:</label>
						<div class="row">
							<div class="p-2">
								<div class="form-check form-check-inline col-1">
								  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="1Cuota" value="1cuota" required>
								  <label class="form-check-label" for="1cuota">1 cuota</label>
								</div>
								<div class="form-check form-check-inline col-1">
								  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="3Cuotas" value="3Cuotas">
								  <label class="form-check-label" for="3Cuotas">3 cuotas</label>
								</div>
								<div class="form-check form-check-inline col-1">
								  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="6Cuotas" value="6Cuotas">
								  <label class="form-check-label" for="6Cuotas">6 cuotas</label>
								</div>
								<div class="form-check form-check-inline col-1">
								  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="12Cuotas" value="12Cuotas">
								  <label class="form-check-label" for="12Cuotas">12 cuotas</label>
								</div>
								<div class="form-check form-check-inline col-1">
								  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="24Cuotas" value="24Cuotas">
								  <label class="form-check-label" for="24Cuotas">24 cuotas</label>
								</div>
								<div class="form-check form-check-inline col-1">
								  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="36Cuotas" value="36Cuotas">
								  <label class="form-check-label" for="36Cuotas">36 cuotas</label>
								</div>
							</div>
						</div>
						<label for="text" id="lbl_monto">Cuenta:</label>
						<%if(request.getAttribute("listaCuentas")!= null){
								ArrayList<Cuenta> listaCuentas = null;
				  				listaCuentas =(ArrayList<Cuenta>) request.getAttribute("listaCuentas"); %>
							<select name="SelectCuentas" class="form-control p-2">
								<option value="Seleccionar">Seleccionar</option>
									<%
									if(listaCuentas != null && !listaCuentas.isEmpty()){
									for(Cuenta ct : listaCuentas){%>
										<option value="<%=ct.getIdCuenta()%>"><%= ct.getNumeroCuenta() %></option>
									<%}} %>
							</select>
						<%}else if (session.getAttribute("errorCuenta") != null){%>
						<div class="alert alert-danger">
							<%= (String)session.getAttribute("errorCuenta")%>
						</div>
						<%} %>
						<input type="submit" name="btnSolicitar" value="Solicitar Préstamo" class="btn btn-success m-2">
						<% if (session.getAttribute("errorAlSolicitar") != null) { %>
							<div class="alert alert-danger">
							<%= (String)session.getAttribute("errorAlSolicitar")%> 
							</div>
						<% } else if (session.getAttribute("PrestamoExitoso") != null)
						{%>
							<div class="alert alert-success">
							<%= (String)session.getAttribute("PrestamoExitoso")%> 
							</div>
						<%} %>
					</div>
				</form>
			</div>
		</div>
		<div class="col-1"></div>
	</div>
	
	<!-- FINAL DEL CONTENIDO DE LA PAGINA-->
	<% }else{ 
		response.sendRedirect("Inicio.jsp");}  %>
</body>
</html>