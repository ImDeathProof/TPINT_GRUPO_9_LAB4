<%@page import="Dominio.Cliente"%>
<%@page import="Dominio.Cuenta"%>
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
	<jsp:include page="NavbarClientes.jsp" />
	<!-- DEMAS CONTENIDO DE LA PAGINA -->
	<!-- NOTA: AGREGAR BUSQUEDA PARA LOS LISTADOS - AGREGAR FUNCIONALIDADES A LOS BOTONES -->
	<!-- SI EL USUARIO ESTA LOGUEADO Y ES ADMIN: ENTRA AL PANEL, SINO: REDIRIGE AL INICIO -->
	<%if(request.getSession().getAttribute("usuarioAutenticado")!=null){
		Cliente usuario = (Cliente) request.getSession().getAttribute("usuarioAutenticado");
		if(usuario.is_Admin()==true){
		%>
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
				      			listaUsuarios = (ArrayList<Cliente>) request.getAttribute("listaUsuarios");
						      		}%>
						    <table class="table">
						        <thead>
						        	<tr>
							            <th scope="col">ID</th>
							            <th scope="col">Usuario</th>
									    <th scope="col">Contrase�a</th>
									    <th scope="col">DNI</th>
									    <th scope="col">Bloquear</th>
								    </tr>
								 </thead>    
								 <tbody>
								    <%if(listaUsuarios != null){
								    	for(Cliente user : listaUsuarios){
								     %>
								     <tr>
									 	<td><%=user.get_IDCliente() %></td>
									    <td><%=user.get_Usuario() %></td>
									    <td><%=user.get_Contrasena() %></td>
									    <td><%=user.get_DNI() %></td>
									    <td>
								           <form action="ServletBloquearUser" method="post">
								               <input type="hidden" name="userID" value="<%= user.get_IDCliente() %>">
								               <% if (user.isBloqueado()) { %>
								                  <input type="submit" name="submitValue" value="Desbloquear" class="btn btn-success">
								               <% } else { %>
								                   <input type="submit" name="submitValue" value="Bloquear" class="btn btn-success">
								               <% } %>
								           </form>
								 �������</td>
									     
								     </tr>
								     <%}}%>
								</tbody>
							</table>
					</div>
					<div class="border-top p-2">
						<form method="post" action="ServletGestionCuentas">
							<input class="btn btn-success m-2" type="submit" name="btnCuentas" value="Cuentas">
					 	</form>
				     		<%
				     		ArrayList<Cuenta> listaCuentas = null;
				      		if(request.getAttribute("listaCuentas")!=null){
				      			listaCuentas = (ArrayList<Cuenta>) request.getAttribute("listaCuentas");
						      		}%>
						    <table class="table">
						        <thead>
						        	<tr>
							            <th scope="col">1</th>
							            <th scope="col">2</th>
									    <th scope="col">3</th>
									    <th scope="col">4</th>
									    <th scope="col">5</th>
								    </tr>
								 </thead>    
								 <tbody>
							    <%if(listaCuentas != null){
							    	for(Cuenta cuenta : listaCuentas){
							     %>
							     <tr>
									 	<td>1</td>
									    <td>2</td>
									    <td>3</td>
									    <td>4</td>
									    <td><input type="submit" value="Bloquear" class="btn btn-success"></td><!-- HAY QUE AGREGAR LA FUNCIONALIDAD -->
								     </tr>
								     <%}}%>
								</tbody>
							</table>
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