package Servlets;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;


import entidad.DBException;
import entidad.GenericException;
import negocio.CuentaNeg;
import negocioImpl.CuentaNegImpl;

/**
 * Servlet implementation class ServletInformes
 */
@WebServlet("/ServletInformes")
public class ServletInformes extends HttpServlet {
	private static final long serialVersionUID = 1L;
    CuentaNeg cneg=new CuentaNegImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInformes() {
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
		//INFORME DE CUENTAS POR MES
		if(request.getParameter("btnGenerarInformeCuentasMensual")!=null) {
			int ano = Integer.parseInt(request.getParameter("selectAno"));
			int mes = Integer.parseInt(request.getParameter("selectMes"));
			Map<String, String> mesesMap = new HashMap<>();
			mesesMap.put("1", "Enero");
			mesesMap.put("2", "Febrero");
			mesesMap.put("3", "Marzo");
			mesesMap.put("4", "Abril");
			mesesMap.put("5", "Mayo");
			mesesMap.put("6", "Junio");
			mesesMap.put("7", "Julio");
			mesesMap.put("8", "Agosto");
			mesesMap.put("9", "Septiembre");
			mesesMap.put("10", "Octubre");
			mesesMap.put("11", "Noviembre");
			mesesMap.put("12", "Diciembre");
			String mesTxt = mesesMap.get(Integer.toString(mes));
			
			int cantidadSeleccionada=0,cantidadAnoAnterior=0;
			try {
				cantidadSeleccionada = cneg.getCuentasCreadasSegunPeriodo(mes, ano);
				cantidadAnoAnterior = cneg.getCuentasCreadasSegunPeriodo(mes, ano-1);
			} catch (DBException | GenericException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			///Solicitado
			request.setAttribute("cantidad1", cantidadSeleccionada);
			request.setAttribute("mes", mesTxt);
			request.setAttribute("ano", ano);
			///Mismo mes del año anterior para comparar
			request.setAttribute("cantidad2", cantidadAnoAnterior);
			request.setAttribute("mes", mesTxt);
			request.setAttribute("anoAnterior", ano-1);
			request.removeAttribute("graficoTipos");
			request.setAttribute("graficoAnual", true);
		}
		//INFORME DE TIPOS DE CUENTAS
		if(request.getParameter("btnGenerarInformeCuentasTipo")!=null) {
			try {
				int cuentaCorriente = cneg.getTotalCuentasPorTipo("Corriente");
				int cajaAhorro = cneg.getTotalCuentasPorTipo("Ahorros");
				request.setAttribute("ctCorriente", cuentaCorriente);
				request.setAttribute("ctAhorro", cajaAhorro);
				request.removeAttribute("graficoAnual");
				request.setAttribute("graficoTipos", true);
				
			} catch (DBException | GenericException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		///----NO AGREGAR NADA MAS ABAJO DE ESTA LINEA----
		RequestDispatcher rd = request.getRequestDispatcher("Informes.jsp");
        rd.forward(request, response);
		
	}

}
