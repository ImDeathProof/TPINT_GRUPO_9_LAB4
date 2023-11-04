package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Dominio.Cliente;
import Dominio.Cuenta;
import Dominio.CuentaDAO;

@WebServlet("/ServletCuenta")
public class ServletCuenta extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        Cliente usuarioActivo = (Cliente) request.getSession().getAttribute("usuarioAutenticado");

        if (usuarioActivo != null) {
           
            CuentaDAO cuentaDAO = new CuentaDAO();
            
            ArrayList<Cuenta> cuentas = cuentaDAO.obtenerCuentasPorUsuario(usuarioActivo.get_IDCliente());
           
            request.setAttribute("cuentas", cuentas);
           
            request.getRequestDispatcher("MisCuentas.jsp").forward(request, response);
        } else {
            
            response.sendRedirect("Inicio.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

