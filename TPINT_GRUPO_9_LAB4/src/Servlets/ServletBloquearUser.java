package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImpl.ClienteDAO;
import negocio.ClienteNeg;
import negocioImpl.ClienteNegImpl;
import excepciones.DBException;
import excepciones.GenericException;

/**
 * Servlet implementation class ServletBloquearUser
 */
@WebServlet("/ServletBloquearUser")
public class ServletBloquearUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClienteNeg clNeg = new ClienteNegImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletBloquearUser() {
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
		
		if(request.getParameter("submitValue")!=null) {
			//LIMPIAR MENSAJES
			if (request.getSession().getAttribute("error") != null) {
				  		request.getSession().removeAttribute("error");
			}
			if (request.getSession().getAttribute("accion") != null) {
				request.getSession().removeAttribute("accion");
			}
			
			
			int userID = Integer.parseInt(request.getParameter("userID"));	
			String buttonValue = request.getParameter("submitValue");
			
			try{
				if (buttonValue.equals("Bloquear")) {		
					clNeg.BloquearCliente(userID);	
					request.getSession().setAttribute("accion", "El usuario con ID:" + userID + " fue bloqueado exitosamente.");
				} else if (buttonValue.equals("Desbloquear")) {
					clNeg.DesbloquearCliente(userID);
					request.getSession().setAttribute("accion", "El usuario con ID:" + userID + " fue desbloqueado exitosamente.");
				}
				response.sendRedirect("PanelDeControl.jsp");
			}
			
			catch (DBException e) {
				e.printStackTrace();
				request.getSession().setAttribute("error", "Error de base de datos. Por favor, inténtalo de nuevo más tarde." + e.getMessage());
				return;
			}catch (GenericException e) {
				e.printStackTrace();
				request.getSession().setAttribute("error", "Hubo un error inesperado. Intente nuevamente más tarde"+ e.getMessage());    
				return;
			}
		}
	}

}
