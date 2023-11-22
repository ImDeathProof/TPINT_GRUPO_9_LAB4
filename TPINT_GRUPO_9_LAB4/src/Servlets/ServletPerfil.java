package Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daoImpl.ClienteDAO;
import entidad.Cliente;
import entidad.DBException;
import entidad.Direccion;
import entidad.Localidad;
import entidad.Provincia;
import negocio.ClienteNeg;
import negocio.DireccionNeg;
import negocioImpl.ClienteNegImpl;
import negocioImpl.DireccionNegImpl;
import entidad.ValidateException;
import entidad.GenericException;

/**
 * Servlet implementation class ServletPerfil
 */
@WebServlet("/ServletPerfil")
public class ServletPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClienteNeg clNeg = new ClienteNegImpl();
	DireccionNeg dNeg = new DireccionNegImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPerfil() {
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
		
		if(request.getParameter("btnModificar")!=null) {
					request.getSession().removeAttribute("usuarioModificado");
					request.getSession().removeAttribute("errorModificarUser");
			
					Cliente cliente = new Cliente();
					HttpSession session = request.getSession();
					Cliente clienteviejo = (Cliente)session.getAttribute("usuarioAutenticado");
					
					cliente.set_IDCliente(clienteviejo.get_IDCliente());
					cliente.set_Usuario(request.getParameter("txtUsuario"));
					cliente.set_Contrasena(request.getParameter("txtContaseña"));
					cliente.set_Nombre(request.getParameter("txtNombre"));
					cliente.set_Apellido(request.getParameter("txtApellido"));
					cliente.set_DNI(Long.parseLong(request.getParameter("txtDni")));
					cliente.set_CUIL(Long.parseLong(request.getParameter("txtCuil")));
					cliente.set_Nacionalidad(request.getParameter("txtNacionalidad"));

					String loc = request.getParameter("Localidad");
					String pro = request.getParameter("Provincia");
	
					Direccion dic = new Direccion();					
					dic.setId(clienteviejo.get_Direccion().getId());
					
					try {
						dic.set_Localidad(dNeg.obtenerLocalidadPorDesc(loc));
					} catch (DBException e1) {
						e1.printStackTrace();
					} catch (GenericException e1) {
						e1.printStackTrace();
					}
					
					try {
						dic.set_Provincia(dNeg.obtenerProvinciaPorDesc(pro));
					} catch (DBException e1) {
						e1.printStackTrace();
					} catch (GenericException e1) {
						e1.printStackTrace();
					}
					
					dic.setCalle(request.getParameter("txtDireccion"));
					dic.setNumero(Integer.parseInt(request.getParameter("txtNum")));

					cliente.set_Direccion(dic);					
					
					cliente.set_Telefono(Long.parseLong(request.getParameter("txtTelefono")));
					cliente.set_Email(request.getParameter("txtEmail"));
					cliente.set_FechaNacimiento(LocalDate.parse(request.getParameter("txtFNacimiento")));
					
					String selectedSex = request.getParameter("sexo");
					if ("Masculino".equals(selectedSex)) {
					    cliente.set_Sexo(true);  
					} else if ("Femenino".equals(selectedSex)) {
					    cliente.set_Sexo(false);  
					}
			
					request.getSession().setAttribute("usuarioAutenticado", cliente);
				    					
					try {
				    	if(!clNeg.usuarioExistente(cliente.get_Usuario(), clienteviejo.get_IDCliente()))
				    	{
				    		dNeg.modificarDireccion(cliente.get_Direccion());
				    		clNeg.modificarUsuario(cliente);			
				    		request.getSession().setAttribute("usuarioModificado", "El usuario fue modificado correctamente!");
				    	}
				    	else
				    	{
				    		request.getSession().setAttribute("errorModificarUser", "El usuario ya existe.");
				    	}
				            
					} catch (DBException e) {
				        e.printStackTrace();
				        request.getSession().setAttribute("error", "Error de base de datos. Por favor, inténtalo de nuevo más tarde. \n" + e.getMessage());	 
				        response.sendRedirect("Login.jsp");
				    }
					catch (ValidateException e) {
				        e.printStackTrace();
				        request.getSession().setAttribute("error", "Error al validar datos de la DB");
				        response.sendRedirect("Inicio.jsp");
				    }
					catch (GenericException e) {
				        e.printStackTrace();
				        request.getSession().setAttribute("error", "Hubo un error inesperado. Intente nuevamente más tarde"+ e.getMessage());	        
				        response.sendRedirect("Inicio.jsp");
				    }
				    response.sendRedirect("PerfilUsuario.jsp");
		}
	}

}
