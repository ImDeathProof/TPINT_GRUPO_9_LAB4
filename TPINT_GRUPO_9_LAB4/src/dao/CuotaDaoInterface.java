package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import entidad.Cuota;
import entidad.Prestamo;

public interface CuotaDaoInterface {
	public int Pagar(int idCuota) throws SQLException;
	public int Agregar(Cuota cuota) throws SQLException;
	public ArrayList<Cuota> obtenerCuotasPorPrestamo(int idPrestamo) throws SQLException;
	public ArrayList<Cuota> obtenerCuotasPorCliente(int idCliente) throws SQLException;
}
