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

import com.google.gson.Gson;

import excepciones.DBException;
import excepciones.GenericException;
import negocio.ClienteNeg;
import negocio.CuentaNeg;
import negocioImpl.ClienteNegImpl;
import negocioImpl.CuentaNegImpl;

/**
 * Servlet implementation class ServletInformes
 */
@WebServlet("/ServletInformes")
public class ServletInformes extends HttpServlet {
	private static final long serialVersionUID = 1L;
    CuentaNeg cneg=new CuentaNegImpl();
    ClienteNeg clNeg=new ClienteNegImpl();
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
		//INFORME DE CLIENTES POR SEXO
		if(request.getParameter("btnGenerarInformeUsuariosXSexo")!=null) {
			try {
				int varones = clNeg.getCantidadDeUsuariosXSexo("1");
				int mujeres = clNeg.getCantidadDeUsuariosXSexo("0");
				
				request.setAttribute("varones", varones);
				request.setAttribute("mujeres", mujeres);
				
				request.removeAttribute("graficoAnual");
				request.removeAttribute("graficoTipos");
				request.setAttribute("graficoUserXSexo", true);
				
			} catch (DBException | GenericException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//INFORME DE CLIENTES POR PROVINCIA
		if(request.getParameter("btnGenerarInformeUsuariosXProvincias")!=null) {
			try {
				int[] usuariosXProvincias = clNeg.getCantidadDeUsuariosXProvincia();
				Map<String, Integer> provinciasXUsuariosMap = new HashMap<>();
				provinciasXUsuariosMap.put("Buenos Aires",usuariosXProvincias[0]);
				provinciasXUsuariosMap.put("Buenos Aires-GBA",usuariosXProvincias[1]);
				provinciasXUsuariosMap.put("Capital Federal",usuariosXProvincias[2]);
				provinciasXUsuariosMap.put("Catamarca",usuariosXProvincias[3]);
				provinciasXUsuariosMap.put("Chaco",usuariosXProvincias[4]);
				provinciasXUsuariosMap.put("Chubut",usuariosXProvincias[5]);
				provinciasXUsuariosMap.put("Córdoba",usuariosXProvincias[6]);
				provinciasXUsuariosMap.put("Corrientes",usuariosXProvincias[7]);
				provinciasXUsuariosMap.put("Entre Ríos",usuariosXProvincias[8]);
				provinciasXUsuariosMap.put("Formosa",usuariosXProvincias[9]);
				provinciasXUsuariosMap.put("Jujuy",usuariosXProvincias[10]);
				provinciasXUsuariosMap.put("La Pampa",usuariosXProvincias[11]);
				provinciasXUsuariosMap.put("La Rioja",usuariosXProvincias[12]);
				provinciasXUsuariosMap.put("Mendoza",usuariosXProvincias[13]);
				provinciasXUsuariosMap.put("Misiones",usuariosXProvincias[14]);
				provinciasXUsuariosMap.put("Neuquén",usuariosXProvincias[15]);
				provinciasXUsuariosMap.put("Río Negro",usuariosXProvincias[16]);
				provinciasXUsuariosMap.put("Salta",usuariosXProvincias[17]);
				provinciasXUsuariosMap.put("San Juan",usuariosXProvincias[18]);
				provinciasXUsuariosMap.put("San Luis",usuariosXProvincias[19]);
				provinciasXUsuariosMap.put("Santa Cruz",usuariosXProvincias[20]);
				provinciasXUsuariosMap.put("Santa Fe",usuariosXProvincias[21]);
				provinciasXUsuariosMap.put("Santiago del Estero",usuariosXProvincias[22]);
				provinciasXUsuariosMap.put("Tierra del Fuego",usuariosXProvincias[23]);
				provinciasXUsuariosMap.put("Tucumán",usuariosXProvincias[24]);
				
				///Estas dos lineas que siguen convierten el map en un json para leerlo desde el jsp con javascript
				Gson gson = new Gson();
				String jsonMap = gson.toJson(provinciasXUsuariosMap);
				request.setAttribute("jsonMap", jsonMap);
				
				request.removeAttribute("graficoAnual");
				request.removeAttribute("graficoTipos");
				request.removeAttribute("graficoUserXSexo");
				request.setAttribute("graficoUserXProvincia", true);
				
			} catch (DBException | GenericException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("ErrorGrafico", "Parece que algo no salió mal, intenta de nuevo mas tarde.");
				return;
			}
		}
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
			} catch (DBException | GenericException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("ErrorGrafico", "Parece que algo no salió mal, intenta de nuevo mas tarde.");
				return;
			}
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
				request.setAttribute("ErrorGrafico", "Parece que algo no salió mal, intenta de nuevo mas tarde.");
				return;
			}
		}
		
		///----NO AGREGAR NADA MAS ABAJO DE ESTA LINEA----
		RequestDispatcher rd = request.getRequestDispatcher("Informes.jsp");
        rd.forward(request, response);
		
	}

}
