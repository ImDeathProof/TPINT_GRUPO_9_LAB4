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
<title>FRGP Bank | Registro de cliente</title>
</head>
<body>



	<% if (session.getAttribute("usuarioAutenticado") != null) { 
		DireccionNeg dNeg = new DireccionNegImpl();
		Cliente reg = (Cliente)session.getAttribute("registro");
		Cliente cl = (Cliente)session.getAttribute("usuarioAutenticado");
		if(cl.is_Admin()){%>
		<jsp:include page="NavbarAdmin.jsp" />
		<!-- DEMAS CONTENIDO DE LA PAGINA -->
		<div class="container min-vh-100">
	        <h2>Alta de Usuario</h2>
	        <form action="ServletRegistro" method="post">
	            <div class="row align-items-start">
		        <div class="col">
		        </div>
		        <div class="col-5">
		            <div class="form-group">
		                <label for="nombre">Nombre:</label>
		                <input type="text" id="nombre" name="nombre" class="form-control" value="<%=reg.get_Nombre() %>" required oninput="this.value = this.value.replace(/[^a-zA-Z]/g, '')">
		            </div>
		             <div class="form-group">
		                <label for="Apellido">Apellido:</label>
		                 <input type="text" id="Apellido" name="apellido" class="form-control" value="<%=reg.get_Apellido() %>" required oninput="this.value = this.value.replace(/[^a-zA-Z]/g, '')">
		            </div>
		             <div class="form-group">
		                <label for="DNI">DNI:</label>
		               <input type="text" id="DNI" name="DNI" class="form-control" value="<%=reg.get_DNI()%>" required oninput="this.value = this.value.replace(/[^0-9]/g, '')">
		            </div>
		             <div class="form-group">
		                <label for="CUIL">CUIL:</label>
		                <input type="text" id="CUIL" name="CUIL" class="form-control" value="<%=reg.get_CUIL()%>" required oninput="this.value = this.value.replace(/[^0-9]/g, '')">
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
		                <input type="text" id="Nacionalidad" name="Nacionalidad" class="form-control" value="<%=reg.get_Nacionalidad()%>" required oninput="this.value = this.value.replace(/[^a-zA-Z]/g, '')">            
		            </div>
		            <div class="form-group">
		                <label for="Direccion">Calle:</label>
		                <input type="text" id="Direccion" name="Direccion" class="form-control" value="<%=reg.get_Direccion().getCalle()%>" required oninput="this.value = this.value.replace(/[^a-zA-Z]/g/s, '')">
		            </div>
		            <div class="form-group">
		                <label for="Direccion">Número:</label>
		                <input type="text" id="numeroDic" name="numeroDic" class="form-control" value="<%=reg.get_Direccion().getNumero()%>" required oninput="this.value = this.value.replace(/[^0-9]/g, '')">
		            </div>
					<br>
					<div class="form-group">
					  <label for="provincia">Provincia:</label>
							    <select name="Provincia" id="provinciaSelect">
							     <% Provincia pr = (Provincia)session.getAttribute("provincia"); %>
							    <option value="<%=pr.getIdProvincia()%>"><%=pr.getDescripcion()%></option>	
							      <% for (Provincia prov : dNeg.getAllProvincias()) { %>									
									    <option value="<%= prov.getIdProvincia()%>"><%= prov.getDescripcion()%></option>
									<%} %>
							    </select><br>
						<input type="submit" id="btnLocalidades" name="btnLocalidades" value="Obtener Localidades" class="btn btn-warning">	
					</div>
		            <br>
					<div class="form-group">
					        <label for="localidad">Localidad:</label>
						    <select name="Localidad" id="localidad">
						    <% Localidad loc = (Localidad)session.getAttribute("lcCliente"); %>
							    <option value="<%=loc.getIdLocalidad()%>"><%=loc.getDescripcion()%></option>								
									 <% if (request.getAttribute("localidades") !=null)
									 {
									 for (Localidad lc : (ArrayList<Localidad>)request.getAttribute("localidades")) { %>									
									    <option value="<%= lc.getIdLocalidad()%>"><%= lc.getDescripcion()%></option>
									<%}
									 }%>
						  </select><br>	
					</div>
					
                        
		        </div>
	            <div class="col-5">
	           	     <div class="form-group">
					    <label for="fechaNacimiento">Fecha de Nacimiento:</label>
					    <input type="date" id="fechaNacimiento" name="fechaNacimiento" value="<%=reg.get_FechaNacimiento()%>" class="form-control" required>
					</div>
		            <div class="form-group">
		                <label for="email">Email:</label>
		                <input type="email" id="email" name="email" class="form-control" value="<%=reg.get_Email()%>" required>
		            </div>
		            <div class="form-group">
		                <label for="Telefono1">Teléfono:</label>
		                <input type="text" id="Telefono1" name="Telefono" class="form-control" value="<%=reg.get_Telefono()%>" required oninput="this.value = this.value.replace(/[^0-9]/g, '')">
		            </div>
		            <div class="form-group">
		                <label for="Username">Usuario:</label>
		                <input type="text" id="Username" name="username" class="form-control" value="<%=reg.get_Usuario()%>" required>
		            </div>
		            <div class="form-group">
		                <label for="password">Contraseña:</label>
		                <input type="password" id="password" name="password" class="form-control" value="<%=reg.get_Contrasena()%>" required>
		            </div>
		            <div class="form-group">
		                <label for="password">Repetir contraseña:</label>
		                <input type="password" id="password" name="pass2" class="form-control" value="<%=reg.get_Contrasena()%>" required>
		            </div>
		        </div>
			    <div class="col">
			    </div>
	            </div>
	            <br>
	            <button type="submit" name="btnRegistrar" class="btn btn-success">Registrar</button>
	            <button type="button" class="btn btn-danger">Cancelar</button>
	             <% if (session.getAttribute("errorRegistro") != null) { %>
		         <div id="errorAlert" class="alert alert-danger">
		              <%= (String)session.getAttribute("errorRegistro")%> 
		         </div>
		     	<% } else if(session.getAttribute("seRegistro") != null){%>	
		     	  <div id="successAlert" class="alert alert-success">
		              <%= (String)session.getAttribute("seRegistro")%> 
		         </div>
		     	<% }%>
	        </form>
	    </div>
	    <jsp:include page="Footer.jsp"/>
		<%}
	} else { 
	response.sendRedirect("Inicio.jsp");
	} %>	
</body>
<script>
    setTimeout(function() {
        var errorAlert = document.getElementById('errorAlert');
        if (errorAlert) {
            errorAlert.style.display = 'none';
        }
    }, 5000);

    setTimeout(function() {
        var successAlert = document.getElementById('successAlert');
        if (successAlert) {
            successAlert.style.display = 'none';
        }
    }, 5000);
</script>
</html>