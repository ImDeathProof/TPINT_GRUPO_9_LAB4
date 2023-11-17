package Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.DBException;
import entidad.Prestamo;
import negocio.PrestamoNeg;
import negocioImpl.PrestamoNegocioImpl;

/**
 * Servlet implementation class ServletListarPrestamos
 */
@WebServlet("/ServletListarPrestamos")
public class ServletListarPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrestamoNeg negPr = new PrestamoNegocioImpl();  
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListarPrestamos() {
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
        try {
	        ArrayList<Prestamo> lista = negPr.obtenerPrestamosPaginados(numeroPagina, 5);
	        request.setAttribute("listaPrestamos", lista);
	        
	        request.setAttribute("cantPags", negPr.getCantPaginas());
        }catch (DBException e) {
	        e.printStackTrace();
	        request.getSession().setAttribute("error", "Error de base de datos. Por favor, inténtalo de nuevo más tarde.");
	    }catch(Exception ex) {
 	        ex.printStackTrace();
		}finally {
	        RequestDispatcher rd = request.getRequestDispatcher("GestionPrestamos.jsp");
	        rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnListarPrestamos") != null) {
			try {
				request.setAttribute("cantPags", negPr.getCantPaginas());
				
				ArrayList<Prestamo> lista = (ArrayList<Prestamo>)negPr.obtenerPrestamosPaginados(1, 5);
				
				request.setAttribute("listaPrestamos", lista);
			}catch (DBException e) {
		        e.printStackTrace();
		        request.getSession().setAttribute("error", "Error de base de datos. Por favor, inténtalo de nuevo más tarde.");
		    }catch(Exception ex) {
	 	        ex.printStackTrace();
    		}finally {
				RequestDispatcher rd = request.getRequestDispatcher("GestionPrestamos.jsp");
				rd.forward(request, response);
    		}
		}
	}

}
