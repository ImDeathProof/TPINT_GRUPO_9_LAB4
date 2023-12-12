package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excepciones.GenericException;

/**
 * Servlet implementation class ServletCerrarSesion
 */
@WebServlet("/ServletCerrarSesion")
public class ServletCerrarSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCerrarSesion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    
		//LIMPIAR MENSAJES
		request.getSession().removeAttribute("error");
		request.getSession().removeAttribute("errorGsPrestamos");
		request.getSession().removeAttribute("exitoGsPrestamos");
		request.getSession().removeAttribute("ErrorGrafico");
		request.getSession().removeAttribute("PagoExitoso");
		request.getSession().removeAttribute("errorCuenta");
		request.getSession().removeAttribute("PrestamoExitoso");
		request.getSession().removeAttribute("errorAlSolicitar");
		request.getSession().removeAttribute("errorCarga");
		request.getSession().removeAttribute("seRegistro");
		request.getSession().removeAttribute("errorTransfer");
		request.getSession().removeAttribute("errorCuenta");
		request.getSession().removeAttribute("successTransfer");
		request.getSession().removeAttribute("accion");
		request.getSession().removeAttribute("accionCuenta");
		

		request.getSession().invalidate();
		response.sendRedirect("Inicio.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
