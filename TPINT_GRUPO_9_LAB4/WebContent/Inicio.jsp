<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:include page="Header.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Banco FRGP</title>
	<style>
        .carousel-item img {
            max-width: 100%;
            max-height: 400px;
            width: auto;
            height: auto;
        }
        .call-to-action img {
        	max-width: 100%;
            max-height: 300px;
            width: auto;
            height: auto;
        }
    </style>
</head>
<body>
<% if (session.getAttribute("usuarioAutenticado") == null) { %>
	<jsp:include page="Navbar.jsp" />
	<% } else { %>
	<jsp:include page="NavbarClientes.jsp" />
	<% } %>
	<!-- DEMAS CONTENIDO DE LA PAGINA -->
	<div class="row mt-2">
		<div class="col-1">
		</div>
		<div class="col-10">
			<div id="carouselExampleDark" class="row carousel carousel-dark slide">
				  <div class="carousel-inner">
				    <div class="carousel-item active" data-bs-interval="10000">
				      <img src="https://concepto.de/wp-content/uploads/2020/09/dinero-e1599518998156.jpg" class="d-block w-100" alt="...">
				      <div class="carousel-caption d-none d-md-block">
				        <h5>TEXTO DE PRUEBA</h5>
				        <p><a href="SolicitarPrestamo.jsp" class="link-light">Solicita tu prestamo haciendo clic ACA!</a></p>
				      </div>
				    </div>
				    <div class="carousel-item" data-bs-interval="2000">
				      <img src="https://www.primeraedicion.com.ar/wp-content/uploads/2023/04/dolar-1-750x375.jpg" class="d-block w-100" alt="...">
				      <div class="carousel-caption d-none d-md-block">
				        <h5>TEXTO DE PRUEBA</h5>
				        <p><a href="SolicitarPrestamo.jsp" class="link-light">Solicita tu prestamo haciendo clic ACA!</a></p>
				      </div>
				    </div>
				    <div class="carousel-item">
				      <img src="https://www.teknei.com/wp-content/uploads/2021/09/los-procesos-mas-automatizados-de-los-bancos.jpg" class="d-block w-100" alt="...">
				      <div class="carousel-caption d-none d-md-block">
				        <h5>TEXTO DE PRUEBA</h5>
				        <p><a href="SolicitarPrestamo.jsp" class="link-light">Solicita tu prestamo haciendo clic ACA!</a></p>
				      </div>
				    </div>
				  </div>
				  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="prev">
				    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
				    <span class="visually-hidden">Previous</span>
				  </button>
				  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="next">
				    <span class="carousel-control-next-icon" aria-hidden="true"></span>
				    <span class="visually-hidden">Next</span>
				  </button>
			</div>
			<div class="row m-2 text-center">
			  <div class="col-sm-3 mb-3 mb-sm-0">
			    <div class="card">
			      <div class="card-body">
			        <h5 class="card-title">PRESTAMOS</h5>
			        <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
			        <a href="#" class="btn btn-primary">Go somewhere</a>
			      </div>
			    </div>
			  </div>
			  <div class="col-sm-3">
			    <div class="card">
			      <div class="card-body">
			        <h5 class="card-title">CUENTAS</h5>
			        <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
			        <a href="#" class="btn btn-primary">Go somewhere</a>
			      </div>
			    </div>
			  </div>
			  <div class="col-sm-3 mb-3 mb-sm-0">
			    <div class="card">
			      <div class="card-body">
			        <h5 class="card-title">TARJETAS</h5>
			        <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
			        <a href="#" class="btn btn-primary">Go somewhere</a>
			      </div>
			    </div>
			  </div>
			  <div class="col-sm-3">
			    <div class="card">
			      <div class="card-body">
			        <h5 class="card-title">INVERSIONES</h5>
			        <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
			        <a href="#" class="btn btn-primary">Go somewhere</a>
			      </div>
			    </div>
			  </div>
			</div>
				<div class="row m-2">
					<div class="col-4">
						<div class="call-to-action">
							<a class="" href="SolicitarPrestamo.jsp">
								<img src="https://drive.google.com/uc?export=download&id=12dD2oAC6cu4JNcCZxduABd5MZjCOW4_2" class="" alt="">
							</a>
						</div>
					</div>
					<div class="col-4">
						<div class="call-to-action">
							<a class="" href="SolicitarPrestamo.jsp">
								<img src="https://drive.google.com/uc?export=download&id=1fhNIaRBxonXmdYwcPPxkrEStHn3dWACR" class="" alt="">
							</a>
						</div>
					</div>
					<div class="col-4">
						<div class="call-to-action">
							<a class="" href="SolicitarPrestamo.jsp">
								<img src="https://drive.google.com/uc?export=download&id=1yu1HIqsFMqTgMg0nRhFbD0WuOHsjFGYy" class="" alt="">
							</a>
						</div>
					</div>
					
				</div>
			</div>
			<div class="col-1">
			</div>
		</div>
</body>
</html>