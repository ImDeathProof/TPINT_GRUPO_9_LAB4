package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;

import entidad.Prestamo;

import negocio.PrestamoNeg;

import negocioImpl.PrestamoNegocioImpl;

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
    		
    		if (usuarioActivo != null) {
    			ArrayList<Prestamo> prestamos = prestamoNeg.obtenerPrestamosAprobadosPorUsuario(usuarioActivo.get_IDCliente());
    			if(!(prestamos.isEmpty())) {
    				request.setAttribute("listaPrestamos", prestamos); 
    			}
    			else {
    				request.setAttribute("errorCarga", true);
    			}
    		}
    		request.getRequestDispatcher("PrestamosAprobados.jsp").forward(request, response);
    	}
    	
    }
}