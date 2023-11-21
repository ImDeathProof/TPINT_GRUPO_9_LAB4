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
	<!-- DEMAS CONTENIDO DE LA PAGINA -->
	<!-- NOTA: AGREGAR BUSQUEDA PARA LOS LISTADOS - AGREGAR FUNCIONALIDADES A LOS BOTONES -->
	<!-- SI EL USUARIO ESTA LOGUEADO Y ES ADMIN: ENTRA AL PANEL, SINO: REDIRIGE AL INICIO -->
	<%if(request.getSession().getAttribute("usuarioAutenticado")!=null){
		Cliente usuario = (Cliente) request.getSession().getAttribute("usuarioAutenticado");
		if(usuario.is_Admin()==true){
		%>
			<jsp:include page="NavbarAdmin.jsp"/>
			<div class="row">
				<div class="col-1"></div>
				<div class="col-10">
					<h1>Panel de Control</h1>
					<div class="border-top p-2">
						<form method="post" action="ServletGestionUsuarios">
							<input class="btn btn-success m-2" type="submit" name="btnUsuarios" value="Usuarios">
					 	</form>
						 <%
				     		ArrayList<Cliente> listaUsuarios = null;
				      		if(request.getAttribute("listaUsuarios")!=null){
				      			listaUsuarios = (ArrayList<Cliente>) request.getAttribute("listaUsuarios");%>
				      			
						   <table class="table">
							    <thead>
							        <tr>
							            <th scope="col">ID</th>
							            <th scope="col">Usuario</th>
							            <th scope="col">Contraseña</th>
							            <th scope="col">DNI</th>
							            <th scope="col">Bloquear</th>
							        </tr>
							    </thead>
							    <tbody>
							        <% if (listaUsuarios != null) {
							            for (Cliente user : listaUsuarios) { %>
							            <tr>
							                <td><%= user.get_IDCliente() %></td>
							                <td><%= user.get_Usuario() %></td>
							                <td>
							                    <form action="ServletCambiarPass" method="post">
							                        <input type="hidden" name="userID" value="<%= user.get_IDCliente() %>">
							                        <table>
							                            <tr>
							                                <td>
							                                    <input type="text" name="txtContaseña" class="form-control" value="<%= user.get_Contrasena() %>" required>
							                                </td>
							                                <td>
							                                    <input type="submit" name="submitValue" value="Setear Cambios" class="btn btn-success">
							                                </td>
							                            </tr>
							                        </table>
							                    </form>
							                </td>
							                <td><%= user.get_DNI() %></td>
							                <td>
							                    <form action="ServletBloquearUser" method="post">
							                        <input type="hidden" name="userID" value="<%= user.get_IDCliente() %>">
							                        <% if (user.isBloqueado()) { %>
							                            <input type="submit" name="submitValue" value="Desbloquear" class="btn btn-success">
							                        <% } else { %>
							                            <input type="submit" name="submitValue" value="Bloquear" class="btn btn-success">
							                        <% } %>
							                    </form>
							                </td>
							            </tr>
							            <% } } %>
							    </tbody>
							</table>
							<form method="get" action="ServletGestionUsuarios">
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
							<%}%>
					</div>
					<div class="border-top p-2">
						<form method="post" action="ServletGestionCuentas">
							<input class="btn btn-success m-2" type="submit" name="btnCuentas" value="Cuentas">
					 	</form>
				     		<%
				     		ArrayList<Cuenta> listaCuentas = null;
				      		if(request.getAttribute("listaTodasCuentas")!=null){
				      			listaCuentas = (ArrayList<Cuenta>) request.getAttribute("listaTodasCuentas");%>
						    <table class="table">
							    <thead>
							        <tr>
							            <th scope="col">ID de Cuenta</th>
							            <th scope="col">Tipo de Cuenta</th>
							            <th scope="col">Número de Cuenta</th>
							            <th scope="col">CBU</th>
							            <th scope="col">Saldo</th>
							            <th scope="col">Fecha de Creación</th>
							            <th scope="col">Cliente</th>
							            <th scope="col">Estado</th>
							        </tr>
							    </thead>
							    <tbody>
							        <% if (listaCuentas != null) {
							            for (Cuenta cuenta : listaCuentas) { %>
							            <tr>
							                <td><%= cuenta.getIdCuenta() %></td>
							                <td><%= cuenta.getTipoCuenta() %></td>
							                <td><%= cuenta.getNumeroCuenta() %></td>
							                <td><%= cuenta.getCBU() %></td>
							                <td>
										<form action="ServletCambiarSaldo" method="post">
										    <input type="hidden" name="userID" value="<%= cuenta.getIdUsuario() %>">
										    <table>
										        <tr>
										            <td>
														<input type="text" name="txtSaldo" class="form-control" value="<%= cuenta.getSaldo() %>" required>

										            </td>
										            <td>
										                <select name="ddlTipoCuenta" class="form-control" style="margin-right: 6rem;">
										                    <option value="Ahorros" <%= cuenta.getTipoCuenta().equals("Ahorros") ? "selected" : "" %>>Ahorros</option>
										                    <option value="Corriente" <%= cuenta.getTipoCuenta().equals("Corriente") ? "selected" : "" %>>Corriente</option>
										                </select>
										            </td>
										            <td>
										                <input type="submit" name="btnSetear" value="Setear Cambios" class="btn btn-success mx-1">
										            </td>
										        </tr>
										    </table>
										</form>

							                </td>
							                <td><%= cuenta.getFechaCreacion() %></td>
							                <td><%= cuenta.getNombre() %></td>
							               <td>
								                <form action="ServletAprobarCuenta" method="post">
								                <input type="hidden" name="cuentaID" value="<%= cuenta.getIdCuenta() %>">
			 				                        <% if (cuenta.getEstado()) { %>
			 				                        	<input type="submit" name="submitValueEstado" value="Bloquear" class="btn btn-success">
							                        <% } else { %>
							                           <input type="submit" name="submitValueEstado" value="Validar" class="btn btn-success">
							                        <% } %>
								                </form>
										  </td>	
										  				               
							            </tr>
							            <% } } %>
							    </tbody>
							</table>
							 <form method="get" action="ServletGestionCuentas">
						    <%
						    int cantPags = (int)request.getAttribute("cantPagsCuentas");
						    if (cantPags != 0) {
						        for (int i = 1; i <= cantPags; i++) {
						    %>
						        <button class="btn btn-primary" type="submit" name="paginaCuenta" value="<%= i %>">
						            Página <%= i %>
						        </button>
						    <%
						        } 
						    }
						    %>
						</form>
							<%}%>
					</div>
				</div>
				<div class="col-1"></div>
			</div>
			<%
		
		}else{
			response.sendRedirect("Inicio.jsp");
		}} %>
</body>
</html>