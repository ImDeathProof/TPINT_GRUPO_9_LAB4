package Servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dominio.Cliente;
import Dominio.ClienteDAO;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		    Cliente usuarioActivo = new Cliente();
	
		    usuarioActivo.set_Usuario(request.getParameter("user"));
		    usuarioActivo.set_Contrasena(request.getParameter("password"));
	
		    ClienteDAO cl = new ClienteDAO();
	
		    try {
		        if (cl.BuscarUsuario(usuarioActivo) == null) {
		        	
		        	request.getSession().setAttribute("error", "No se pudo conectar. Verifica tus credenciales.");
		        	response.sendRedirect("Login.jsp");
		            
		        } else {   	
		            usuarioActivo = cl.BuscarUsuario(usuarioActivo);
		            request.getSession().setAttribute("usuarioAutenticado", usuarioActivo);
		            
		            if (request.getSession().getAttribute("error") != null) {
		                request.getSession().removeAttribute("error");
		            }
		            
		            response.sendRedirect("PerfilUsuario.jsp");
		        }
	
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		
		
	}


}
