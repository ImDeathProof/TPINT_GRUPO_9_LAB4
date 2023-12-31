package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.CuentaNeg;
import negocioImpl.CuentaNegImpl;
import excepciones.DBException;
import excepciones.GenericException;
import excepciones.ValidateException;

/**
 * Servlet implementation class ServletAprovarCuenta
 */
@WebServlet("/ServletAprobarCuenta")
public class ServletAprobarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CuentaNeg cuNeg = new CuentaNegImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAprobarCuenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("submitValueEstado")!=null) {
			//LIMPIAR MENSAJES
			if (request.getSession().getAttribute("accionCuenta") != null) {
				  		request.getSession().removeAttribute("accionCuenta");
			}
			if (request.getSession().getAttribute("error") != null) {
				request.getSession().removeAttribute("error");
			}
			
			int cuentaID = Integer.parseInt(request.getParameter("cuentaID"));
			String buttonValueEstado = request.getParameter("submitValueEstado");
	
	
			try {
					if (buttonValueEstado.equals("Validar")) {		
						cuNeg.ValidarCuenta(cuentaID);
				    	request.getSession().setAttribute("accionCuenta", "La cuenta ID:"+ cuentaID + " fue validada correctamente.");	
				    } else if (buttonValueEstado.equals("Bloquear")) {
				    	cuNeg.BloquearCuenta(cuentaID);
				    	request.getSession().setAttribute("accionCuenta", "La cuenta ID:"+ cuentaID + " fue bloqueada correctamente.");	
				    }
					
					response.sendRedirect("PanelDeControl.jsp");
				
				}
			
			catch (ValidateException e) {
		        e.printStackTrace();
		        request.getSession().setAttribute("error", "Error al validar datos en la DB \n");	        
		        return;
		    }
			
			catch (DBException e) {
		        e.printStackTrace();
		        request.getSession().setAttribute("error", "Error de base de datos. Por favor, int�ntalo de nuevo m�s tarde. \n" + e.getMessage());	        
		        return;
		    }
			catch (GenericException e) {
		        e.printStackTrace();
		        request.getSession().setAttribute("error", "Hubo un error inesperado. Intente nuevamente m�s tarde.\n " + e.getMessage());	          
		        return;
		    }
			
			
		}

	}
}
