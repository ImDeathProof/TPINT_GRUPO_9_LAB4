package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cuota;
import entidad.DBException;
import entidad.Prestamo;
import negocio.CuotaNeg;
import negocio.PrestamoNeg;
import negocioImpl.CuotaNegImpl;
import negocioImpl.PrestamoNegocioImpl;
import entidad.GenericException;

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
	    // APROBAR O RECHAZAR PRESTAMOS
	    try {
	        if (request.getParameter("submitValue") != null) {
	            int prID = Integer.parseInt(request.getParameter("Id_Prestamo"));
	            String buttonValue = request.getParameter("submitValue");
	            Prestamo pr = negPr.ObtenerUno(prID);

	            if (buttonValue.equals("Aprobar")) {
	                try {

	                    CuotaNeg negCt = new CuotaNegImpl();
	                    negPr.Aprobar(pr);
	                    for (int i = 0; i < pr.getCantidadCuotas(); i++) {
	                        Cuota ct = negCt.generarCuota(pr, prID, i + 1);
	                        negCt.Agregar(ct);
	                    }
	                } catch (GenericException e) {
	                    e.printStackTrace();
	                    request.getSession().setAttribute("error", "Hubo un error inesperado al aprobar el préstamo. Intente nuevamente más tarde");
	                    response.sendRedirect("PanelDeControl.jsp");
	                    return; 
	                }
	            } else if (buttonValue.equals("Rechazar")) {
	                negPr.Rechazar(prID);
	            }
	        }
	    }catch (DBException e) {
	        e.printStackTrace();
	        request.getSession().setAttribute("error", "Error de base de datos. Por favor, inténtalo de nuevo más tarde. \n" + e.getMessage());
	        response.sendRedirect("PanelDeControl.jsp");
	        return; 
	    }catch (GenericException e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Hubo un error inesperado al aprobar el préstamo. Intente nuevamente más tarde"+ e.getMessage());	
            response.sendRedirect("PanelDeControl.jsp");
            return; 
        }

	    RequestDispatcher rd = request.getRequestDispatcher("GestionPrestamos.jsp");
	    rd.forward(request, response);
	}
}



