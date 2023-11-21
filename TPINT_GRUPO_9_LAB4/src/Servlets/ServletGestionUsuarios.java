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
import entidad.Cliente;
import negocio.ClienteNeg;
import negocioImpl.ClienteNegImpl;
import entidad.DBException;
import entidad.GenericException;
/**
 * Servlet implementation class ServletGestionUsuarios
 */
@WebServlet("/ServletGestionUsuarios")
public class ServletGestionUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClienteNeg clNeg = new ClienteNegImpl();
       
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
		 try{String paginaElegida = request.getParameter("pagina");
	        int numeroPagina = 1;

	        if (paginaElegida != null && !paginaElegida.isEmpty()) {
	            try {
	                numeroPagina = Integer.parseInt(paginaElegida);
	            } catch (NumberFormatException e) {
	            	  throw e;
	            }
	        }

	        ArrayList<Cliente> listaUsuarios = clNeg.obtenerUsuariosPaginados(numeroPagina, 5);
	        request.setAttribute("listaUsuarios", listaUsuarios);
	        
	        request.setAttribute("cantPags", clNeg.getCantPaginas());

	        RequestDispatcher rd = request.getRequestDispatcher("/PanelDeControl.jsp");
	        rd.forward(request, response);
	}catch (DBException e) {
        e.printStackTrace();
        request.getSession().setAttribute("error", "Error de base de datos. Por favor, inténtalo de nuevo más tarde.");
        response.sendRedirect("Login.jsp");
	}catch (GenericException e) {
        e.printStackTrace();
        request.getSession().setAttribute("error", "Hubo un error inesperado. Intente nuevamente más tarde");	        
        response.sendRedirect("PanelDeControl.jsp");
    }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			if(request.getParameter("btnUsuarios")!=null) {
		
			
			request.setAttribute("cantPags", clNeg.getCantPaginas());
			
			ArrayList<Cliente> lista = (ArrayList<Cliente>)clNeg.obtenerUsuariosPaginados(1, 5);
			
			request.setAttribute("listaUsuarios", lista);
			
			RequestDispatcher rd = request.getRequestDispatcher("/PanelDeControl.jsp");
			rd.forward(request, response);
		}
	}catch (DBException e) {
        e.printStackTrace();
        request.getSession().setAttribute("error", "Error de base de datos. Por favor, inténtalo de nuevo más tarde."+ e.getMessage());	
        response.sendRedirect("PanelDeControl.jsp");
    }catch (GenericException e) {
        e.printStackTrace();
        request.getSession().setAttribute("error", "Hubo un error inesperado. Intente nuevamente más tarde"+ e.getMessage());	        
        response.sendRedirect("PanelDeControl.jsp");
    }
	}
}
