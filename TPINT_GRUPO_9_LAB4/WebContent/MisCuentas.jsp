<%@ page import="java.util.ArrayList, Dominio.Cuenta" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mis Cuentas</title>
</head>
<body>
    <jsp:include page="NavbarClientes.jsp" />
  

    <h2>Mis Cuentas</h2>

   
	<form action="ServletCuenta" method="get">
	    <label for="filtroCuentas">Seleccione el tipo de cuenta:</label>
	    <select name="filtroCuentas" id="filtroCuentas">
	        <option value="todos">Todos los tipos</option>
	        <option value="ahorros">Caja de ahorro</option>
	        <option value="corrientes">Cuentas corriente</option>
	    </select>
	    <button type="submit" class="btn btn-primary" name="btnFiltrar">Ver cuentas</button>
	</form>

    <%
   
    ArrayList<Cuenta> cuentas = (ArrayList<Cuenta>) request.getAttribute("cuentas");
    String filtroCuentas = request.getParameter("filtroCuentas");
	
    
    if (cuentas != null) {
        for (Cuenta cuenta : cuentas) {
            // Verifica si se aplica un filtro
            if (filtroCuentas == null || filtroCuentas.equals("todos") || (filtroCuentas.equals("ahorros") && cuenta.getTipoCuenta().equals("Ahorros")) || (filtroCuentas.equals("corrientes") && cuenta.getTipoCuenta().equals("Corriente"))) {
%>
              
                <div>
                    <p>ID Cuenta: <%= cuenta.getIdCuenta() %></p>
                    <p>Tipo de cuenta: <%= cuenta.getTipoCuenta() %></p>
                    <p>Número de Cuenta: <%= cuenta.getNumeroCuenta() %></p>
                    <p>CBU: <%= cuenta.getCBU() %></p>
                    <p>Saldo: <%= cuenta.getSaldo() %></p>
                    <p>Fecha de creación: <%= cuenta.getFechaCreacion() %></p>
                </div>
<%
            }
        } 
    }
%>

</body>
</html>
