package Servlets;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImpl.CuentaDAO;
import entidad.DBException;
import negocio.CuentaNeg;
import negocioImpl.CuentaNegImpl;
import entidad.GenericException;

/**
 * Servlet implementation class ServletTransferencias
 */
@WebServlet("/ServletTransferencias")
public class ServletTransferencias extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CuentaNeg cuNeg = new CuentaNegImpl();
       
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
	    String CBU = request.getParameter("CBU");
	    String monto = request.getParameter("monto");
	     
	    BigDecimal saldoDecimal = new BigDecimal(monto);
	    String tipoCuenta = request.getParameter("tipoCuenta");
	    
	    try {
	    
	    if (cuNeg.transferirDinero(saldoDecimal, idUser, CBU, tipoCuenta) == 1) {
	        // Si hay un error en la transferencia
	        request.getSession().setAttribute("errorTransfer", "No se pudo transferir el dinero. Verifica tu saldo o el estado de tu cuenta");
	        response.sendRedirect("Transferencias.jsp");
	    } else {
	        // Si la transferencia es exitosa
	        request.getSession().setAttribute("successTransfer", "Transferencia realizada exitósamente");
	        response.sendRedirect("Transferencias.jsp");
	    }
	
	}catch (DBException e) {
        e.printStackTrace();
        request.getSession().setAttribute("error", "Error de base de datos. Por favor, inténtalo de nuevo más tarde. \n" + e.getMessage());	 
        response.sendRedirect("Transferencias.jsp");
    }catch (GenericException e) {
        e.printStackTrace();
        request.getSession().setAttribute("error", "Hubo un error inesperado. Intente nuevamente más tarde"+ e.getMessage());		        
        response.sendRedirect("Transferencias.jsp");
    }


}
}
