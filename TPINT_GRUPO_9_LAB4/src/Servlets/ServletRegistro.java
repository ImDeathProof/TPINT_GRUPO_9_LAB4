package Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImpl.ClienteDAO;
import entidad.Cliente;
import entidad.DBException;
import entidad.Direccion;
import entidad.Localidad;
import entidad.Provincia;
import negocio.ClienteNeg;
import negocioImpl.ClienteNegImpl;
import entidad.DBException;
import entidad.GenericException;

/**
 * Servlet implementation class ServletRegistro
 */
@WebServlet("/ServletRegistro")
public class ServletRegistro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClienteNeg clNeg = new ClienteNegImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegistro() {
        super();
//         TODO Auto-generated constructor stub
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
		
	    String usuario = request.getParameter("username");
	    
	    String contrasena = request.getParameter("password");
	    String contra2 = request.getParameter("pass2");
	    
	    if(contrasena.equals(contra2))
	    {    
	    	 if (request.getSession().getAttribute("errorRegistro") != null) {
	                request.getSession().removeAttribute("errorRegistro");
	            }

	    	 String nombre = request.getParameter("nombre");
		     String apellido = request.getParameter("apellido");
		     long dni = Long.parseLong(request.getParameter("DNI"));
	    	 long cuil = Long.parseLong(request.getParameter("CUIL"));
			    
			 String sexoValue = request.getParameter("sexo");
		
			 boolean sexo = true;  //Variable para almacenar el valor de sexo
		
			 if (sexoValue.equals("Masculino")) {
			     sexo = true;  //Si se selecciona "Masculino", establece sexo en true
		     } else if (sexoValue.equals("Femenino")) {
		         sexo = false;  //Si se selecciona "Femenino", establece sexo en false
	         } 
			    
			    
		    String nacionalidad = request.getParameter("Nacionalidad");
		    String email = request.getParameter("email");
		    long Telefono = Long.parseLong(request.getParameter("Telefono"));
		    String calle = request.getParameter("Direccion");
		    int numeroCalle = Integer.parseInt(request.getParameter("numeroDic"));
		    String Localidad = request.getParameter("Localidad");
		    String Provincia = request.getParameter("Provincia");
		    
		    Direccion dic = new Direccion(calle, numeroCalle,new Localidad(Localidad), new Provincia(Provincia));
			    
		    LocalDate fechaNacimiento = LocalDate.parse(request.getParameter("fechaNacimiento"));
			    
			  
		  	Cliente cliente = new Cliente(usuario, contrasena, nombre, apellido, dni, cuil, sexo, nacionalidad, fechaNacimiento, dic, email, Telefono);	    		      
			    	
			boolean cargo = false;
		    try {
				    	
		       cliente = clNeg.agregarUsuario(cliente);			
			   if(cliente != null) {
		     		request.getSession().setAttribute("usuarioAutenticado", cliente);
					cargo=true;
			   }
				            
		    }  catch (DBException e) {
		        e.printStackTrace();
		        request.getSession().setAttribute("error", "Error de base de datos. Por favor, inténtalo de nuevo más tarde. \n" + e.getMessage());		        
		        response.sendRedirect("Login.jsp");
		    }
		    catch (GenericException e) {
		        e.printStackTrace();
		        request.getSession().setAttribute("error", "Hubo un error inesperado. Intente nuevamente más tarde"+ e.getMessage());	        
		        response.sendRedirect("Login.jsp");
		    }
		    if(cargo) {
		    	response.sendRedirect("PerfilUsuario.jsp");
		    }else {
		    	response.sendRedirect("Inicio.jsp");		    	
		    }
			    
	    }
	    else {      
	        request.getSession().setAttribute("errorRegistro", "Las contraseñas no coinciden. Vuelve a ingresar los datos.");
	        response.sendRedirect("Registro.jsp");
	    }
	}

}
