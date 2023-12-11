package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImpl.ClienteDAO;
import excepciones.DBException;
import excepciones.GenericException;
import negocio.ClienteNeg;
import negocio.CuentaNeg;
import negocioImpl.ClienteNegImpl;
import negocioImpl.CuentaNegImpl;

/**
 * Servlet implementation class ServletCambiarPass
 */
@WebServlet("/ServletCambiarPass")
public class ServletCambiarPass extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClienteNeg clNeg = new ClienteNegImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCambiarPass() {
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
		
		try{
			String pass = request.getParameter("txtContaseña");
		
		int userID = Integer.parseInt(request.getParameter("userID"));
				
		clNeg.CambiarPass(pass, userID);
		
		response.sendRedirect("PanelDeControl.jsp");
		
	}
		catch (DBException e) {
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
