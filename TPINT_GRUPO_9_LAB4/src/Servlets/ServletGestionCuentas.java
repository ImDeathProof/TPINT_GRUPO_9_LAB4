package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImpl.ClienteDAO;
import daoImpl.CuentaDAO;
import entidad.Cliente;
import entidad.Cuenta;
import negocio.CuentaNeg;
import negocioImpl.CuentaNegImpl;

/**
 * Servlet implementation class ServletGestionCuentas
 */
@WebServlet("/ServletGestionCuentas")
public class ServletGestionCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CuentaNeg cuNeg = new CuentaNegImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGestionCuentas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paginaElegida = request.getParameter("paginaCuenta");
	      int numeroPagina = 1;

	        if (paginaElegida != null && !paginaElegida.isEmpty()) {
	            try {
	                numeroPagina = Integer.parseInt(paginaElegida);
	            } catch (NumberFormatException e) {
	            	  throw e;
	            }
	        }

	        ArrayList<Cuenta> listaCu = cuNeg.obtenerCuentasPaginadas(numeroPagina, 5);
	        request.setAttribute("listaTodasCuentas", listaCu);
	        
	        request.setAttribute("cantPagsCuentas", cuNeg.getCantPaginas());

	        RequestDispatcher rd = request.getRequestDispatcher("/PanelDeControl.jsp");
	        rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnCuentas")!=null) {
			
			request.setAttribute("cantPagsCuentas", cuNeg.getCantPaginas());
			
			
			ArrayList<Cuenta> listaCu = cuNeg.obtenerCuentasPaginadas(1, 5);
			
			request.setAttribute("listaTodasCuentas", listaCu);
			
			RequestDispatcher rd = request.getRequestDispatcher("/PanelDeControl.jsp");
			rd.forward(request, response);
		}
	}


}
