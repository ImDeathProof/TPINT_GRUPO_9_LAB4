package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dominio.Cliente;
import Dominio.ClienteDAO;
import Dominio.Cuenta;
import Dominio.CuentaDAO;

/**
 * Servlet implementation class ServletGestionCuentas
 */
@WebServlet("/ServletGestionCuentas")
public class ServletGestionCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGestionCuentas() {
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
		
		if(request.getParameter("btnCuentas")!=null) {
			CuentaDAO cu = new CuentaDAO();
			ArrayList<Cuenta> listaCu = cu.obtenerTodasLasCuentas();
			
			request.setAttribute("listaTodasCuentas", listaCu);
			
			RequestDispatcher rd = request.getRequestDispatcher("/PanelDeControl.jsp");
			rd.forward(request, response);
		}
	}

}
