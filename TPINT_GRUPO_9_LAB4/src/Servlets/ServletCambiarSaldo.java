package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Dominio.CuentaDAO;

/**
 * Servlet implementation class ServletCambiarPass
 */
@WebServlet("/ServletCambiarSaldo")
public class ServletCambiarSaldo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCambiarSaldo() {
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
		
		String saldo = request.getParameter("txtSaldo");
		int userID = Integer.parseInt(request.getParameter("userID"));
		String TipoCuenta = request.getParameter("txtTipoCuenta");
		
		CuentaDAO cu = new CuentaDAO();		
		cu.CambiarSaldo(saldo, userID,TipoCuenta);
		
		response.sendRedirect("PanelDeControl.jsp");
		
	}

}
