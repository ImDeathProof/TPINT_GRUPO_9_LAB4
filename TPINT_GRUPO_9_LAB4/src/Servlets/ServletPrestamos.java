package Servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.Prestamo;
import negocio.ClienteNeg;
import negocio.CuentaNeg;
import negocio.PrestamoNeg;
import negocioImpl.ClienteNegImpl;
import negocioImpl.CuentaNegImpl;
import negocioImpl.PrestamoNegocioImpl;


/**
 * Servlet implementation class ServletPrestamos
 */
@WebServlet("/ServletPrestamos")
public class ServletPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
    PrestamoNeg negPr = new PrestamoNegocioImpl();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPrestamos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		if(request.getParameter("btnSolicitar")!=null) {
			Prestamo pr = new Prestamo();
			CuentaNegImpl cn = new CuentaNegImpl();
			Cuenta cuenta = new Cuenta();
			Cliente cliente = (Cliente) request.getSession().getAttribute("usuarioAutenticado");
			
			if ("ahorros".equals(request.getParameter("SelectCuentas"))) {
				cuenta = cn.obtenerCuentaAhorroPorUsuario(cliente.get_IDCliente());
			}else {
				cuenta = cn.obtenerCuentaCorrientePorUsuario(cliente.get_IDCliente());
			}
			String cuotas;
			int cantCuotas = 0;
			if(request.getParameter("inlineRadioOptions")!=null) {
				cuotas = request.getParameter("inlineRadioOptions");
				switch (cuotas) {
				case "1cuota":
					cantCuotas = 1;
					break;
				case "3cuotas":
					cantCuotas = 3;
					break;
				case "6cuotas":
					cantCuotas = 6;
					break;
				case "12cuotas":
					cantCuotas = 12;
					break;
				case "24cuotas":
					cantCuotas = 24;
					break;
				case "36cuotas":
					cantCuotas = 36;
					break;
				}
			}
			
			pr.setCliente(cliente);
			pr.setCuenta(cuenta);
			pr.setEstado(false);
			pr.setFechaPedido(LocalDate.now());
			BigDecimal monto = new BigDecimal(request.getParameter("txtMonto"));
			pr.setMonto(monto);
			BigDecimal cuotasBD = new BigDecimal(cantCuotas);
			BigDecimal importeCuota = monto.divide(cuotasBD);
			pr.setImporteCuota(importeCuota);
			pr.setPlazoPago(30); //Por ahora es 30(dias) para testear y despues se peude cambiar
			pr.setTasaInteres(0);//Por ahora es 0% para testear y despues se puede cambiar
			
			try {
		    	
			       int filas = negPr.Insertar(pr);		
				   if(filas > 0) {
			     		request.getSession().setAttribute("SolicitudPR", true);
			     		RequestDispatcher dispatcher = request.getRequestDispatcher("/SolicitarPrestamo.jsp");
						dispatcher.forward(request, response);
				   }
					            
			    } catch (Exception e) {
					e.printStackTrace();
			    }
		}
		
	}

}
