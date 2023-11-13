package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.Prestamo;
import negocio.CuentaNeg;
import negocio.PrestamoNeg;
import negocioImpl.CuentaNegImpl;
import negocioImpl.PrestamoNegocioImpl;

/**
 * Servlet implementation class ServletPrestamosPorUsuario
 */
@WebServlet("/ServletPrestamosPorUsuario")
public class ServletPrestamosPorUsuario extends HttpServlet {
    private static final long serialVersionUID = 1L;
    PrestamoNeg prestamoNeg = new PrestamoNegocioImpl(); 

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cliente usuarioActivo = (Cliente) request.getSession().getAttribute("usuarioAutenticado");

        if (usuarioActivo != null) {
            ArrayList<Prestamo> prestamos = prestamoNeg.obtenerPrestamosPorUsuario(usuarioActivo.get_IDCliente());

            request.setAttribute("listaPrestamos", prestamos); 

            request.getRequestDispatcher("PrestamosAprobados.jsp").forward(request, response);
        } else {
            response.sendRedirect("Inicio.jsp");
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}