package Servlets;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Base64;

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
		//INFORME DE CLIENTES
		if(request.getParameter("btnInformeClientes")!=null) {

		}
		//INFORME DE CUENTAS
		if(request.getParameter("btnInformeCuentas")!=null) {
			ArrayList<Integer> cuentasPorMes = new ArrayList<>();
			String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
			
			DefaultPieDataset dataset = new DefaultPieDataset();
			
			for(int i=0;i<12;i++) {
				try {
					cuentasPorMes.add(cneg.getCuentasCreadasSegunPeriodo(i+1, 2023));
				} catch (DBException | GenericException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			DefaultPieDataset dataset1 = new DefaultPieDataset();

			for (int i = 0; i < 12; i++) {
			    dataset1.setValue("Mes " + (i + 1), cuentasPorMes.get(i));
			}

			JFreeChart chart = ChartFactory.createPieChart("Cuentas por Mes", dataset1, true, true, false);
			
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			ChartUtilities.writeChartAsPNG(outputStream, chart, 800, 800);
			byte[] chartBytes = outputStream.toByteArray();

			// Guardar la imagen en el servidor
			String relativePath = "/images/chart.png";
			String fullPath = getServletContext().getRealPath(relativePath);

			FileOutputStream fos = new FileOutputStream(fullPath);
			fos.write(chartBytes);
			fos.close();

			// Guardar la URL de la imagen en el request
			request.setAttribute("chartImageURL", relativePath);
		}
		
	}

}
