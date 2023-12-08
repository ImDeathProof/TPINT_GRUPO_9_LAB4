<%@page import="entidad.Cliente"%>
<%@page import="entidad.Cuota"%>
<%@page import="entidad.Cuenta"%>
<%@page import="entidad.Prestamo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:include page="Header.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FRGP Bank | Pagar Prestamos</title>
<script src="jspdf.min.js"></script>
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
					<div class="row">
						<h1>Pago de Préstamos</h1>
						<form method="post" action="ServletListarCuotas">
								<input class="btn btn-success m-2" type="submit" name="btnListarCuotas" value="Ver todas las cuotas">
						</form>
					</div>
					<%
					if(session.getAttribute("error") != null) {%>
						<div class="alert alert-danger">
			        		<%= (String)session.getAttribute("error")%> 
					    </div>
					<%}
				     	ArrayList<Cuota> listaCuotas = null;
				     	ArrayList<Cuenta> listaCuentas = null;
				      	if(request.getAttribute("listaCuotas")!= null){
				      		listaCuotas = (ArrayList<Cuota>) request.getAttribute("listaCuotas");
					      	if(request.getAttribute("listaCuentas")!= null){
					      		listaCuentas =(ArrayList<Cuenta>) request.getAttribute("listaCuentas");
					      	}%>
					      	<div class="row">
							   <table class="table">
								    <thead>
								        <tr>
								             <th scope="col">Fecha de pago</th>	
								            <th scope="col">Monto a pagar</th>
								            <th scope="col">N° de cuota</th>
								            <th scope="col">Cuotas totales</th>
								            <th scope="col">Pagar</th>
								        </tr>
								    </thead>
								    <tbody>
								        <% if (listaCuotas != null && !listaCuotas.isEmpty()) {
								            for (Cuota cta : listaCuotas) { %>
								            
									            <tr>
									                
									                 <td><%= cta.getFechaDePago() %></td>							                
									                <td><%= cta.getMontoAPagar() %></td>
									                <td><%= cta.getNro_Cuota() %></td>
									                <td><%= cta.getCuotas_Totales() %></td>
									                <td>
									                   <form action="ServletPagarCuota" method="post">
									                        <% if (cta.getEstado().equalsIgnoreCase("No Pagado")) { %>
									                        <select name="SelectCuentas" class="form-control p-2">
											                   <option value="Seleccionar">Seleccionar cuenta</option>
											                    <%if(listaCuentas != null && !listaCuentas.isEmpty()){
											                    for(Cuenta ct : listaCuentas){
											                    if(ct.getEstado())
											                    {%>
											                    	<option value="<%=ct.getIdCuenta()%>"><%= ct.getNumeroCuenta() %></option>
											                    <%}}} %>
								    	            		</select>
									                        <input type="hidden" name="IDCuota" value="<%= cta.getIDCuota() %>">
									                            <input type="submit" name="PagarCuota" value="Pagar" class="btn btn-success">
		
									                        <% }else{ %>
									                        	Cuota pagada
									                        	<%}%>
									                   </form>
									                </td>
									            </tr>
								            
								            <% } }%>
								    </tbody>
								</table>
					      	</div>
						<% }%>
						<div class="row">
							<%if(session.getAttribute("PagoExitoso")!= null){ %>
								<div class="alert alert-success">
						             <%= (String)session.getAttribute("PagoExitoso")%> 
						        </div>
								<%String base64Image = (String) request.getSession().getAttribute("base64Image");
								if (base64Image != null && !base64Image.isEmpty()) { %>
									        <!-- Muestra el comprobante -->
								<img class="rounded mx-auto d-block border" id="comprobanteTransferencia" src="data:image/png;base64, <%=base64Image%>" alt="Comprobante de pago">
								<button class="btn btn-primary mt-2" id="descargarBtn">Descargar</button>
								<script>
									document.getElementById('descargarBtn').addEventListener('click', function () {
										if (typeof jsPDF !== 'undefined') {
											// Obtener la imagen directamente del elemento <img>
											var imgElement = document.getElementById('comprobanteTransferencia');
													
											// Crear un canvas y dibujar la imagen en él
											var canvas = document.createElement('canvas');
											canvas.width = imgElement.width;
											canvas.height = imgElement.height;
											var ctx = canvas.getContext('2d');
											ctx.drawImage(imgElement, 0, 0, imgElement.width, imgElement.height);
													
											// Obtener la cadena base64 del canvas
											var base64Image = canvas.toDataURL('image/png');
													
											descargarPDF(base64Image);
										} else {
											console.error('La biblioteca jsPDF no está cargada correctamente.');
										}
									});
											
									function descargarPDF(base64Image) {
										var doc = new jsPDF();
										doc.addImage(base64Image, 'PNG', 10, 10, 180, 120);
										doc.save('comprobante.pdf');
									}
								</script>
							<%}}%>
						</div>
				</div>
				<div class="col-1"></div>
		</div>
				
	<!-- FINAL DEL CONTENIDO DE LA PAGINA-->
	<% } %>

</body>
</html>