<%@page import="entidad.Cliente"%>
<%@ page import="java.util.Base64" %>
<%@page import="java.io.*"%>
<%@page import="org.jfree.chart.*"%>
<%@page import="org.jfree.chart.plot.*"%>
<%@page import="org.jfree.data.general.*"%>
<%@page import="org.jfree.data.category.DefaultCategoryDataset.*"%>

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
	<!-- DEMAS CONTENIDO DE LA PAGINA-->
		<div class="row text-center">
			<div class="col-1 ">
			</div>
			<div class="col-10 ">
				<div class="accordion accordion-flush" id="accordionFlushExample">
					  <!-- INFORMES DE CLIENTES -->
					  <div class="accordion-item">
						    <form action="ServletInformes" method="post">
							    <h2 class="accordion-header">
							      <button class="accordion-button collapsed" name="btnInformeClientes" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
							        INFORMES CLIENTES
							      </button>
							    </h2>
						    </form>
						    <div id="flush-collapseOne" class="accordion-collapse collapse" data-bs-parent="#accordionFlushExample">
						    	<div class="accordion-body">
									<div class="row border">
										<div class="col-6 border">
										PANEL ADMINISTRACION</div>
										<div class="col-6 border">
										VISUALIZACION</div>
									</div>
								</div>
						    </div>
					  </div>
					  <!-- FIN INFORMES DE CLIENTES -->
					  <!-- INFORMES DE CUENTAS -->
					  <div class="accordion-item">
					    	<h2 class="accordion-header">
						      <input value="Informe Cuentas" class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseTwo" aria-expanded="false" aria-controls="flush-collapseTwo">
						    </h2>
					    <div id="flush-collapseTwo" class="accordion-collapse collapse" data-bs-parent="#accordionFlushExample">
					    	<div class="accordion-body">
						      	<div class="row border" >
									<div class="col-2 border">
									PANEL ADMINISTRACION
									<form action="ServletInformes" method="post">
										<input value="Informe Cuentas" name="btnInformeCuentas" type="submit">
									</form>
									</div>
										<div class="col-10 border">

										<% 
										if(request.getAttribute("chartImageURL") != null){
										    String chartImageURL = (String) request.getAttribute("chartImageURL");
										%>
											<img src="<%= request.getContextPath() %><%= chartImageURL %>" alt="Cuentas por Mes">
										<% 
										}
										%>

									</div>
								</div>
							</div>
					    </div>
					</div>
				</div>	
			</div>
			<div class="col-1 ">
			</div>
		</div>
	<!-- FINAL DEL CONTENIDO DE LA PAGINA-->
	<% }} %>
</body>
</html>