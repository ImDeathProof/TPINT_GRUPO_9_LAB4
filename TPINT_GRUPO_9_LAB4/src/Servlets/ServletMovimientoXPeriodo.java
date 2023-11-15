package Servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Movimiento;
import entidad.Prestamo;
import negocio.MovimientoNeg;
import negocio.PrestamoNeg;
import negocioImpl.MovimientoNegImpl;
import negocioImpl.PrestamoNegocioImpl;

/**
 * Servlet implementation class ServletMovimientoXPeriodo
 */
@WebServlet("/ServletMovimientoXPeriodo")
public class ServletMovimientoXPeriodo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MovimientoNeg movNeg = new MovimientoNegImpl();  
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletMovimientoXPeriodo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String paginaElegida = request.getParameter("pagina");
        int numeroPagina = 1;

        if (paginaElegida != null && !paginaElegida.isEmpty()) {
            try {
                numeroPagina = Integer.parseInt(paginaElegida);
            } catch (NumberFormatException e) {
            	  throw e;
            }
        }
        
        HttpSession session = request.getSession();
        LocalDate fechaInicio = (LocalDate) session.getAttribute("fechaInicioInforme");
        LocalDate fechaFin = (LocalDate) session.getAttribute("fechaFinInforme");
        String orderBy = (String) session.getAttribute("orderByInforme");

        ArrayList<Movimiento> lista = movNeg.obtenerInformePaginado(numeroPagina, 5, fechaInicio, fechaFin, orderBy);
        request.setAttribute("listaMovimientosPeriodo", lista);
        
        request.setAttribute("cantPagsInforme", movNeg.getCantPaginas());

        RequestDispatcher rd = request.getRequestDispatcher("MovimientosBanco.jsp");
        rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 LocalDate fechaInicio = LocalDate.parse(request.getParameter("fechaInicio"));
		 LocalDate fechaFin = LocalDate.parse(request.getParameter("fechaFin"));	 
		 String orderBy = request.getParameter("ordenarPor");
		 
		 HttpSession session = request.getSession();
		 session.setAttribute("fechaInicioInforme", fechaInicio);
		 session.setAttribute("fechaFinInforme", fechaFin);
		 session.setAttribute("orderByInforme", orderBy);
		 
		 request.setAttribute("cantPagsInforme", movNeg.getCantPaginas());
		 
		 ArrayList<Movimiento> lista = movNeg.obtenerInformePaginado(1, 5, fechaInicio, fechaFin, orderBy);
			
		 request.setAttribute("listaMovimientosPeriodo", lista);
		
		 RequestDispatcher rd = request.getRequestDispatcher("/MovimientosBanco.jsp");
		 rd.forward(request, response);
		 
	}

}
