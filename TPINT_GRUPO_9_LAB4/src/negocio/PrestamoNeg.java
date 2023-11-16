package negocio;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.Cliente;
import entidad.Prestamo;

public interface PrestamoNeg {
	public List<Prestamo> ObtenerTodos()throws SQLException;
	public Prestamo ObtenerUno(int id)throws SQLException;
	public int Insertar(Prestamo prestamo)throws SQLException;
	public int Editar(Prestamo prestamo)throws SQLException;
	public int Borrar(int id)throws SQLException;
	public int Aprobar(int id)throws SQLException;
	public int Rechazar(int id)throws SQLException;
	public ArrayList<Prestamo> obtenerPrestamosPaginados(int pageNumber, int pageSize)throws SQLException;
	public int getCantPaginas()throws SQLException;
	public ArrayList<Prestamo> obtenerPrestamosPorUsuario(int IDCliente)throws SQLException;
	public ArrayList<Prestamo> obtenerPrestamosAprobadosPorUsuario(int IDCliente)throws SQLException;
	public int obtenerUltimoID()throws SQLException;
}
