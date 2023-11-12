package dao;

import java.util.ArrayList;
import java.util.List;

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
}
