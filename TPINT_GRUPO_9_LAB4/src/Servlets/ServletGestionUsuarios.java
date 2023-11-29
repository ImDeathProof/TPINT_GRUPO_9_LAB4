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
import entidad.Cliente;
import negocio.ClienteNeg;
import negocioImpl.ClienteNegImpl;
import entidad.DBException;
import entidad.GenericException;
/**
 * Servlet implementation class ServletGestionUsuarios
 */
@WebServlet("/ServletGestionUsuarios")
public class ServletGestionUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClienteNeg clNeg = new ClienteNegImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGestionUsuarios() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try{
			String paginaElegida = request.getParameter("pagina");
	        int numeroPagina = 1;

	        if (paginaElegida != null && !paginaElegida.isEmpty()) {
	            try {
	                numeroPagina = Integer.parseInt(paginaElegida);
	            } catch (NumberFormatException e) {
	            	  throw e;
	            }
	        }
	        ArrayList<Cliente> listaUsuarios = new ArrayList<>();
	        
	        HttpSession session = request.getSession();
	        if (session.getAttribute("txtBusquedaUser") != null) {
	        	
	        	listaUsuarios = (ArrayList<Cliente>)clNeg.obtenerUsuariosPaginadosFiltrados(numeroPagina, 5, (String)session.getAttribute("txtBusquedaUser"), (String)session.getAttribute("filtroBusquedaUser"));	        	
	        	request.setAttribute("cantPags", clNeg.getCantPaginasXFiltro((String)session.getAttribute("txtBusquedaUser"), (String)session.getAttribute("filtroBusquedaUser")));
	        }
	        else
	        {
	        	listaUsuarios = clNeg.obtenerUsuariosPaginados(numeroPagina, 5);	        	
	        	request.setAttribute("cantPags", clNeg.getCantPaginas());
	        }
	        request.setAttribute("listaUsuarios", listaUsuarios);

	        RequestDispatcher rd = request.getRequestDispatcher("/PanelDeControl.jsp");
	        rd.forward(request, response);
			
		 	}
		 catch (DBException e) {
		        e.printStackTrace();
		        request.getSession().setAttribute("error", "Error de base de datos. Por favor, inténtalo de nuevo más tarde.");
		        response.sendRedirect("Login.jsp");
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
		try{
			HttpSession session = request.getSession();
			if(request.getParameter("btnUsuarios")!=null && request.getParameter("Param")==null) {
			
			session.setAttribute("txtBusquedaUser", null);
			session.setAttribute("filtroBusquedaUser", null);
			request.setAttribute("cantPags", clNeg.getCantPaginas());
			
			ArrayList<Cliente> lista = (ArrayList<Cliente>)clNeg.obtenerUsuariosPaginados(1, 5);
			
			request.setAttribute("listaUsuarios", lista);
			
			RequestDispatcher rd = request.getRequestDispatcher("/PanelDeControl.jsp");
			rd.forward(request, response);
			}
			
			if(request.getParameter("btnBuscarUsuarios") != null) {
//			if(request.getParameter("Param")!=null) {
				String textBoxBusquedaUsuario = request.getParameter("textBoxBusquedaUsuarios");
				String filtroBusquedaUsuario = request.getParameter("filtroBusquedaUsuarios");

				//prueba
				System.out.println("Contenido de textBoxBusquedaUsuario: " + textBoxBusquedaUsuario);
				System.out.println("Contenido de filtroBusquedaUsuario: " + filtroBusquedaUsuario);
								
				session.setAttribute("txtBusquedaUser", textBoxBusquedaUsuario);
				session.setAttribute("filtroBusquedaUser", filtroBusquedaUsuario);
				request.setAttribute("cantPags", clNeg.getCantPaginasXFiltro(textBoxBusquedaUsuario, filtroBusquedaUsuario));
				
				ArrayList<Cliente> lista = (ArrayList<Cliente>)clNeg.obtenerUsuariosPaginadosFiltrados(1, 5, textBoxBusquedaUsuario, filtroBusquedaUsuario);
				
				request.setAttribute("listaUsuarios", lista);
				
				//prueba
				System.out.println("Contenido de la lista:");
				for (Cliente cliente : lista) {
				    System.out.println("IDUsuario: " + cliente.get_IDCliente());
				    System.out.println("Username: " + cliente.get_Usuario());
				}
				
				RequestDispatcher rd = request.getRequestDispatcher("/PanelDeControl.jsp");
				rd.forward(request, response);
				
			}
			
	}catch (DBException e) {
        e.printStackTrace();
        request.getSession().setAttribute("error", "Error de base de datos. Por favor, inténtalo de nuevo más tarde."+ e.getMessage());	
        response.sendRedirect("PanelDeControl.jsp");
    }catch (GenericException e) {
        e.printStackTrace();
        request.getSession().setAttribute("error", "Hubo un error inesperado. Intente nuevamente más tarde"+ e.getMessage());	        
        response.sendRedirect("PanelDeControl.jsp");
    }
	}
}
