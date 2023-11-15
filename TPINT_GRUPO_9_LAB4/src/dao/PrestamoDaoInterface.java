package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.Cuenta;
import entidad.Prestamo;

public interface PrestamoDaoInterface {
	public List<Prestamo> ObtenerTodos();
	public Prestamo ObtenerUno(int id);
	public int Insertar(Prestamo prestamo);
	public int Editar(Prestamo prestamo);
	public int Borrar(int id);
	public int Aprobar(int id);
	public int Rechazar(int id);
	public ArrayList<Prestamo> obtenerPrestamosPaginados(int pageNumber, int pageSize);
	public int getCantPaginas();
	public ArrayList<Prestamo> obtenerPrestamosPorUsuario(int IDCliente);
	public ArrayList<Prestamo> obtenerPrestamosAprobadosPorUsuario(int IDCliente);
	
}
