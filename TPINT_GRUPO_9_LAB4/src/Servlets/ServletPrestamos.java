package Servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
import entidad.Cuota;
import entidad.Prestamo;
import negocio.ClienteNeg;
import negocio.CuentaNeg;
import negocio.CuotaNeg;
import negocio.PrestamoNeg;
import negocioImpl.ClienteNegImpl;
import negocioImpl.CuentaNegImpl;
import negocioImpl.PrestamoNegocioImpl;
import negocioImpl.CuotaNegImpl;


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
			
			if ("ahorros".equals(request.getParameter("SelectCuentas")) && request.getParameter("SelectCuentas") != null) {
				cuenta = cn.obtenerCuentaAhorroPorUsuario(cliente.get_IDCliente());
			}else if(request.getParameter("SelectCuentas") != null){
				cuenta = cn.obtenerCuentaCorrientePorUsuario(cliente.get_IDCliente());
			}
			String cuotas;
			int cantCuotas = 0;
			if(request.getParameter("inlineRadioOptions") != null) {
				cuotas = request.getParameter("inlineRadioOptions");
				if(cuotas.equals("1cuota")) {
					cantCuotas = 1;
				}else if(cuotas.equals("3Cuotas")) {
					cantCuotas = 3;
				}else if(cuotas.equals("6Cuotas")) {
					cantCuotas = 6;
				}else if(cuotas.equals("12Cuotas")) {
					cantCuotas = 12;
				}else if(cuotas.equals("24Cuotas")) {
					cantCuotas = 24;
				}else if(cuotas.equals("36Cuotas")) {
					cantCuotas = 36;
				}
			}
			
			pr.setCantidadCuotas(cantCuotas);
			pr.setCliente(cliente);
			pr.setCuenta(cuenta);
			pr.setEstado("Pendiente");
			pr.setFechaPedido(LocalDate.now());
			BigDecimal monto = new BigDecimal(request.getParameter("txtMonto"));
			pr.setMonto(monto);
			if (cantCuotas != 0) {
	            BigDecimal importeCuota = monto.divide(new BigDecimal(cantCuotas), RoundingMode.HALF_UP);
	            pr.setImporteCuota(importeCuota);
	        }
			pr.setPlazoPago(30); //Por ahora es 30(dias) para testear y despues se peude cambiar
			pr.setTasaInteres(0);//Por ahora es 0% para testear y despues se puede cambiar
			
			try {
				int filas = negPr.Insertar(pr);		
				if(filas > 0) {
					try {
						int ultimo = negPr.obtenerUltimoID();
						CuotaNeg negCt = new CuotaNegImpl();
						for(int i = 0; i< cantCuotas ; i++) {
							Cuota ct = negCt.generarCuota(pr, ultimo, i+1);	
							negCt.Agregar(ct);
						} 
					}
					catch (Exception e) {
						e.printStackTrace();
					}
					
					request.getSession().setAttribute("PrestamoExitoso", "El préstamo fue solicitado con exito!");	
				}else {
					request.getSession().setAttribute("errorAlSolicitar", "Hubo un error al intentar solicitar el prestamo, intente de nuevo mas tarde!");
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher("/SolicitarPrestamo.jsp");
				dispatcher.forward(request, response);							
					            
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
