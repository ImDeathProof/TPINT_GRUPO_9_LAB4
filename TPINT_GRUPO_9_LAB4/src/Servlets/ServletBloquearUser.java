package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dominio.ClienteDAO;

/**
 * Servlet implementation class ServletBloquearUser
 */
@WebServlet("/ServletBloquearUser")
public class ServletBloquearUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		
		int userID = Integer.parseInt(request.getParameter("userID"));
		String buttonValue = request.getParameter("submitValue");
		
		ClienteDAO cl = new ClienteDAO();	

		if (buttonValue.equals("Bloquear")) {		
			cl.BloquearCliente(userID);			
	    } else if (buttonValue.equals("Desbloquear")) {
	    	cl.DesbloquearCliente(userID);
	    }
		
		response.sendRedirect("PanelDeControl.jsp");
		
	}

}
