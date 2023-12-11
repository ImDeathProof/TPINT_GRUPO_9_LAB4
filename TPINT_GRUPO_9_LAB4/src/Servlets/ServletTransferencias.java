package Servlets;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImpl.CuentaDAO;
import entidad.Comprobante;
import entidad.Cliente;
import entidad.Cuenta;
import negocio.ClienteNeg;
import negocio.CuentaNeg;
import negocio.PrestamoNeg;
import negocioImpl.ClienteNegImpl;
import negocioImpl.CuentaNegImpl;
import negocioImpl.PrestamoNegocioImpl;
import excepciones.DBException;
import excepciones.GenericException;
import excepciones.ValidateException;

/**
 * Servlet implementation class ServletTransferencias
 */
@WebServlet("/ServletTransferencias")
public class ServletTransferencias extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CuentaNeg cuNeg = new CuentaNegImpl();
	PrestamoNeg negPr = new PrestamoNegocioImpl();  
	ClienteNeg clNeg = new ClienteNegImpl();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTransferencias() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		if(request.getParameter("Param")!=null) {
			if(request.getSession().getAttribute("error") != null) {
				request.getSession().removeAttribute("error");
			}
			Cliente cl = (Cliente) request.getSession().getAttribute("usuarioAutenticado");
			try {
				ArrayList<Cuenta> listaCt = cuNeg.obtenerCuentasPorUsuario(cl.get_IDCliente());
				if(!listaCt.isEmpty() && listaCt != null) {
					request.setAttribute("listaCuentas", listaCt);
				}else {
					request.getSession().setAttribute("errorCuenta", "Usted no posee cuentas, por favor solicite una e intente mas tarde.");
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher("Transferencias.jsp");
				dispatcher.forward(request, response);	
			}catch (GenericException e) {
		        e.printStackTrace();
		        request.getSession().setAttribute("errorCuenta", "Hubo un error al listar las cuentas \n"+ e.getMessage());	        
		        response.sendRedirect(request.getContextPath() + "/Transferencias.jsp");
		    } catch (DBException e) {
		    	 e.printStackTrace();
		    	 request.getSession().setAttribute("errorCuenta", "Hubo un error de base de datos al listar las cuentas \n" + e.getMessage());
		    	 response.sendRedirect(request.getContextPath() + "/Transferencias.jsp");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("error") != null) {
			request.getSession().removeAttribute("error");
		}else if(request.getSession().getAttribute("errorTransfer") != null) {
			request.getSession().removeAttribute("errorTransfer");
		}else if(request.getSession().getAttribute("errorCuenta") != null) {
			request.getSession().removeAttribute("errorCuenta");
		}else if(request.getSession().getAttribute("successTransfer") != null) {
			request.getSession().removeAttribute("successTransfer");
		}
		int idUser = Integer.parseInt(request.getParameter("userID"));
		Cliente cliente;
		try {
			cliente = clNeg.BuscarClientePorID(idUser);
		} catch (DBException | GenericException e2) {
			e2.printStackTrace();
			request.getSession().setAttribute("errorTransfer", "Hubo un error al buscar el cliente");
	        response.sendRedirect(request.getContextPath() + "/ServletTransferencias?Param=1");
	        return;
		} 
	    String CBU = request.getParameter("CBU");
	    
		try {
			if(!cuNeg.existeCBU(Integer.parseInt(CBU))) {
				request.getSession().setAttribute("errorTransfer", "El CBU no existe.");
			    response.sendRedirect(request.getContextPath() + "/ServletTransferencias?Param=1");
			    return;
			}
		} catch (NumberFormatException | ValidateException | GenericException e1) {
			e1.printStackTrace();
			request.getSession().setAttribute("errorTransfer", "Hubo un error al revisar el CBU, intente de nuevo mas tarde.");
	        response.sendRedirect(request.getContextPath() + "/ServletTransferencias?Param=1");
	        return;
		}
		
	    String monto = request.getParameter("monto");
	     
	    BigDecimal saldoDecimal = new BigDecimal(monto);
	    Cuenta cuenta = new Cuenta();
	    
	    if(request.getParameter("SelectCuentas") != null && !request.getParameter("SelectCuentas").isEmpty() && !"Seleccionar".equals(request.getParameter("SelectCuentas"))) {
			try {
		        cuenta = cuNeg.obtenerCuentaPorID(Integer.parseInt(request.getParameter("SelectCuentas")));
		        if(cuenta.getSaldo().compareTo(new BigDecimal(monto)) < 0) {
		        	request.getSession().setAttribute("errorTransfer", "Usted no dispone de saldo suficiente, seleccione otra cuenta o deposite dinero en la sucursal.");
			        response.sendRedirect(request.getContextPath() + "/ServletTransferencias?Param=1");
			        return;
		        }
			} catch (NumberFormatException e) {
				e.printStackTrace();
		        request.getSession().setAttribute("errorTransfer", "Seleccione una cuenta válida.");
		        response.sendRedirect(request.getContextPath() + "/ServletTransferencias?Param=1");
		        return;
		    } catch (DBException e) {
		    	e.printStackTrace();
		    	request.getSession().setAttribute("errorTransfer", "Hubo un error con la Base de Datos.");
		        response.sendRedirect(request.getContextPath() + "/ServletTransferencias?Param=1");
		        return;
			} catch (GenericException e) {
				e.printStackTrace();
				request.getSession().setAttribute("errorTransfer", "Hubo un error inesperado. Intente de nuevo mas tarde");
		        response.sendRedirect(request.getContextPath() + "/ServletTransferencias?Param=1");
		        return;
			}
		}else {
			request.getSession().setAttribute("errorTransfer", "Seleccione una cuenta.");
			response.sendRedirect(request.getContextPath() + "/ServletTransferencias?Param=1");
			return;
		}
	    
	    try {
	    
		    if (cuNeg.transferirDinero(saldoDecimal, idUser, CBU, cuenta.getIdCuenta()) == 1) {
		        // Si hay un error en la transferencia
		        request.getSession().setAttribute("errorTransfer", "No se pudo transferir el dinero. Verifica tu saldo o el estado de tu cuenta");
		        response.sendRedirect(request.getContextPath() + "/ServletTransferencias?Param=1");
		        return;
		    } else {
		    	// Si la transferencia es exitosa
		        //	Generar comprobante (TEST)
		        Comprobante comprobante = new Comprobante(cliente, cuenta, saldoDecimal, CBU);
		        // Generar el comprobante
		        String base64Image = comprobante.generarComprobanteTransferencia();
		        // Guardar el comprobante en la sesión
		        request.getSession().setAttribute("base64Image", base64Image);

		        // Esto pertenece a la transferencia y se ve en el jsp
		    	request.getSession().setAttribute("successTransfer", "Transferencia realizada exitósamente");
		    	response.sendRedirect(request.getContextPath() + "/ServletTransferencias?Param=1");
		        return;
		    }
	
		}catch (DBException e) {
	        e.printStackTrace();
	        request.getSession().setAttribute("error", "Error de base de datos. Por favor, inténtalo de nuevo más tarde. \n" + e.getMessage());	 
	        response.sendRedirect(request.getContextPath() + "/ServletTransferencias?Param=1");
	        return;
		}catch (GenericException e) {
	        e.printStackTrace();
	        request.getSession().setAttribute("error", "Hubo un error inesperado. Intente nuevamente más tarde"+ e.getMessage());		        
	        response.sendRedirect(request.getContextPath() + "/ServletTransferencias?Param=1");
	        return;
		}
	    
	    

	}
}
