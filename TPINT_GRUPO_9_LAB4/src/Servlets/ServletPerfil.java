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

import Dominio.Cliente;
import Dominio.ClienteDAO;

/**
 * Servlet implementation class ServletPerfil
 */
@WebServlet("/ServletPerfil")
public class ServletPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
					cliente.set_Direccion(request.getParameter("txtDireccion"));
					cliente.set_Localidad(request.getParameter("txtLocalidad"));
					cliente.set_Provincia(request.getParameter("txtProvincia"));
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
					ClienteDAO clientedao = new ClienteDAO();
				    try {
				    	
						int filas = clientedao.modificarUsuario(cliente);
				            
					} catch (SQLException e) {
						e.printStackTrace();
					}
				    response.sendRedirect("PerfilUsuario.jsp");
		}
	}

}
