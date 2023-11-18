package Servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cuota;
import entidad.DBException;
import negocio.CuotaNeg;
import negocioImpl.CuotaNegImpl;

/**
 * Servlet implementation class ServletPagarCuota
 */
@WebServlet("/ServletPagarCuota")
public class ServletPagarCuota extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CuotaNeg negCt = new CuotaNegImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPagarCuota() {
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
		if(request.getParameter("PagarCuota")!= null) {
			try {
				Cuota ct = negCt.obtenerCuotaPorID(Integer.parseInt(request.getParameter("IDCuota")));
				negCt.Pagar(ct, ct.getIDPrestamo(), ct.getIDUsuario(), ct.getIDCuenta());
			} catch (SQLException | NumberFormatException | DBException e) {
				e.printStackTrace();
				request.setAttribute("error", "Error al procesar el pago de la cuota.");
			}finally {
		        RequestDispatcher rd = request.getRequestDispatcher("PagoDePrestamos.jsp");
		        rd.forward(request, response);
			}
		}
	}

}
