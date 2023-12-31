package Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.Prestamo;
import excepciones.DBException;
import excepciones.GenericException;
import negocio.CuentaNeg;
import negocio.PrestamoNeg;
import negocioImpl.CuentaNegImpl;
import negocioImpl.PrestamoNegocioImpl;

/**
 * Servlet implementation class ServletPrestamosPorUsuario
 */
@WebServlet("/ServletPrestamosPorUsuario")
public class ServletPrestamosPorUsuario extends HttpServlet {
    private static final long serialVersionUID = 1L;
    PrestamoNeg prestamoNeg = new PrestamoNegocioImpl(); 
    CuentaNeg ctNeg = new CuentaNegImpl();



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
    	if(request.getParameter("btnListarPrestamos")!=null) {
    		
    		//LIMPIAR MENSAJES
    		if (request.getSession().getAttribute("errorCarga") != null) {
    			  		request.getSession().removeAttribute("errorCarga");
    		}
    		if (request.getSession().getAttribute("error") != null) {
    			request.getSession().removeAttribute("error");
    		}
    		
    		Cliente usuarioActivo = (Cliente) request.getSession().getAttribute("usuarioAutenticado");
    		
    		try {
	    		if (usuarioActivo != null) {
	    			ArrayList<Prestamo> prestamos = prestamoNeg.obtenerPrestamosAprobadosPorUsuario(usuarioActivo.get_IDCliente());
	    			if(!(prestamos.isEmpty())) {
	    				request.setAttribute("listaPrestamos", prestamos); 
	    			}
	    			else {
	    				request.getSession().setAttribute("errorCarga", "No tienes prestamos aprobados.");
	    			}
	    		}
    		}catch (DBException e) {
		        e.printStackTrace();
		        request.getSession().setAttribute("error", "Error de base de datos. Por favor, int�ntalo de nuevo m�s tarde."+ e.getMessage());	
		    }catch (GenericException e) {
		        e.printStackTrace();
		        request.getSession().setAttribute("error", "Hubo un error inesperado. Intente nuevamente m�s tarde"+ e.getMessage());	        
		        response.sendRedirect("PrestamosAprobados.jsp");
		    }
    		finally {
    			request.getRequestDispatcher("PrestamosAprobados.jsp").forward(request, response);    			
    		}
    	}
    	
    }
}