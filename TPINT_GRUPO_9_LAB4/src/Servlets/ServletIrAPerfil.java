package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Cliente;

/**
 * Servlet implementation class ServletIrAPerfil
 */
@WebServlet("/ServletIrAPerfil")
public class ServletIrAPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletIrAPerfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Cliente cliente = (Cliente) request.getSession().getAttribute("usuarioAutenticado"); 	
		request.getSession().setAttribute("lcCliente",cliente.get_Direccion().get_Localidad());
		request.getSession().setAttribute("provincia",cliente.get_Direccion().get_Provincia());
		int a = 0;
		
		if(a == 0)
		{
			int b = 1;
		}
		response.sendRedirect("PerfilUsuario.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
