package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImpl.ClienteDAO;
import daoImpl.CuentaDAO;
import entidad.Cliente;
import entidad.Cuenta;
import negocio.ClienteNeg;
import negocio.CuentaNeg;
import negocioImpl.ClienteNegImpl;
import negocioImpl.CuentaNegImpl;

/**
 * Servlet implementation class ServletAprovarCuenta
 */
@WebServlet("/ServletAprovarCuenta")
public class ServletAprovarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CuentaNeg cuNeg = new CuentaNegImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAprovarCuenta() {
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
		
		int cuentaID = Integer.parseInt(request.getParameter("cuentaID"));
		String buttonValueEstado = request.getParameter("submitValueEstado");

		if (buttonValueEstado.equals("Validar")) {		
			cuNeg.ValidarCuenta(cuentaID);
	    } else if (buttonValueEstado.equals("Bloquear")) {
	    	cuNeg.BloquearCuenta(cuentaID);
	    }
		
		response.sendRedirect("PanelDeControl.jsp");
	}

}
