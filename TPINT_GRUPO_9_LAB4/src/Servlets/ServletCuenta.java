package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImpl.CuentaDAO;
import entidad.Cliente;
import entidad.Cuenta;
import excepciones.DBException;
import excepciones.GenericException;
import negocio.CuentaNeg;
import negocioImpl.CuentaNegImpl;

@WebServlet("/ServletCuenta")
public class ServletCuenta extends HttpServlet {
    private static final long serialVersionUID = 1L;
    CuentaNeg cuNeg = new CuentaNegImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//LIMPIAR MENSAJES
    	request.getSession().removeAttribute("error");

        Cliente usuarioActivo = (Cliente) request.getSession().getAttribute("usuarioAutenticado");

        
        try {
        
        if (usuarioActivo != null) {
            
            ArrayList<Cuenta> cuentas = cuNeg.obtenerCuentasPorUsuario(usuarioActivo.get_IDCliente());
           
            request.setAttribute("cuentas", cuentas);
           
            request.getRequestDispatcher("MisCuentas.jsp").forward(request, response);
        } else {
          
            response.sendRedirect("Inicio.jsp");
        }
        
        }catch (DBException e) {
	        e.printStackTrace();
	        request.getSession().setAttribute("error", "Error de base de datos. Por favor, inténtalo de nuevo más tarde. \n" + e.getMessage());	        
	        response.sendRedirect("Inicio.jsp");
	    }catch (GenericException e) {
	        e.printStackTrace();
	        request.getSession().setAttribute("error", "Hubo un error inesperado. Intente nuevamente más tarde"+ e.getMessage());        
	        response.sendRedirect("Inicio.jsp");
	    }
        
       
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    	
    	
    }
}

