package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dominio.Cliente;
import Dominio.ClienteDAO;
import Dominio.Cuenta;
import Dominio.CuentaDAO;

/**
 * Servlet implementation class ServletGestionCuentas
 */
@WebServlet("/ServletGestionCuentas")
public class ServletGestionCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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

	        CuentaDAO cu = new CuentaDAO();
	        ArrayList<Cuenta> listaCu = cu.obtenerCuentasPaginadas(numeroPagina, 5);
	        request.setAttribute("listaTodasCuentas", listaCu);
	        
	        request.setAttribute("cantPagsCuentas", cu.getCantPaginas());

	        RequestDispatcher rd = request.getRequestDispatcher("/PanelDeControl.jsp");
	        rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnCuentas")!=null) {
			CuentaDAO cu = new CuentaDAO();
			
			request.setAttribute("cantPagsCuentas", cu.getCantPaginas());
			
			
			ArrayList<Cuenta> listaCu = cu.obtenerCuentasPaginadas(1, 5);
			
			request.setAttribute("listaTodasCuentas", listaCu);
			
			RequestDispatcher rd = request.getRequestDispatcher("/PanelDeControl.jsp");
			rd.forward(request, response);
		}
	}


}
