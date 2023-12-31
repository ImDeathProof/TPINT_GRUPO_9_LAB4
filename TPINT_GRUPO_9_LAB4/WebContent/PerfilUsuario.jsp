<%@page import="entidad.Cliente"%>
<%@page import="entidad.Localidad"%>
<%@page import="entidad.Provincia"%>
<%@page import="entidad.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="negocio.DireccionNeg"%>
<%@page import="negocioImpl.DireccionNegImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:include page="Header.jsp" />
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FRGP BANK - Mi perfil</title>
</head>
<body>
	<% if (session.getAttribute("usuarioAutenticado") == null) { 
		response.sendRedirect("Inicio.jsp");
	} else { 
		
		DireccionNeg dNeg = new DireccionNegImpl();
		Cliente cliente = new Cliente(); 
		
		if(session.getAttribute("usuarioAModificar") != null)
		{
			cliente = (Cliente) session.getAttribute("usuarioAModificar");
		}
		else
		{
			cliente =(Cliente) session.getAttribute("usuarioAutenticado");
		}
		
		if(cliente.is_Admin()){%>
			<jsp:include page="NavbarAdmin.jsp"/>
		<% }else{ %>
			<jsp:include page="NavbarClientes.jsp" />
		<% } %>
	<!-- DEMAS CONTENIDO DE LA PAGINA -->
	<form action="ServletPerfil" method="post">
	<div class="row min-vh-100">
		<div class="col-1">
		</div>
			<div class="col-10">
			<h1>Perfil del usuario</h1>
				<div class="row">
					<div class="col-6 border p-2">				
						<label for="nombre" id="lbl_nombre">Nombre:</label>
						<input type="text" id="nombre" name="txtNombre" class="form-control" value="<%=cliente.get_Nombre()%>" required oninput="this.value = this.value.replace(/[^a-zA-Z]/g, '')" <% if (!cliente.is_Admin()) { %>readonly<% } %>>
						<label for="apellido" id="lbl_apellido">Apellido:</label>
						<input type="text" id="apellido" name="txtApellido" class="form-control" value="<%=cliente.get_Apellido()%>" required oninput="this.value = this.value.replace(/[^a-zA-Z]/g, '')" <% if (!cliente.is_Admin()) { %>readonly<% } %>>
						<label for="dni" id="lbl_DNI">DNI:</label>
						<input type="text" id="DNI" name="txtDni" class="form-control" value="<%=cliente.get_DNI()%>" required oninput="this.value = this.value.replace(/[^0-9]/g, '')" <% if (!cliente.is_Admin()) { %>readonly<% } %>>
						<label for="cuil" id="lbl_CUIL">CUIL:</label>
						<input type="text" id="CUIL" name="txtCuil" class="form-control" value="<%=cliente.get_CUIL()%>" required oninput="this.value = this.value.replace(/[^0-9]/g, '')" <% if (!cliente.is_Admin()) { %>readonly<% } %>>
						
						<div class="form-group">
						    <label>Sexo:</label>
						    <div class="form-check">
						        <input type="radio" id="masculino" name="sexo" class="form-check-input" value="Masculino" <% if (cliente.is_Sexo()) { %>checked<% } %> <% if (!cliente.is_Admin()) { %>disabled<% } %>>
						        <label for="masculino" class="form-check-label">Masculino</label>
						    </div>
						    <div class="form-check">
						        <input type="radio" id="femenino" name="sexo" class="form-check-input" value="Femenino" <% if (!cliente.is_Sexo()) { %>checked<% } %> <% if (!cliente.is_Admin()) { %>disabled<% } %>>
						        <label for="femenino" class="form-check-label">Femenino</label>
						    </div>
						</div>
						<label for="nacionalidad" id="lbl_nacionalidad">Nacionalidad:</label>
						<input type="text" id="Nacionalidad" name="txtNacionalidad" class="form-control" value="<%=cliente.get_Nacionalidad()%>" required oninput="this.value = this.value.replace(/[^a-zA-Z]/g, '')" <% if (!cliente.is_Admin()) { %>readonly<% } %>>									
					 <label for="direccion" id="lbl_direccion">Calle:</label>
						<input type="text" name="txtDireccion" class="form-control" value="<%=cliente.get_Direccion().getCalle()%>" required <% if (!cliente.is_Admin()) { %>readonly<% } %>>
						<label for="numDic" id="lbl_numDic">Numero:</label>
						<input type="text" name="txtNum" class="form-control" value="<%=cliente.get_Direccion().getNumero()%>" required <% if (!cliente.is_Admin()) { %>readonly<% } %>>
					</div>
					<div class="col-6 border p-2">	
										
				      
			            <label for="provincia">Provincia:</label>
							    <select name="Provincia" id="provinciaSelect" <% if (!cliente.is_Admin()) { %>disabled<% } %>>
							    <% Provincia pr = (Provincia)session.getAttribute("provincia"); %>
							    <option value="<%=pr.getIdProvincia()%>"><%=pr.getDescripcion()%></option>
							      <% for (Provincia prov : dNeg.getAllProvincias()) { %>									
									    <option value="<%= prov.getIdProvincia()%>"><%= prov.getDescripcion()%></option>
									<%} %>
							    </select><br>
						<% if (cliente.is_Admin()) { %>						
						<input type="submit" id="btnLocalidades" name="btnLocalidades" value="Obtener Localidades" class="btn btn-warning">	 
						<%}%>
							    				
					    <label for="localidad">Localidad:</label>
						    <select name="Localidad" id="localidad" <% if (!cliente.is_Admin()) { %>disabled<% } %>>
						    <% Localidad loc = (Localidad)session.getAttribute("lcCliente"); %>
							    <option value="<%=loc.getIdLocalidad()%>"><%=loc.getDescripcion()%></option>								
									 <% if (request.getAttribute("localidades") !=null)
									 {
									 for (Localidad lc : (ArrayList<Localidad>)request.getAttribute("localidades")) { %>									
									    <option value="<%= lc.getIdLocalidad()%>"><%= lc.getDescripcion()%></option>
									<%}
									 }%>
						  </select><br>	
								
						<label for="Nacimiento" id="lbl_FNacimiento">Fecha de Nacimiento:</label>
						<input type="date" id="fechaNacimiento" name="txtFNacimiento" class="form-control" value="<%=cliente.get_FechaNacimiento()%>" required <% if (!cliente.is_Admin()) { %>readonly<% } %>>
						<label for="email" id="lbl_email">E-mail:</label>
						<input type="text" name="txtEmail" class="form-control" value="<%=cliente.get_Email()%>" required <% if (!cliente.is_Admin()) { %>readonly<% } %>>
						<label for="telefono" id="lbl_telefono">Telefono:</label>
						<input type="text" id="Telefono" name="txtTelefono" class="form-control" value="<%=cliente.get_Telefono()%>" required oninput="this.value = this.value.replace(/[^0-9]/g, '')" <% if (!cliente.is_Admin()) { %>readonly<% } %>>
						<label for="user" id="lbl_usuario">Nombre de usuario:</label>
						<input type="text" name="txtUsuario" class="form-control" value="<%=cliente.get_Usuario()%>" required <% if (!cliente.is_Admin()) { %>readonly<% } %>>
						<label for="password" id="lbl_contrase�a">Contrase�a:</label>
						<input type="password" name="txtContase�a" class="form-control" value="<%=cliente.get_Contrasena()%>" required <% if (!cliente.is_Admin()) { %>readonly<% } %>>
						<br>
						<% if (cliente.is_Admin()) { %>
						<input type="submit" name="btnModificar" value="Modificar datos" class="btn btn-warning">							
						<%}%>
					</div>
				</div>
			</div>
			<div class="col-1">
			</div>
	</div>
	 <% if (session.getAttribute("errorModificarUser") != null) { %>
	         <div id="errorAlert" class="alert alert-danger">
	              <%= (String)session.getAttribute("errorModificarUser")%> 
	         </div>
	     	<% } else if (session.getAttribute("usuarioModificado") != null)
	     		{%>
	     	 <div id="successAlert"  class="alert alert-success">
	              <%= (String)session.getAttribute("usuarioModificado")%> 
	         </div>
	     		<%} %>
	</form>
	<jsp:include page="Footer.jsp"/>
	<% } %>
	
</body>

<script>
    // Ocultar el mensaje de error despu�s de 5000 milisegundos (5 segundos)
    setTimeout(function() {
        var errorAlert = document.getElementById('errorAlert');
        if (errorAlert) {
            errorAlert.style.display = 'none';
        }
    }, 5000);

    // Ocultar el mensaje de �xito despu�s de 5000 milisegundos (5 segundos)
    setTimeout(function() {
        var successAlert = document.getElementById('successAlert');
        if (successAlert) {
            successAlert.style.display = 'none';
        }
    }, 5000);
</script>

</html>