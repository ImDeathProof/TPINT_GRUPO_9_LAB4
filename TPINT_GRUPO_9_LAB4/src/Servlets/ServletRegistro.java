package Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dominio.Cliente;
import Dominio.ClienteDAO;

/**
 * Servlet implementation class ServletRegistro
 */
@WebServlet("/ServletRegistro")
public class ServletRegistro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegistro() {
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
		
	    String usuario = request.getParameter("username");
	    
	    String contrasena = request.getParameter("password");
	    String contra2 = request.getParameter("pass2");

	    String nombre = request.getParameter("nombre");
	    String apellido = request.getParameter("apellido");
	    long dni = Long.parseLong(request.getParameter("DNI"));
	    long cuil = Long.parseLong(request.getParameter("CUIL"));
	    
	    String sexoValue = request.getParameter("sexo");

	    boolean sexo = true; // Variable para almacenar el valor de sexo

	    if (sexoValue.equals("Masculino")) {
	        sexo = true; // Si se selecciona "Masculino", establece sexo en true
	    } else if (sexoValue.equals("Femenino")) {
	        sexo = false; // Si se selecciona "Femenino", establece sexo en false
	    } 
	    
	    
	    String nacionalidad = request.getParameter("Nacionalidad");
	    String email = request.getParameter("email");
	    long Telefono = Long.parseLong(request.getParameter("Telefono"));
	    String Direccion = request.getParameter("Direccion");
	    String Localidad = request.getParameter("Localidad");
	    String Provincia = request.getParameter("Provincia");
	    
	    LocalDate fechaNacimiento = LocalDate.parse(request.getParameter("fechaNacimiento"));
	    
	  
	    	Cliente cliente = new Cliente(usuario, contrasena, nombre, apellido, dni, cuil, sexo, nacionalidad, fechaNacimiento, Direccion, Localidad, Provincia, email, Telefono);	    	
	      
	    	//ACA HAY QUE HACER EL CHECK DE SI LAS CONTRAS SON IGUALES
	    	boolean cargo = false;
		    ClienteDAO agregadorClientes = new ClienteDAO();
		    try {
		    	
				int filas = agregadorClientes.agregarUsuario(cliente);			
				if(filas > 0) {
					request.getSession().setAttribute("usuarioAutenticado", cliente);
					cargo=true;
				}
		            
			} catch (SQLException e) {
				e.printStackTrace();
			}
		    if(cargo) {
		    	response.sendRedirect("PerfilUsuario.jsp");
		    }else {
		    	response.sendRedirect("Inicio.jsp");		    	
		    }
	    
		
	}

}
