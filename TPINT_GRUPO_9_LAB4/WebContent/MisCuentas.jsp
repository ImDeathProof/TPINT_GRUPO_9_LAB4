<%@page import="entidad.Cliente"%>
<%@page import="entidad.Movimiento"%>
<%@ page import="java.util.ArrayList,entidad.Cuenta" %>
<jsp:include page="Header.jsp" />
<jsp:include page="NavbarClientes.jsp" />
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mis Cuentas</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <%
    if (session.getAttribute("usuarioAutenticado") == null) {
        response.sendRedirect("Login.jsp");
    } else {
        Cliente cliente = (Cliente) session.getAttribute("usuarioAutenticado");
    %>
        <div class="container">
            <h2>Mis Cuentas</h2>
            <form action="ServletCuenta" method="get">
                <label for="filtroCuentas">Seleccione el tipo de cuenta:</label>
                <select name="filtroCuentas" id="filtroCuentas" class="form-control">
                    <option value="todos">Todos los tipos</option>
                    <option value="ahorros">Caja de ahorro</option>
                    <option value="corrientes">Cuentas corriente</option>
                </select>
                <button type="submit" class="btn btn-primary mt-2" name="btnFiltrar">Ver cuentas</button>
            </form>
            <div class="text-center mt-4 mb-2">
            <a class="btn btn-info" href="RegistroCuenta.jsp">Solicitá tu cuenta haciendo click acá</a>
			</div>
			
            <%
            ArrayList<Cuenta> cuentas = (ArrayList<Cuenta>) request.getAttribute("cuentas");
            String filtroCuentas = request.getParameter("filtroCuentas");
            if (cuentas != null) {
            %>
            <%if(request.getAttribute("error")!= null){%>
            	<div class="alert alert-danger">
            		<%=(String)request.getAttribute("error")%>
            	</div>
            <%}%>
            <table class="table table-striped table-bordered">
                <thead class="thead-dark">
                    <tr>
                        <th>ID Cuenta</th>
                        <th>Tipo de cuenta</th>
                        <th>Número de Cuenta</th>
                        <th>CBU</th>
                        <th>Saldo</th>
                        <th>Fecha de creación</th>
                        <th>Estado</th>
                        <th>Ver Movimientos</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                    for (Cuenta cuenta : cuentas) {
                        if (filtroCuentas == null || filtroCuentas.equals("todos") || (filtroCuentas.equals("ahorros") && cuenta.getTipoCuenta().equals("Ahorros")) || (filtroCuentas.equals("corrientes") && cuenta.getTipoCuenta().equals("Corriente"))) {
                    %>
                    <tr>
                        <td><%= cuenta.getIdCuenta() %></td>
                        <td><%= cuenta.getTipoCuenta() %></td>
                        <td><%= cuenta.getNumeroCuenta() %></td>
                        <td><%= cuenta.getCBU() %></td>
                        <td><%= cuenta.getSaldo() %></td>
                        <td><%= cuenta.getFechaCreacion() %></td>
                        <td><% if (!cuenta.getEstado()) { %>En proceso<% } %></td>
                        <td>
	                        <form action="ServletMisMovimientos" method="post">
				             <input type="hidden" name="idCliente" value="<%= cuenta.getIdCuenta() %>" />
				             <button type="submit" class="btn btn-primary mt-2" name="btnMisMovimientos">Ver Movimientos de la cuenta</button>
				            </form>
				        </td>
                    </tr>
                    <%
                        }
                    }
                    %>
                </tbody>
            </table>
            <%
            }
            %>
                <%
            ArrayList<Movimiento> mov = (ArrayList<Movimiento>) request.getAttribute("misMovimientos");
            if (mov != null) {
            %>
                
            <table class="table table-striped table-bordered">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">Monto</th>
					    <th scope="col">Fecha</th>
						<th scope="col">Detalles/Tipo Movimiento</th>
						<th scope="col">IdUsuario</th>
						<th scope="col">IdCuenta</th>
                    </tr>
                </thead>
                <tbody>
                   <% if (mov != null) {
						for (Movimiento movi : mov) { %>
						 <tr>
							 <td><%= movi.getMonto() %></td>
							 <td><%= movi.getFecha() %></td>
							 <td><%= movi.getDetalles() %></td>
							 <td><%= movi.getIdCliente() %></td>
							 <td><%= movi.getIdCuenta() %></td>
						 </tr>
					  <% } } %>
                </tbody>
            </table>
            <form method="get" action="ServletMisMovimientos">
			 <%
				 int cantPags = (int)request.getAttribute("cantPagsMisMovimientos");
				 if (cantPags > 0) {
					 for (int i = 1; i <= cantPags; i++) {
					 %>
					 <button class="btn btn-primary" type="submit" name="pagina" value="<%= i %>">
						   Página <%= i %>
					 </button>
					 <%
					     } 
				   }
			    %>
			</form>
            <%
            }
            %>
            
        </div>
    <%
    }
    %>
</body>
</html>
