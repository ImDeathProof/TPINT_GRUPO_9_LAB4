package Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImpl.ClienteDAO;
import entidad.Cliente;
import negocio.ClienteNeg;
import negocio.DireccionNeg;
import negocioImpl.ClienteNegImpl;
import negocioImpl.DireccionNegImpl;
import entidad.DBException;
import entidad.Direccion;
import entidad.GenericException;
import entidad.Localidad;
import entidad.Provincia;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	ClienteNeg clNeg = new ClienteNegImpl();
	DireccionNeg dNeg = new DireccionNegImpl();
       
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		Cliente usuarioActivo = new Cliente();
	
		    usuarioActivo.set_Usuario(request.getParameter("user"));
		    usuarioActivo.set_Contrasena(request.getParameter("password"));
	
		    try {	            
		        usuarioActivo = clNeg.BuscarUsuario(usuarioActivo);

		        if (usuarioActivo == null) {
		            request.getSession().setAttribute("error", "No se pudo conectar. Verifica tus credenciales.");
		            response.sendRedirect("Login.jsp");
		        } else if (usuarioActivo.isBloqueado()) {
		            request.getSession().setAttribute("error", "Tu cuenta está bloqueada.");
		            response.sendRedirect("Login.jsp");
		        } else {
		            request.getSession().setAttribute("usuarioAutenticado", usuarioActivo);
		            request.getSession().setAttribute("localidades",dNeg.getAllLocalidades(usuarioActivo.get_Direccion().get_Provincia().getIdProvincia()));
					LocalDate fechaNacimiento = LocalDate.of(1, 1, 1);
					request.getSession().setAttribute("registro",new Cliente("X","X","X","X",0,0,false,"X",fechaNacimiento,new Direccion(),"X@gmail.com",0));	

		            if (request.getSession().getAttribute("error") != null) {
		                request.getSession().removeAttribute("error");
		            }

		            response.sendRedirect("ServletIrAPerfil");
		        }
		    } catch (DBException e) {
		        e.printStackTrace();
		        request.getSession().setAttribute("error", "Error de base de datos. Por favor, inténtalo de nuevo más tarde. \n" + e.getMessage());
		        
		        response.sendRedirect("Login.jsp");
		    }catch (GenericException e) {
		        e.printStackTrace();
		        request.getSession().setAttribute("error", "Hubo un error inesperado. Intente nuevamente más tarde"+ e.getMessage());	        
		        response.sendRedirect("Login.jsp");
		    }
		
		
	}


}
