<%@page import="Dominio.Cliente"%>
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
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="Inicio.jsp">FRGP BANK</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">Gesti�n 1</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">Gesti�n 2</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">Gesti�n 3</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="PanelDeControl.jsp">Panel de Control</a>
                </li>
            </ul>
            </div>
           <div>
		   <ul class="navbar-nav">
		    <li class="nav-item">
			    <a class="btn" type="submit" href="PerfilUsuario.jsp">Admin</a>
			</li>
		    <li class="nav-item">
		        <a class="btn" type="submit" href="ServletCerrarSesion">Cerrar sesi�n</a>
		    </li>
			</ul>

        </div>
    </div>
</nav>

</body>
</html>
