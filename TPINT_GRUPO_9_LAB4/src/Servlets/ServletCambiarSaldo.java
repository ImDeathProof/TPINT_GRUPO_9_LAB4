package Servlets;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImpl.CuentaDAO;
import negocio.ClienteNeg;
import negocio.CuentaNeg;
import negocioImpl.ClienteNegImpl;
import negocioImpl.CuentaNegImpl;
import entidad.DBException;
import entidad.GenericException;

/**
 * Servlet implementation class ServletCambiarPass
 */
@WebServlet("/ServletCambiarSaldo")
public class ServletCambiarSaldo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CuentaNeg cuNeg = new CuentaNegImpl();
       
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
		if(request.getParameter("btnSetear")!= null) {
			String saldo = request.getParameter("txtSaldo");
			BigDecimal saldoDecimal = new BigDecimal(saldo);
			int userID = Integer.parseInt(request.getParameter("userID"));
			String TipoCuenta = request.getParameter("ddlTipoCuenta");
		
			try {
			
			cuNeg.CambiarSaldo(saldoDecimal, userID,TipoCuenta);
			
			response.sendRedirect("PanelDeControl.jsp");
			
			}catch (DBException e) {
		        e.printStackTrace();
		        request.getSession().setAttribute("error", "Error de base de datos. Por favor, inténtalo de nuevo más tarde. \n" + e.getMessage());	        
		        response.sendRedirect("PanelDeControl.jsp");
		    }catch (GenericException e) {
		        e.printStackTrace();
		        request.getSession().setAttribute("error", "Hubo un error inesperado. Intente nuevamente más tarde"+ e.getMessage());     
		        response.sendRedirect("PanelDeControl.jsp");
		    }
		}
		
		
	}

}
