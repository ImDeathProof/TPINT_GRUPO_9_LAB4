package Servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Movimiento;
import excepciones.DBException;
import excepciones.GenericException;
import negocio.MovimientoNeg;
import negocioImpl.MovimientoNegImpl;

/**
 * Servlet implementation class ServletMisMovimientos
 */
@WebServlet("/ServletMisMovimientos")
public class ServletMisMovimientos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MovimientoNeg movNeg = new MovimientoNegImpl();  
	

    public ServletMisMovimientos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//LIMPIAR MENSAJES
		request.getSession().removeAttribute("error");
		
		String paginaElegida = request.getParameter("pagina");
        int numeroPagina = 1;

       try {
        
	        if (paginaElegida != null && !paginaElegida.isEmpty()) {
	            try {
	                numeroPagina = Integer.parseInt(paginaElegida);
	            } catch (NumberFormatException e) {
	            	  throw e;
	            }
	        }
	        HttpSession session = request.getSession();
	        int idCuenta = (int) session.getAttribute("idCuenta");         
	        
	        ArrayList<Movimiento> lista = movNeg.obtenerInformePaginado(numeroPagina, 5,idCuenta);
	        request.setAttribute("misMovimientos", lista);     
	        
	        request.setAttribute("cantPagsMisMovimientos", movNeg.getCantPaginas(idCuenta));

		}catch (DBException e) {
	        e.printStackTrace();
	        request.getSession().setAttribute("error", "Error de base de datos. Por favor, int�ntalo de nuevo m�s tarde. \n" + e.getMessage());	        
	        response.sendRedirect("MisCuentas.jsp");
	    }catch (GenericException e) {
	        e.printStackTrace();
	        request.getSession().setAttribute("error", "Hubo un error inesperado. Intente nuevamente m�s tarde");	        
	        response.sendRedirect("MisCuentas.jsp");
	    }
	    RequestDispatcher rd = request.getRequestDispatcher("MisCuentas.jsp");
	    rd.forward(request, response);      
       
       
       
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//LIMPIAR MENSAJES
		if (request.getSession().getAttribute("error") != null) {
			  		request.getSession().removeAttribute("error");
		}
		
		try { 
		
		int idCuenta = Integer.parseInt(request.getParameter("idCliente"));
		 request.setAttribute("cantPagsMisMovimientos", movNeg.getCantPaginas(idCuenta));	
		 
		 HttpSession session = request.getSession();
		 session.setAttribute("idCuenta", idCuenta);
		 
		 request.setAttribute("cantPagsMisMovimientos", movNeg.getCantPaginas(idCuenta));
		 
		 ArrayList<Movimiento> lista = movNeg.obtenerInformePaginado(1, 5,idCuenta );
			
		 request.setAttribute("misMovimientos", lista);
		
		 RequestDispatcher rd = request.getRequestDispatcher("/MisCuentas.jsp");
		 rd.forward(request, response);
		}catch (DBException e) {
	        e.printStackTrace();
	        request.getSession().setAttribute("error", "Error de base de datos. Por favor, int�ntalo de nuevo m�s tarde. \n" + e.getMessage());	        
	        response.sendRedirect("MisCuentas.jsp");
	    }catch (GenericException e) {
	        e.printStackTrace();
	        request.getSession().setAttribute("error", "Hubo un error inesperado. Intente nuevamente m�s tarde"+ e.getMessage());		        
	        response.sendRedirect("MisCuentas.jsp");
	    }
	}

}
