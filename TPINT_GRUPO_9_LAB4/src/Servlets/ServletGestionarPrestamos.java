package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.PrestamoNeg;
import negocioImpl.PrestamoNegocioImpl;

/**
 * Servlet implementation class ServletGestionarPrestamos
 */
@WebServlet("/ServletGestionarPrestamos")
public class ServletGestionarPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrestamoNeg negPr = new PrestamoNegocioImpl();  
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGestionarPrestamos() {
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
		// TODO Auto-generated method stub
		doGet(request, response);
		//APROBAR O RECHAZAR PRESTAMOS
				if(request.getParameter("submitValue") != null) {
					try {
						int prID = Integer.parseInt(request.getParameter("Id_Prestamo"));
						String buttonValue = request.getParameter("submitValue");

						if (buttonValue.equals("Aprobar")) {		
							negPr.Aprobar(prID);			
					    } else if (buttonValue.equals("Rechazar")) {
					    	negPr.Rechazar(prID);
					    }
						
					}catch (Exception e) {
						e.printStackTrace();
					}
					RequestDispatcher rd = request.getRequestDispatcher("GestionPrestamos.jsp");
					rd.forward(request, response);
					
					
				}
	}

}
