<%@page import="entidad.Cliente"%>
<%@ page import="java.util.Base64" %>
<%@page import="java.io.*"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:include page="Header.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BFRGP| Informes</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
	<% if (session.getAttribute("usuarioAutenticado") == null) { 
		response.sendRedirect("Inicio.jsp");
	} else { 
		Cliente cliente = (Cliente) session.getAttribute("usuarioAutenticado"); 	
		if(cliente.is_Admin()){%>
			<jsp:include page="NavbarAdmin.jsp"/>
	<!-- DEMAS CONTENIDO DE LA PAGINA-->
		<div class="row">
			<div class="col-1 ">
			</div>
			<div class="col-10 ">
				<div class="accordion accordion-flush" id="accordionFlushExample">
					  <!-- INFORMES DE CLIENTES -->
					  <div class="accordion-item">
						    <h2 class="accordion-header">
							      <button class="accordion-button collapsed" name="btnInformeClientes" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
							        INFORMES CLIENTES
							      </button>
							    </h2>
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
							<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseTwo" aria-expanded="false" aria-controls="flush-collapseTwo">
								INFORMES CUENTAS
							</button>
							</h2>
						    <div id="flush-collapseTwo" class="accordion-collapse collapse" data-bs-parent="#accordionFlushExample">
						    	<div class="accordion-body">
									<div class="row border">
										<div class="col-4 p-2 border">
											<div id="controlesGraficoMes"class="">
												<h6>Comparar alta de cuentas</h6>
												<p>Seleccione el mes y año que desea comparar con el año anterior</p>
												<form action="ServletInformes" method="post">
													<div class="">
														<label>Mes</label>
														<select class="form-select" name="selectMes" aria-label="Default select example">
															<option selected>Seleccionar Mes</option>
															<option value="1">Enero</option>
															<option value="2">Febrero</option>
															<option value="3">Marzo</option>
															<option value="4">Abril</option>
															<option value="5">Mayo</option>
															<option value="6">Junio</option>
															<option value="7">Julio</option>
															<option value="8">Agosto</option>
															<option value="9">Septiempre</option>
															<option value="10">Octubre</option>
															<option value="11">Noviembre</option>
															<option value="12">Diciembre</option>
														</select>
													</div>
													<div class="">
														<label>Año</label>
														<select class="form-select" name="selectAno" aria-label="Default select example">
														  <option selected>Seleccionar Año</option>
														  <option value="2021">2021</option>
														  <option value="2022">2022</option>
														  <option value="2023">2023</option>
														</select>
													</div>
													<div class="p-2">
														<input class="btn btn-success" value="Generar Informe" name="btnGenerarInformeCuentasMensual" type="submit">
													</div>
												</form>
											</div>
											<hr>
											<div id="controlesGraficoTipoCuentas"class="">
												<h6>Comparar tipos de cuentas</h6>
												<p>Muestra las cantidades segun el tipo desde la primera hasta la ultima</p>
												<form action="ServletInformes" method="post">
													<div>
														<div>
															<label>Tipo de cuenta:</label>
														</div>
														<div class="p-2">
															<input class="btn btn-success"value="Generar Informe" name="btnGenerarInformeCuentasTipo" type="submit">
														</div>
													</div>
												</form>
											</div>
										</div>
										<div class="col-8 border">
											<%if(request.getAttribute("graficoAnual")!=null){
												if((boolean)request.getAttribute("graficoAnual")){
												
													String mes = (String)request.getAttribute("mes");
													Integer ano = (Integer)request.getAttribute("ano");
													Integer cantidadSolicitada = (Integer)request.getAttribute("cantidad1");
													
													Integer anoanterior = (Integer)request.getAttribute("anoAnterior");
													Integer cantidadAnoAnterior = (Integer)request.getAttribute("cantidad2");
													%>
													<div class="row">
														<div class="col-6">
															<h6>Altas del mes de <%=mes%> del año <%=ano%></h6>
															<h6><%=cantidadSolicitada%></h6>
														</div>
														<div class="col-6">
															<h6>Altas del mes de <%=mes%> del año <%=anoanterior%></h6>
															<h6><%=cantidadAnoAnterior%></h6>
														</div>
													</div>
													<div class="row">
														<canvas id="graficoAnual" style="width:300px"></canvas>
													</div>
													<!-- SCRIPT TORTA ALTA DE CUENTAS -->
													<script>
												    // Datos para el gráfico
												    var datos = {
												      labels: [<%=ano%>,<%=anoanterior%>],
												      datasets: [{
												        data: [<%=cantidadSolicitada%>, <%=cantidadAnoAnterior%>],
												        backgroundColor: ['#FF6384', '#36A2EB'], // Colores para cada sección
												      }]
												    };
												
												    // Configuración del gráfico
												    var opciones = {
												      responsive: true,
												      maintainAspectRatio: false,
												    };
												
												    // Obtener el contexto del lienzo (canvas)
												    var ctx = document.getElementById('graficoAnual').getContext('2d');
												
												    // Crear el gráfico de torta
												    var miGrafico = new Chart(ctx, {
												      type: 'pie',
												      data: datos,
												      options: opciones
												    });
												  </script>
											<%}}else{
												if(request.getAttribute("graficoTipos")!=null){
													if((boolean)request.getAttribute("graficoTipos")){
													Integer cuentaCorriente = (Integer)request.getAttribute("ctCorriente");
													Integer cajaDeAhorro = (Integer)request.getAttribute("ctAhorro");
													%>
														<div class="row">
															<canvas id="graficoTipos" style="width:300px"></canvas>
														</div>
														<!-- SCRIPT BARRAS TIPOS DE CUENTA -->
													  	<script>
														  	var data = {
														  			labels: ['Cuenta corriente','Caja de ahorro'],
														  	        datasets:[{
														  	            label: 'Valores',
														  	            data: [<%=cuentaCorriente%>, <%=cajaDeAhorro%>],
														  	            borderColor: 'rgba(75, 192, 192, 1)',
														  	            backgroundColor: 'rgba(75, 192, 192, 0.2)',
														  	            borderWidth: 1
														  	        }]
														  	};
														  	var opciones = {
														  			responsive: true,
														  	        maintainAspectRatio: false,
														  	        scales: {
														  	            y: {
														  	                beginAtZero: true
														  	            }
														  	        }
														  			    };
														  	var ctx = document.getElementById('graficoTipos').getContext('2d');
														  	var miGrafico = new Chart(ctx, {
														        type: 'bar',
														        data: data,
														        options: opciones
														      });
														  	
													  	</script>
											<%}}}%>
										</div>
									</div>
								</div>
						    </div>
					  </div>
					  <!-- FIN INFORMES DE CCUENTAS -->
				</div>	
			</div>
			<div class="col-1 ">
			</div>
		</div>
	<!-- FINAL DEL CONTENIDO DE LA PAGINA-->
	<!-- SCRIPTS GENERALES-->
  	<script>
    $(document).ready(function() {
        // Recupera el estado almacenado de la cookie
        var accordionState = localStorage.getItem('accordionState');

        // Si hay un estado almacenado, aplica la clase 'show' al Accordion
        if (accordionState === 'show') {
            $('#flush-collapseTwo').addClass('show');
        }

        // Captura el evento de ocultar o mostrar el Accordion
        $('#flush-collapseTwo').on('hidden.bs.collapse shown.bs.collapse', function () {
            // Almacena el estado en la cookie cuando se oculta o muestra el Accordion
            localStorage.setItem('accordionState', $(this).hasClass('show') ? 'show' : '');
        });
    	});
	</script>
	
  
  <!-- SCRIPTS GRAFICOS CLIENTES -->
  <!-- SCRIPT BARRAS CLIENTES POR SEXO -->
  <script>
  </script>
  <!-- SCRIPT TORTA CLIENTES POR PROVINCIA -->
  <script>
  </script>
<%}} %>
</body>
</html>