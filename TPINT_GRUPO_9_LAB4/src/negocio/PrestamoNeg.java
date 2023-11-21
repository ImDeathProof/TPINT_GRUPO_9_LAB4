package negocio;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.Cliente;
import entidad.Prestamo;
import entidad.DBException;
import entidad.GenericException;

public interface PrestamoNeg {
	public List<Prestamo> ObtenerTodos()throws DBException, GenericException;
	public Prestamo ObtenerUno(int id)throws DBException, GenericException;
	public int Insertar(Prestamo prestamo)throws DBException, GenericException;
	public int Editar(Prestamo prestamo)throws DBException, GenericException;
	public int Borrar(int id)throws DBException, GenericException;
	public int Aprobar(Prestamo pr)throws DBException, GenericException;
	public int Rechazar(int id)throws DBException, GenericException;
	public ArrayList<Prestamo> obtenerPrestamosPaginados(int pageNumber, int pageSize)throws DBException, GenericException;
	public int getCantPaginas()throws DBException, GenericException;
	public ArrayList<Prestamo> obtenerPrestamosPorUsuario(int IDCliente)throws DBException, GenericException;
	public ArrayList<Prestamo> obtenerPrestamosAprobadosPorUsuario(int IDCliente)throws DBException, GenericException;
	public int obtenerUltimoID()throws DBException, GenericException;
}
