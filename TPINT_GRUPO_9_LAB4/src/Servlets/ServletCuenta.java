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
        // Obtiene al usuario activo desde la sesión
        Cliente usuarioActivo = (Cliente) request.getSession().getAttribute("usuarioAutenticado");

        if (usuarioActivo != null) {
            // Crea una instancia de la clase CuentaDAO
            CuentaDAO cuentaDAO = new CuentaDAO();

            // Obtiene la lista de cuentas asociadas al usuario utilizando el método obtenerCuentasPorUsuario
            ArrayList<Cuenta> cuentas = cuentaDAO.obtenerCuentasPorUsuario(usuarioActivo.get_IDCliente());

            // Agrega la lista de cuentas a los atributos de solicitud para mostrar en la página JSP
            request.setAttribute("cuentas", cuentas);

            // Redirige a la página JSP
            request.getRequestDispatcher("MisCuentas.jsp").forward(request, response);
        } else {
            // Si el usuario no está autenticado, redirige a "Inicio.jsp"
            response.sendRedirect("Inicio.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

