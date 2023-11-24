package Servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import entidad.Comprobante;
import entidad.Cuenta;
import entidad.Cuota;
import entidad.DBException;
import negocio.CuentaNeg;
import negocio.CuotaNeg;
import negocioImpl.CuentaNegImpl;
import negocioImpl.CuotaNegImpl;
import entidad.GenericException;

/**
 * Servlet implementation class ServletPagarCuota
 */
@WebServlet("/ServletPagarCuota")
public class ServletPagarCuota extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CuotaNeg negCt = new CuotaNegImpl();
    private CuentaNeg negCn = new CuentaNegImpl();
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
			if(request.getSession().getAttribute("error") != null) {
				request.getSession().removeAttribute("error");
			}
			try {
				Cuota ct = negCt.obtenerCuotaPorID(Integer.parseInt(request.getParameter("IDCuota")));
				if(request.getParameter("SelectCuentas") != null && !request.getParameter("SelectCuentas").isEmpty() && !"Seleccionar".equals(request.getParameter("SelectCuentas"))) {
					try {
				        int IDCuenta = Integer.parseInt(request.getParameter("SelectCuentas"));
				        Cuenta cn = negCn.obtenerCuentaPorID(IDCuenta);
				        if(cn.getSaldo().compareTo(ct.getMontoAPagar()) >= 0) {
				        	negCt.Pagar(ct, ct.getIDPrestamo(), ct.getIDUsuario(), IDCuenta);
//				        	Generar comprobante (TEST)
				        	Cliente cl = (Cliente) request.getSession().getAttribute("usuarioAutenticado");
					        Comprobante comprobante = new Comprobante(cl, cn, ct.getMontoAPagar(), ct);
					        // Generar el comprobante
					        String base64Image = comprobante.generarComprobanteCuota();
					        // Guardar el comprobante en la sesión
					        request.getSession().setAttribute("base64Image", base64Image);
					        request.getSession().setAttribute("PagoExitoso", "Cuota pagada con exito!");
				        }else {
				        	request.getSession().setAttribute("error", "El saldo de la cuenta seleccionada es insuficiente.");
				        	return;
				        }
				    } catch (NumberFormatException e) {
				        e.printStackTrace();
				        request.getSession().setAttribute("error", "Error al intentar pagar la cuota.");
				        return;
				    }
				}else {
					request.getSession().setAttribute("error", "Seleccione una cuenta.");
					return;
				}
			} catch (NumberFormatException | DBException e) {
				e.printStackTrace();
				request.getSession().setAttribute("error", "Error al procesar el pago de la cuota."+ e.getMessage());	
				return;
			}catch (GenericException e) {
		        e.printStackTrace();
		        request.getSession().setAttribute("error", "Hubo un error inesperado. Intente nuevamente más tarde"+ e.getMessage());	        
		        return;
		    }
			
			finally {
		        RequestDispatcher rd = request.getRequestDispatcher("PagoDePrestamos.jsp");
		        rd.forward(request, response);
			}
		}
	}

}
