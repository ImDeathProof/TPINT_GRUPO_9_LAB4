package Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import entidad.DBException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import entidad.DBException;
import entidad.Prestamo;

import negocio.PrestamoNeg;

import negocioImpl.PrestamoNegocioImpl;
import entidad.GenericException;

/**
 * Servlet implementation class ServletPrestamosPorUsuario
 */
@WebServlet("/ServletPrestamosPorUsuario")
public class ServletPrestamosPorUsuario extends HttpServlet {
    private static final long serialVersionUID = 1L;
    PrestamoNeg prestamoNeg = new PrestamoNegocioImpl(); 



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
    	if(request.getParameter("btnListarPrestamos")!=null) {
    		Cliente usuarioActivo = (Cliente) request.getSession().getAttribute("usuarioAutenticado");
    		
    		try {
	    		if (usuarioActivo != null) {
	    			ArrayList<Prestamo> prestamos = prestamoNeg.obtenerPrestamosAprobadosPorUsuario(usuarioActivo.get_IDCliente());
	    			if(!(prestamos.isEmpty())) {
	    				request.setAttribute("listaPrestamos", prestamos); 
	    			}
	    			else {
	    				request.setAttribute("errorCarga", true);
	    			}
	    		}
    		}catch (DBException e) {
		        e.printStackTrace();
		        request.getSession().setAttribute("error", "Error de base de datos. Por favor, inténtalo de nuevo más tarde."+ e.getMessage());	
		    }catch (GenericException e) {
		        e.printStackTrace();
		        request.getSession().setAttribute("error", "Hubo un error inesperado. Intente nuevamente más tarde"+ e.getMessage());	        
		        response.sendRedirect("PrestamosAprobados.jsp");
		    }
    		finally {
    			request.getRequestDispatcher("PrestamosAprobados.jsp").forward(request, response);    			
    		}
    	}
    	
    }
}