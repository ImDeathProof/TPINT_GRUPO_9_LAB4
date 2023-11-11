package dao;

import java.util.List;

import entidad.Prestamo;

public interface PrestamoDaoInterface {
	public List<Prestamo> ObtenerTodos();
	public Prestamo ObtenerUno(int id);
	public boolean Insertar(Prestamo prestamo);
	public boolean Editar(Prestamo prestamo);
	public boolean Borrar(int id);
	public boolean Aprobar(int id);
}
