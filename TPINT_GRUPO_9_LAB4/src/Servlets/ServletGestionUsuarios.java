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

/**
 * Servlet implementation class ServletGestionUsuarios
 */
@WebServlet("/ServletGestionUsuarios")
public class ServletGestionUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGestionUsuarios() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String paginaElegida = request.getParameter("pagina");
	        int numeroPagina = 1;

	        if (paginaElegida != null && !paginaElegida.isEmpty()) {
	            try {
	                numeroPagina = Integer.parseInt(paginaElegida);
	            } catch (NumberFormatException e) {
	            	  throw e;
	            }
	        }

	        ClienteDAO clienteDao = new ClienteDAO();
	        ArrayList<Cliente> listaUsuarios = clienteDao.obtenerUsuariosPaginados(numeroPagina, 5);
	        request.setAttribute("listaUsuarios", listaUsuarios);
	        
	        request.setAttribute("cantPags", clienteDao.getCantPaginas());

	        RequestDispatcher rd = request.getRequestDispatcher("/PanelDeControl.jsp");
	        rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnUsuarios")!=null) {
			
			ClienteDAO clienteDao = new ClienteDAO();	
			
			request.setAttribute("cantPags", clienteDao.getCantPaginas());
			
			ArrayList<Cliente> lista = (ArrayList<Cliente>)clienteDao.obtenerUsuariosPaginados(1, 5);
			
			request.setAttribute("listaUsuarios", lista);
			
			RequestDispatcher rd = request.getRequestDispatcher("/PanelDeControl.jsp");
			rd.forward(request, response);
		}
	}

}
