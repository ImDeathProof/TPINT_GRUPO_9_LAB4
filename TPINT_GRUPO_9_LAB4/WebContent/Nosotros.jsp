<%@page import="entidad.Cliente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:include page="Header.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FRGP BANK | Nosotros</title>
</head>
<body>
	<% if (session.getAttribute("usuarioAutenticado") == null) { 
		%>
		<jsp:include page="Navbar.jsp"/>
	<%
	} else { 
		Cliente cliente = (Cliente) session.getAttribute("usuarioAutenticado"); 	
		if(cliente.is_Admin()){%>
			<jsp:include page="NavbarAdmin.jsp"/>
		<% }else{ %>
			<jsp:include page="NavbarClientes.jsp" />
		<% }} %>
	<!-- DEMAS CONTENIDO DE LA PAGINA-->
	<div class="container mt-4 min-vh-100">
	    <div class="row">
	        <div class="col-lg-8">
	            <h2>Bienvenido/a!</h2>
				<p>En <strong>FRGP BANK</strong>, damos la más cordial bienvenida a nuestra página "Sobre Nosotros". Aquí, nos enorgullece compartir nuestra dedicación inquebrantable a la satisfacción del cliente y a brindar una experiencia excepcional en cada interacción.
				</p>
				<h4>Misión:</h4>
				<p>Nuestra misión es ser el aliado financiero de confianza para nuestros clientes. Nos dedicamos a proporcionar servicios bancarios de alta calidad, centrados en las necesidades individuales y empresariales. Buscamos fortalecer la estabilidad financiera de nuestros clientes y contribuir al crecimiento económico de las comunidades que servimos.
				</p>
				<h4>Visión:</h4>
				<p>Nos esforzamos por ser reconocidos como el banco líder en innovación financiera y servicio al cliente excepcional. Aspiramos a ser la primera opción para aquellos que buscan soluciones financieras sólidas y personalizadas. Buscamos impulsar el bienestar financiero de nuestros clientes y contribuir al desarrollo sostenible a través de prácticas bancarias responsables.
				</p>				
				<h4>Valores:</h4>
				<p><strong>Confianza:</strong> Construimos relaciones basadas en la confianza y la transparencia. Nos esforzamos por ser el socio financiero en el que nuestros clientes pueden confiar en todas las etapas de su vida y desarrollo empresarial.
				</p>
				<p><strong>Excelencia en el Servicio:</strong> Nos comprometemos a proporcionar un servicio excepcional a nuestros clientes. Buscamos superar sus expectativas mediante soluciones financieras personalizadas y un servicio proactivo y eficiente.
				</p>
				<p><strong>Innovación Financiera:</strong> Fomentamos la innovación en todos los aspectos de nuestros servicios. Buscamos constantemente maneras de mejorar la experiencia financiera de nuestros clientes mediante la adopción de tecnologías avanzadas y soluciones financieras innovadoras.
				</p>
				<p><strong>Responsabilidad Social Corporativa:</strong> Reconocemos nuestra responsabilidad en la construcción de comunidades sostenibles. Integramos prácticas socialmente responsables en nuestras operaciones y apoyamos iniciativas que promueven el bienestar social y ambiental.
				</p>
				<p><strong>Compromiso con el Desarrollo:</strong> Contribuimos al desarrollo económico de las comunidades a través de servicios financieros inclusivos y programas que fomentan la educación financiera y el emprendimiento.
	        	</p>
	        </div>
	        <div class="col-lg-4">
	            <div class="row">
		            <img src="https://drive.google.com/uc?export=download&id=1-V8fvvy6L8oYXsqdY9FoecZcGHoj3YwM" class="img-fluid rounded" alt="Imagen Sobre Nosotros">
	            </div>
	        	<div class="row">
	        		<h4>Proyecto Integrador Grupo 9</h4>
	        		<h6>Desarrollado por:</h6>
	        			<div class="ms-3">
		        			<h6>Blanco, Nazareno</h6>
							<h6>García, Elías</h6>
							<h6>Ponce de León, Tobías</h6>
	        			</div>
	        	</div>
	        </div>
	    </div>
	</div>
	<!-- FINAL DEL CONTENIDO DE LA PAGINA-->
	<jsp:include page="Footer.jsp"/>

</body>
</html>