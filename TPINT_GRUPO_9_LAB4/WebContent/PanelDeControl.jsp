<%@page import="entidad.Cliente"%>
<%@page import="entidad.Cuenta"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.util.UUID" %>
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
<jsp:include page="NavbarAdmin.jsp" />

<div class="row">
    <div class="col-1"></div>
    <div class="col-10">
        <h1>Panel de Control</h1>

		
					
			<div class="border-top p-2">
						<form method="post" action="ServletGestionUsuarios">
							<input class="btn btn-success m-2" type="submit" name="btnUsuarios" value="Usuarios">	
					 	</form>
					 	<%if(session.getAttribute("accion")!=null){ %>
					 		<div class="alert alert-success">
							    <%= (String)session.getAttribute("accion")%> 
							</div>
					 	<%} %>
					 	
				<form method="post" action="ServletGestionUsuarios">
				    <div class="input-group mb-3">
				        <input type="text" class="form-control" id="textBoxBusquedaUsuarios" name="textBoxBusquedaUsuarios" placeholder="Buscar Usuario">

				            <select class="form-select" id="filtroBusquedaUsuarios" name="filtroBusquedaUsuarios">
				                <option value="" selected>Seleccione un filtro</option>
				                <option value="IDUsuario">ID</option>
				                <option value="Username">Usuario</option>
				                <option value="Nombre">Nombre</option>
				                <option value="Apellido">Apellido</option>
				                <option value="DNI">DNI</option>
				                <option value="Mail">Mail</option>
				            </select>
<!-- 				            <a class="btn btn-primary" type="submit" href="ServletGestionUsuarios?Param=1">Buscar</a> -->
<!-- 								<button class="btn btn-primary" type="submit">Buscar</button> -->
							<button class="btn btn-primary" type="submit" name="btnBuscarUsuarios">Buscar</button>

				    </div>
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
							            int i = 0;
							        	for (Cliente user : listaUsuarios) { 
							        	%>
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
							                        <%if(user.get_IDCliente() != 1) {
								                        if (user.isBloqueado()) { 
							                        		String uniqueID = "modal" + UUID.randomUUID().toString().replace("-", "");
							                       		%>
							                            <!-- Button trigger modal + Aprobar prestamo-->
							                            <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#<%= uniqueID %>">
														  Desbloquear
														</button>
							                            
							                        	<!-- Modal aprobar prestamo-->
													    <div class="modal fade" id="<%= uniqueID %>" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
													        <div class="modal-dialog">
													        <div class="modal-content">
													            <div class="modal-header">
														            <h1 class="modal-title fs-5" id="exampleModalLabel">Desbloquear usuario</h1>
														            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
														            </div>
														            <div class="modal-body">
														            ¿Está seguro que desea desbloquear al usuario seleccionado?
														            </div>
														            <div class="modal-footer">
														            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
														            <form action="ServletBloquearUser" method="post">
												                        <input type="hidden" name="userID" value="<%= user.get_IDCliente() %>">
												                        <input type="submit" name="submitValue" value="Desbloquear" class="btn btn-success">
														            </form>
													            </div>
													        </div>
													        </div>
													    </div>
							                            
							                        <% } else { 
							                        	String uniqueID = "modal" + UUID.randomUUID().toString().replace("-", "");%>
							                            <!-- Button trigger modal + Aprobar prestamo-->
							                            <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#<%= uniqueID %>">
														  Bloquear
														</button>
							                            
							                        	<!-- Modal aprobar prestamo-->
													    <div class="modal fade" id="<%= uniqueID %>" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
													        <div class="modal-dialog">
													        <div class="modal-content">
													            <div class="modal-header">
														            <h1 class="modal-title fs-5" id="exampleModalLabel">Bloquear usuario</h1>
														            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
														            </div>
														            <div class="modal-body">
														            ¿Está seguro que desea bloquear al usuario seleccionado?
														            </div>
														            <div class="modal-footer">
														            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
														            <form action="ServletBloquearUser" method="post">
												                        <input type="hidden" name="userID" value="<%= user.get_IDCliente() %>">
												                        <input type="submit" name="submitValue" value="Bloquear" class="btn btn-danger">
														            </form>
													            </div>
													        </div>
													        </div>
													    </div>
							                        <% }} %>
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
					 	<%if(session.getAttribute("accionCuenta")!=null){ %>
					 		<div class="alert alert-success">
							    <%= (String)session.getAttribute("accionCuenta")%> 
							</div>
					 	<%} %>
					 	
					 	<form method="post" action="ServletGestionCuentas">
						    <div class="input-group mb-3">
						            <select class="form-select " id="filtroBusquedaCuenta" name="filtroBusquedaCuenta">
						                <option value="" selected>Seleccione un filtro</option>
						                <option value="IDCuenta">ID de Cuenta</option>
						                <option value="TipoCuenta">Tipo de cuenta</option>
						                <option value="Saldo">Saldo</option>
						                <option value="NumeroCuenta">Numero de cuenta</option>
						                <option value="CBU">CBU</option>
						            </select>
						            <select class="form-select" id="filtroBusquedaCuenta2" name="filtroBusquedaCuenta2">
						                <option value=">" selected>Mayor a</option>
						                <option value="<">Menor a</option>
						                <option value="=">Igual</option>
						            </select>
						        <input type="text" class="form-control" id="textBoxBusquedaCuentas" name="textBoxBusquedaCuentas" placeholder="Buscar Cuenta">
						        <button class="btn btn-primary" type="submit" name="btnBuscarCuenta">Buscar</button>
						    </div>
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
										    <input type="hidden" name="cuentaID" value="<%= cuenta.getIdCuenta()%>">
										    <table>
										        <tr>
										            <td>
														<input type="text" name="txtSaldo" class="form-control" value="<%= cuenta.getSaldo() %>" required>
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
								                
			 				                        <% if (cuenta.getEstado()) {
							                        	String uniqueID = "modal" + UUID.randomUUID().toString().replace("-", "");
							                       		%>
			 				                        	
							                            <!-- Button trigger modal + Aprobar prestamo-->
							                            <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#<%= uniqueID %>">
														  Bloquear
														</button>
							                            
							                        	<!-- Modal aprobar prestamo-->
													    <div class="modal fade" id="<%= uniqueID %>" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
													        <div class="modal-dialog">
													        <div class="modal-content">
													            <div class="modal-header">
														            <h1 class="modal-title fs-5" id="exampleModalLabel">Bloquear cuenta</h1>
														            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
														            </div>
														            <div class="modal-body">
														            ¿Está seguro que desea bloquear la cuenta seleccionada?
														            </div>
														            <div class="modal-footer">
														            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
														            <form action="ServletAprobarCuenta" method="post">
												                        <input type="hidden" name="cuentaID" value="<%= cuenta.getIdCuenta() %>">
												                        <input type="submit" name="submitValueEstado" value="Bloquear" class="btn btn-danger">
														            </form>
													            </div>
													        </div>
													        </div>
													    </div>
							                        <% } else { 
							                        	String uniqueID = "modal" + UUID.randomUUID().toString().replace("-", "");%>
							                            <!-- Button trigger modal + Aprobar prestamo-->
							                            <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#<%= uniqueID %>">
														  Validar
														</button>
							                            
							                        	<!-- Modal aprobar prestamo-->
													    <div class="modal fade" id="<%= uniqueID %>" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
													        <div class="modal-dialog">
													        <div class="modal-content">
													            <div class="modal-header">
														            <h1 class="modal-title fs-5" id="exampleModalLabel">Validar cuenta</h1>
														            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
														            </div>
														            <div class="modal-body">
														            ¿Está seguro que desea validar la cuenta seleccionada?
														            </div>
														            <div class="modal-footer">
														            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
														            <form action="ServletAprobarCuenta" method="post">
												                        <input type="hidden" name="cuentaID" value="<%= cuenta.getIdCuenta() %>">
												                        <input type="submit" name="submitValueEstado" value="Validar" class="btn btn-success">
														            </form>
													            </div>
													        </div>
													        </div>
													    </div>
							                        <% } %>
								                
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