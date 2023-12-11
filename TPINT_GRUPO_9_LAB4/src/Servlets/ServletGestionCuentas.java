package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daoImpl.ClienteDAO;
import daoImpl.CuentaDAO;
import entidad.Cliente;
import entidad.Cuenta;
import excepciones.DBException;
import excepciones.GenericException;
import negocio.CuentaNeg;
import negocioImpl.CuentaNegImpl;

/**
 * Servlet implementation class ServletGestionCuentas
 */
@WebServlet("/ServletGestionCuentas")
public class ServletGestionCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CuentaNeg cuNeg = new CuentaNegImpl();
       
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

	    	 try {   
	    			String paginaElegida = request.getParameter("paginaCuenta");
	    		      int numeroPagina = 1;
	      
			      if (paginaElegida != null && !paginaElegida.isEmpty()) {
			            try {
			                numeroPagina = Integer.parseInt(paginaElegida);
			            } catch (NumberFormatException e) {
			            	  throw e;
			            }
			        }
		
			        ArrayList<Cuenta> listaCu = new ArrayList<>();
			        
			        HttpSession session = request.getSession();
			        if (session.getAttribute("txtBusquedaCuenta") != null) {
						
			        	listaCu = (ArrayList<Cuenta>)cuNeg.obtenerCuentasPaginadasFiltradas(1, 5, (String)session.getAttribute("txtBusquedaCuenta"), (String)session.getAttribute("filtroBusquedaCuenta"),(String)session.getAttribute("filtroBusquedaCuenta2") );
			        	request.setAttribute("cantPagsCuentas", cuNeg.getCantPaginasXFiltro((String)session.getAttribute("txtBusquedaCuenta"), (String)session.getAttribute("filtroBusquedaCuenta"),(String)session.getAttribute("filtroBusquedaCuenta2")));				
			        }
			        else
			        {
			        	listaCu = cuNeg.obtenerCuentasPaginadas(numeroPagina, 5);     
			            request.setAttribute("cantPagsCuentas", cuNeg.getCantPaginas());
			        }
			        
			        request.setAttribute("listaTodasCuentas", listaCu);
			
			        RequestDispatcher rd = request.getRequestDispatcher("/PanelDeControl.jsp");
			        rd.forward(request, response);
	}
	    	 catch (DBException e) {
        e.printStackTrace();
        request.getSession().setAttribute("error", "Error de base de datos. Por favor, inténtalo de nuevo más tarde. \n" + e.getMessage());	        
        response.sendRedirect("Inicio.jsp");
    }catch (GenericException e) {
        e.printStackTrace();
        request.getSession().setAttribute("error", "Hubo un error inesperado. Intente nuevamente más tarde");	        
        response.sendRedirect("PanelDeControl.jsp");
    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			HttpSession session = request.getSession();
		if(request.getParameter("btnCuentas")!=null) {
			session.setAttribute("txtBusquedaCuenta", null);
			session.setAttribute("filtroBusquedaCuenta", null);
			session.setAttribute("filtroBusquedaCuenta2", null);
			request.setAttribute("cantPagsCuentas", cuNeg.getCantPaginas());
			
			
			ArrayList<Cuenta> listaCu = cuNeg.obtenerCuentasPaginadas(1, 5);
			
			request.setAttribute("listaTodasCuentas", listaCu);
			
			RequestDispatcher rd = request.getRequestDispatcher("/PanelDeControl.jsp");
			rd.forward(request, response);		
		}
		if(request.getParameter("btnBuscarCuenta")!=null)
		{
			String textBoxBusquedaCuenta = request.getParameter("textBoxBusquedaCuentas");
			String filtroBusquedaCuenta = request.getParameter("filtroBusquedaCuenta");
			String filtroBusquedaCuenta2 = request.getParameter("filtroBusquedaCuenta2");

			//prueba
			System.out.println("Contenido de textBoxBusquedaUsuario: " + textBoxBusquedaCuenta);
			System.out.println("Contenido de filtroBusquedaUsuario: " + filtroBusquedaCuenta);
			System.out.println("Contenido de filtroBusquedaUsuario: " + filtroBusquedaCuenta2);
							
			session.setAttribute("txtBusquedaCuenta", textBoxBusquedaCuenta);
			session.setAttribute("filtroBusquedaCuenta", filtroBusquedaCuenta);
			session.setAttribute("filtroBusquedaCuenta2", filtroBusquedaCuenta2);		
			request.setAttribute("cantPagsCuentas", cuNeg.getCantPaginasXFiltro(textBoxBusquedaCuenta, filtroBusquedaCuenta, filtroBusquedaCuenta2));
			
			ArrayList<Cuenta> lista = (ArrayList<Cuenta>)cuNeg.obtenerCuentasPaginadasFiltradas(1, 5, textBoxBusquedaCuenta, filtroBusquedaCuenta,filtroBusquedaCuenta2);
			
			request.setAttribute("listaTodasCuentas", lista);
			
			RequestDispatcher rd = request.getRequestDispatcher("/PanelDeControl.jsp");
			rd.forward(request, response);
		}
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
