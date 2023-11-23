<%@page import="entidad.Localidad"%>
<%@page import="entidad.Provincia"%>
<%@page import="entidad.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    
    <jsp:include page="Header.jsp" />
<%
	ArrayList<Localidad> localidades = (ArrayList<Localidad>) request.getAttribute("localidades");
	ArrayList<Provincia> provincias = (ArrayList<Provincia>) request.getAttribute("provincias");

%>

<%
    System.out.println("Localidades: " + localidades);
    System.out.println("Provincias: " + provincias);
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registro</title>
</head>
<body>



	<% if (session.getAttribute("usuarioAutenticado") != null) { 
		Cliente cl = (Cliente)session.getAttribute("usuarioAutenticado");
		if(cl.is_Admin()){%>
		<jsp:include page="NavbarAdmin.jsp" />
		<!-- DEMAS CONTENIDO DE LA PAGINA -->
		<div class="container">
	        <h2>Alta de Usuario</h2>
	        <form action="ServletRegistro" method="post">
	            <div class="row align-items-start">
		        <div class="col">
		        </div>
		        <div class="col-5">
		            <div class="form-group">
		                <label for="nombre">Nombre:</label>
		                <input type="text" id="nombre" name="nombre" class="form-control" required oninput="this.value = this.value.replace(/[^a-zA-Z]/g, '')">
		            </div>
		             <div class="form-group">
		                <label for="Apellido">Apellido:</label>
		                 <input type="text" id="Apellido" name="apellido" class="form-control" required oninput="this.value = this.value.replace(/[^a-zA-Z]/g, '')">
		            </div>
		             <div class="form-group">
		                <label for="DNI">DNI:</label>
		               <input type="text" id="DNI" name="DNI" class="form-control" required oninput="this.value = this.value.replace(/[^0-9]/g, '')">
		            </div>
		             <div class="form-group">
		                <label for="CUIL">CUIL:</label>
		                <input type="text" id="CUIL" name="CUIL" class="form-control" required oninput="this.value = this.value.replace(/[^0-9]/g, '')">
		            </div>
		            <div class="form-group">
					    <label>Sexo:</label>
					    <div class="form-check">
					        <input type="radio" id="masculino" name="sexo" class="form-check-input" value="Masculino" checked> 
					        <label for="masculino" class="form-check-label">Masculino</label>
					    </div>
					    <div class="form-check">
					        <input type="radio" id="femenino" name="sexo" class="form-check-input" value="Femenino">
					        <label for="femenino" class="form-check-label">Femenino</label>
					    </div>
					</div>
		            <div class="form-group">
		                <label for="Nacionalidad">Nacionalidad:</label>
		                <input type="text" id="Nacionalidad" name="Nacionalidad" class="form-control" required oninput="this.value = this.value.replace(/[^a-zA-Z]/g, '')">            
		            </div>
		            <div class="form-group">
		                <label for="Direccion">Calle:</label>
		                <input type="text" id="Direccion" name="Direccion" class="form-control" required oninput="this.value = this.value.replace(/[^a-zA-Z]/g, '')">
		            </div>
		            <div class="form-group">
		                <label for="Direccion">Número:</label>
		                <input type="text" id="numeroDic" name="numeroDic" class="form-control" required oninput="this.value = this.value.replace(/[^0-9]/g, '')">
		            </div>
					<br>
					<div class="form-group">
					    <label for="provincia">Provincia:</label>
					    <select name="Provincia" id="provincia">
					        <option value="">Seleccione una opción</option> 
					        <c:forEach var="provincia" items="${provincias}">
					            <option value="${provincia.descripcion}">${provincia.descripcion}</option>
					        </c:forEach>
					    </select>
					</div>
		            <br>
					<div class="form-group">
					    <label for="localidad">Localidad:</label>
					    <select name="Localidad" id="localidad">
					        <option value="">Seleccione una opción</option> 
					        <c:forEach var="localidad" items="${localidades}">
					            <option value="${localidad.descripcion}">${localidad.descripcion}</option>
					        </c:forEach>
					    </select>
					</div>
					
                        
		        </div>
	            <div class="col-5">
	           	     <div class="form-group">
					    <label for="fechaNacimiento">Fecha de Nacimiento:</label>
					    <input type="date" id="fechaNacimiento" name="fechaNacimiento" class="form-control" required>
					</div>
		            <div class="form-group">
		                <label for="email">Email:</label>
		                <input type="email" id="email" name="email" class="form-control" required>
		            </div>
		            <div class="form-group">
		                <label for="Telefono1">Teléfono:</label>
		                <input type="text" id="Telefono1" name="Telefono" class="form-control" required oninput="this.value = this.value.replace(/[^0-9]/g, '')">
		            </div>
		            <div class="form-group">
		                <label for="Username">Usuario:</label>
		                <input type="text" id="Username" name="username" class="form-control" required>
		            </div>
		            <div class="form-group">
		                <label for="password">Contraseña:</label>
		                <input type="password" id="password" name="password" class="form-control" required>
		            </div>
		            <div class="form-group">
		                <label for="password">Repetir contraseña:</label>
		                <input type="password" id="password" name="pass2" class="form-control" required>
		            </div>
		        </div>
			    <div class="col">
			    </div>
	            </div>
	            <br>
	            <button type="submit" class="btn btn-success">Registrar</button>
	            <button type="button" class="btn btn-danger">Cancelar</button>
	             <% if (session.getAttribute("errorRegistro") != null) { %>
		         <div class="alert alert-danger">
		              <%= (String)session.getAttribute("errorRegistro")%> 
		         </div>
		     	<% } %>	
	        </form>
	    </div>
		<%}
	} else { 
	response.sendRedirect("Inicio.jsp");
	} %>	
</body>
</html>