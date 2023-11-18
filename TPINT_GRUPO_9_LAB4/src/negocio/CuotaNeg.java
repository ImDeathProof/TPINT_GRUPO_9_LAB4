package negocio;

import java.sql.SQLException;
import java.util.ArrayList;

import entidad.Cuota;
import entidad.DBException;
import entidad.Prestamo;

public interface CuotaNeg {
	public int Pagar(Cuota cuota, int IDPrestamo, int IDUsuario, int IDCuenta) throws SQLException;
	public int Agregar(Cuota cuota) throws SQLException;
	public ArrayList<Cuota> obtenerCuotasPorPrestamo(int IDPrestamo) throws DBException;
	public ArrayList<Cuota> obtenerCuotasPorCliente(int idCliente) throws DBException;
	public Cuota generarCuota(Prestamo prestamo, int idPr, int nroCt);
	public Cuota obtenerCuotaPorID(int IDCuota) throws DBException;
}
