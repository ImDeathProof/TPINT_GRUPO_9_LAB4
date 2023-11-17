package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entidad.DBException;

import entidad.Cuenta;
import entidad.Prestamo;
import entidad.DBException;

public interface PrestamoDaoInterface {
	public List<Prestamo> ObtenerTodos() throws DBException;
	public Prestamo ObtenerUno(int id) throws DBException;
	public int Insertar(Prestamo prestamo) throws DBException;
	public int Editar(Prestamo prestamo)throws DBException;
	public int Borrar(int id) throws DBException;
	public int Aprobar(int id) throws DBException;
	public int Rechazar(int id) throws DBException;
	public ArrayList<Prestamo> obtenerPrestamosPaginados(int pageNumber, int pageSize) throws DBException;
	public int getCantPaginas() throws DBException;
	public ArrayList<Prestamo> obtenerPrestamosPorUsuario(int IDCliente) throws DBException;
	public ArrayList<Prestamo> obtenerPrestamosAprobadosPorUsuario(int IDCliente) throws DBException;
	public int obtenerUltimoID() throws DBException;
}
