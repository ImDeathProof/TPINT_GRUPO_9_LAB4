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
import negocioImpl.ClienteNegImpl;

/**
 * Servlet implementation class ServletPerfil
 */
@WebServlet("/ServletPerfil")
public class ServletPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClienteNeg clNeg = new ClienteNegImpl();
       
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
					cliente.set_Contrasena(request.getParameter("txtContase�a"));
					cliente.set_Nombre(request.getParameter("txtNombre"));
					cliente.set_Apellido(request.getParameter("txtApellido"));
					cliente.set_DNI(Long.parseLong(request.getParameter("txtDni")));
					cliente.set_CUIL(Long.parseLong(request.getParameter("txtCuil")));
					cliente.set_Nacionalidad(request.getParameter("txtNacionalidad"));
					
					
					Direccion dic = new Direccion();				
					dic.set_Localidad(new Localidad(request.getParameter("txtLocalidad")));
					dic.set_Provincia(new Provincia(request.getParameter("txtProvincia")));
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
				    		clNeg.modificarUsuario(cliente);			
				    		request.getSession().setAttribute("usuarioModificado", "El usuario fue modificado correctamente!");
				    	}
				    	else
				    	{
				    		request.getSession().setAttribute("errorModificarUser", "El usuario ya existe.");
				    	}
				            
					} catch (DBException e) {
				        e.printStackTrace();
				        request.getSession().setAttribute("error", "Error de base de datos. Por favor, int�ntalo de nuevo m�s tarde.");
				        response.sendRedirect("Login.jsp");
				    }
				    response.sendRedirect("PerfilUsuario.jsp");
		}
	}

}
