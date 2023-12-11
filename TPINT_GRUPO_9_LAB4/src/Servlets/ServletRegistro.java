package Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImpl.ClienteDAO;
import entidad.Cliente;
import entidad.Direccion;
import entidad.Localidad;
import entidad.Provincia;
import excepciones.DBException;
import excepciones.GenericException;
import negocio.ClienteNeg;
import negocio.DireccionNeg;
import negocioImpl.ClienteNegImpl;
import negocioImpl.DireccionNegImpl;

/**
 * Servlet implementation class ServletRegistro
 */
@WebServlet("/ServletRegistro")
public class ServletRegistro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClienteNeg clNeg = new ClienteNegImpl();
	DireccionNeg dNeg = new DireccionNegImpl();
       
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
		request.getSession().removeAttribute("seRegistro");
		request.getSession().removeAttribute("errorRegistro");
		if(request.getParameter("Param")!=null) {
			try{
					LocalDate fechaNacimiento = LocalDate.of(1, 1, 1);
					request.getSession().setAttribute("registro",new Cliente("X","X","X","X",0,0,false,"X",fechaNacimiento,new Direccion(),"X@gmail.com",0));		
				    
					ArrayList<Provincia> provincias = clNeg.obtenerProvincias();   
					request.setAttribute("provincias", provincias);
				    ArrayList<Localidad> loc = dNeg.getAllLocalidades(1);
				    request.setAttribute("localidades", loc);

				    RequestDispatcher dispatcher = request.getRequestDispatcher("Registro.jsp");
				    dispatcher.forward(request, response);	
			
				}catch (DBException e) {
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
		
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	  	if (request.getSession().getAttribute("errorRegistro") != null) {
	  		request.getSession().removeAttribute("errorRegistro");
	  	}
		
			String usuario = request.getParameter("username");
		    
		    String contrasena = request.getParameter("password");
		    String contra2 = request.getParameter("pass2");	   
		    
		    String nombre = request.getParameter("nombre");
		     String apellido = request.getParameter("apellido");
		     long dni = Long.parseLong(request.getParameter("DNI"));
	    	 long cuil = Long.parseLong(request.getParameter("CUIL"));
			    
			 String sexoValue = request.getParameter("sexo");
		
			 boolean sexo = true; 
		
			 if (sexoValue.equals("Masculino")) {
			     sexo = true; 
		     } else if (sexoValue.equals("Femenino")) {
		         sexo = false;
	         } 
			    
			    
		    String nacionalidad = request.getParameter("Nacionalidad");
		    String email = request.getParameter("email");
		    long Telefono = Long.parseLong(request.getParameter("Telefono"));
		    
		    
		  //DIRECCION
			
			Direccion dic = new Direccion();	
			
			String Localidad = request.getParameter("Localidad");
			int locId = Integer.parseInt(Localidad);
			
			String Provincia = request.getParameter("Provincia");
			int provId = Integer.parseInt(Provincia);
			
			try {
				dic.set_Provincia(dNeg.obtenerProvinciaPorID(provId));
				dic.set_Localidad(dNeg.obtenerLocalidadPorID(locId));
			} catch (DBException | GenericException e2) {
				e2.printStackTrace();
			}

			dic.setCalle(request.getParameter("Direccion"));
			dic.setNumero(Integer.parseInt(request.getParameter("numeroDic")));
			
				
			///////////////////////////////////	    
			    
		    LocalDate fechaNacimiento = LocalDate.parse(request.getParameter("fechaNacimiento"));			    
			  
		  	Cliente cliente = new Cliente(usuario, contrasena, nombre, apellido, dni, cuil, sexo, nacionalidad, fechaNacimiento, dic, email, Telefono);	 	    
		    

		    if(request.getParameter("btnRegistrar")!=null) 
		    {	    
			    try {				
					   if(!contrasena.equals(contra2))
					   {
						   request.getSession().setAttribute("registro",cliente);
						   request.getSession().setAttribute("errorRegistro", "Las contraseñas no coinciden. Vuelve a ingresar los datos.");
						   RequestDispatcher dispatcher = request.getRequestDispatcher("Registro.jsp");
						   dispatcher.forward(request, response);
					   } else
					   {
						   dic = dNeg.addDireccion(dic); 
						   if(dic != null)
						   {
							   cliente = clNeg.agregarUsuario(cliente);
							   if(cliente != null)
							   {
								   LocalDate fc = LocalDate.of(1, 1, 1);
								   request.getSession().setAttribute("registro",new Cliente("X","X","X","X",0,0,false,"X",fc,new Direccion(),"X@gmail.com",0));		
								   request.getSession().setAttribute("seRegistro", "Usuario cargado con éxito!");							   
							   }
							   else 
							   {
								   request.getSession().setAttribute("errorRegistro", "Usuario no cargado. Chequee los datos");								   
							   }	 
						   }					   
					   }		   				   				            
			    }
			    catch (DBException | GenericException e) {
			    	e.printStackTrace();
			        request.getSession().setAttribute("errorRegistro", "Hubo un error inesperado. Intente nuevamente más tarde" + e.getMessage());
			        response.sendRedirect("Login.jsp");
			    } finally {
			        request.getRequestDispatcher("Registro.jsp").forward(request, response);
			    }		    
		    }
		
			else
			{
		      request.getSession().setAttribute("registro",cliente);
		      
			  String provinciaId = request.getParameter("Provincia");
		      int provID = Integer.parseInt(provinciaId);

		        ArrayList<Localidad> localidades = new ArrayList<>();
				try {
					localidades = dNeg.getAllLocalidades(provID);
				} catch (DBException | GenericException e) {
					e.printStackTrace();
				}
				
				request.getSession().setAttribute("lcCliente",localidades.get(0));	
				localidades.remove(0);
				request.setAttribute("localidades", localidades);
				

				try {
					request.getSession().setAttribute("provincia", dNeg.obtenerProvinciaPorID(provID));
				} catch (DBException | GenericException e) {
					e.printStackTrace();
				}
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("Registro.jsp");
				dispatcher.forward(request, response);
		}
	}
	

}
