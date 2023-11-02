<%@page import="Dominio.Cliente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:include page="Header.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BFRGP | Mi perfil</title>
</head>
<body>
	<jsp:include page="Navbar.jsp" />
	<!-- DEMAS CONTENIDO DE LA PAGINA -->
	<h1>Perfil del usuario</h1>
	<form action="ServletPerfil" method="post">
	<div class="row">
		<div class="col-1">
		</div>
			<div class="col-10">
				<div class="row">
					<div class="col-6 border p-2">
						<%HttpSession session1 = request.getSession();
						Cliente cliente = (Cliente) session1.getAttribute("usuarioAutenticado"); %>
						<label for="nombre" id="lbl_nombre">Nombre:</label>
						<input type="text" name="txtNombre" class="form-control" value="<%=cliente.get_Nombre()%>">
						<label for="apellido" id="lbl_apellido">Apellido:</label>
						<input type="text" name="txtApellido" class="form-control" value="<%=cliente.get_Apellido()%>">
						<label for="dni" id="lbl_DNI">DNI:</label>
						<input type="text" name="txtDni" class="form-control" value="<%=cliente.get_DNI()%>">
						<label for="cuil" id="lbl_CUIL">CUIL:</label>
						<input type="text" name="txtCuil" class="form-control" value="<%=cliente.get_CUIL()%>">
						<label for="sexo" id="lbl_sexo">Sexo:</label>
						<%String sexo;
						if(cliente.is_Sexo()){
							sexo = "Masculino";}
							else{
								sexo="Femenino";
							}%>
						}
						<input type="text" name="txtSexo" class="form-control" value="<%=sexo%>">
						<label for="nacionalidad" id="lbl_nacionalidad">Nacionalidad:</label>
						<input type="text" name="txtNacionalidad" class="form-control" value="<%=cliente.get_Nacionalidad()%>">
						<label for="direccion" id="lbl_direccion">Dirección:</label>
						<input type="text" name="txtDireccion" class="form-control" value="<%=cliente.get_Direccion()%>">
					</div>
					<div class="col-6 border p-2">
						<label for="localidad" id="lbl_localidad">Localidad:</label>
						<input type="text" name="txtLocalidad" class="form-control" value="<%=cliente.get_Localidad()%>">
						<label for="provincia" id="lbl_provincia">Provincia:</label>
						<input type="text" name="txtProvincia" class="form-control" value="<%=cliente.get_Provincia()%>">
						<%--<label for="provincia" id="lbl_FNacimiento">Fecha de Nacimiento:</label>
						<input type="text" name="txtFNacimiento" class="form-control" value="<%=cliente.get_FechaNacimiento()%>">--%>
						<label for="email" id="lbl_email">E-mail:</label>
						<input type="text" name="txtEmail" class="form-control" value="<%=cliente.get_Email()%>">
						<label for="telefono" id="lbl_telefono">Telefono:</label>
						<input type="text" name="txtTelefono" class="form-control" value="<%=cliente.get_Telefono()%>">
						<label for="user" id="lbl_usuario">Nombre de usuario:</label>
						<input type="text" name="txtUsuario" class="form-control" value="<%=cliente.get_Usuario()%>">
						<label for="password" id="lbl_contraseña">Contraseña:</label>
						<input type="text" name="txtContaseña" class="form-control" value="<%=cliente.get_Contrasena()%>">
						<br>
						<input type="submit" name="btnModificar" value="Modificar datos" class="btn btn-warning">
					</div>
				</div>
			</div>
			<div class="col-1">
			</div>
	</div>
	</form>
	
</body>
</html>