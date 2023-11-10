package Servlets;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dominio.CuentaDAO;

/**
 * Servlet implementation class ServletTransferencias
 */
@WebServlet("/ServletTransferencias")
public class ServletTransferencias extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTransferencias() {
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
		
		  int idUser = Integer.parseInt(request.getParameter("userID"));
		  long CBU = Long.parseLong(request.getParameter("CBU"));
		  String monto = request.getParameter("monto");
	     
		  BigDecimal saldoDecimal = new BigDecimal(monto);
	      String tipoCuenta = request.getParameter("tipoCuenta");
	      
	      
	      CuentaDAO cu = new CuentaDAO();
	      if (cu.transferirDinero(saldoDecimal, idUser, CBU, tipoCuenta) == 1) {
	    	    request.getSession().setAttribute("errorTransfer", "No se pudo transferir el dinero. Verifica tu saldo");
	    	}
	      
	      response.sendRedirect("Transferencias.jsp");
	}

}
