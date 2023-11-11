package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImpl.CuentaDAO;
import entidad.Cliente;
import negocio.CuentaNeg;
import negocioImpl.CuentaNegImpl;

/**
 * Servlet implementation class ServletRegistroCuenta
 */
@WebServlet("/ServletRegistroCuenta")
public class ServletRegistroCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CuentaNeg cuNeg = new CuentaNegImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegistroCuenta() {
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
		
		if(request.getSession().getAttribute("errorRegistroCuenta") != null)
		{
			request.getSession().removeAttribute("errorRegistroCuenta");     		
		}
		if(request.getSession().getAttribute("noErrorRegistroCuenta") != null)
		{
	         request.getSession().removeAttribute("noErrorRegistroCuenta");   
		}
		
		 String tipoValue = request.getParameter("tipoCuenta");
		 Cliente usuarioAutenticado = (Cliente) request.getSession().getAttribute("usuarioAutenticado");
		 
		 if(cuNeg.pedirCuenta(tipoValue, usuarioAutenticado.get_IDCliente()) != 0){
			 request.getSession().setAttribute("noErrorRegistroCuenta", "Cuenta pedida con exito.");
		 }
		 else
		 {
			 request.getSession().setAttribute("errorRegistroCuenta", "La cuenta no fue pedida, quizas ya tienes 1 cuenta de ahorro/corriente.");
		 }
		 
		 response.sendRedirect("RegistroCuenta.jsp");
	}

}
