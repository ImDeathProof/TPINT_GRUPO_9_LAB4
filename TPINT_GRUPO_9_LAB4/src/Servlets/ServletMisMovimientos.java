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
import negocio.MovimientoNeg;
import negocioImpl.MovimientoNegImpl;

/**
 * Servlet implementation class ServletMisMovimientos
 */
@WebServlet("/ServletMisMovimientos")
public class ServletMisMovimientos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MovimientoNeg movNeg = new MovimientoNegImpl();  
	

    public ServletMisMovimientos() {
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
        int idCuenta = (int) session.getAttribute("idCliente");         
        
        ArrayList<Movimiento> lista = movNeg.obtenerInformePaginado(numeroPagina, 5,idCuenta);
        request.setAttribute("misMovimientos", lista);     
        
        request.setAttribute("cantPagsInforme", movNeg.getCantPaginas(idCuenta));

        RequestDispatcher rd = request.getRequestDispatcher("MisCuentas.jsp");
        rd.forward(request, response);      
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 int idCuenta = Integer.parseInt(request.getParameter("idCliente"));
		 request.setAttribute("cantPagsMisMovimientos", movNeg.getCantPaginas(idCuenta));	
		 
		 HttpSession session = request.getSession();
		 session.setAttribute("idCuenta", idCuenta);
		 
		 request.setAttribute("cantPagsMisMovimientos", movNeg.getCantPaginas(idCuenta));
		 
		 ArrayList<Movimiento> lista = movNeg.obtenerInformePaginado(1, 5,idCuenta );
			
		 request.setAttribute("misMovimientos", lista);
		
		 RequestDispatcher rd = request.getRequestDispatcher("/MisCuentas.jsp");
		 rd.forward(request, response);
		 
	}

}