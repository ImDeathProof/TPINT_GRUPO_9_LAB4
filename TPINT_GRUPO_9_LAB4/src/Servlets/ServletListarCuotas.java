package Servlets;

import java.io.IOException;
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
import entidad.DBException;
import entidad.Prestamo;
import negocio.CuentaNeg;
import negocio.CuotaNeg;
import negocio.PrestamoNeg;
import negocioImpl.CuentaNegImpl;
import negocioImpl.CuotaNegImpl;
import negocioImpl.PrestamoNegocioImpl;
import entidad.GenericException;

/**
 * Servlet implementation class ServletListarCuotas
 */
@WebServlet("/ServletListarCuotas")
public class ServletListarCuotas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CuotaNeg ctaNeg = new CuotaNegImpl(); 
	CuentaNeg ctNeg = new CuentaNegImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListarCuotas() {
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
		if(request.getParameter("btnListarCuotas") != null) {
			
				Cliente cl = (Cliente) request.getSession().getAttribute("usuarioAutenticado");
				try {
					ArrayList<Cuota> lista = ctaNeg.obtenerCuotasPorCliente(cl.get_IDCliente());
					
					request.setAttribute("listaCuotas", lista);
					try {
						ArrayList<Cuenta> listaCt = ctNeg.obtenerCuentasPorUsuario(cl.get_IDCliente());
						request.setAttribute("listaCuentas", listaCt);
					}catch (GenericException e) {
				        e.printStackTrace();
				        request.getSession().setAttribute("error", "Hubo un error al listar las cuotas \n"+ e.getMessage());	        
				        response.sendRedirect("Inicio.jsp");
				    }
					RequestDispatcher rd = request.getRequestDispatcher("PagoDePrestamos.jsp");
					rd.forward(request, response);
				}catch (DBException e) {
			        e.printStackTrace();
			        request.getSession().setAttribute("error", "Error de base de datos. Por favor, inténtalo de nuevo más tarde."+ e.getMessage());	
			    }catch (GenericException e) {
			        e.printStackTrace();
			        request.getSession().setAttribute("error", "Hubo un error inesperado. Intente nuevamente más tarde"+ e.getMessage());	        
			        response.sendRedirect("Inicio.jsp");
			    }finally {
					
	    		}
			
		}
		else {
			response.sendRedirect("Inicio.jsp");
		}
	}

}
