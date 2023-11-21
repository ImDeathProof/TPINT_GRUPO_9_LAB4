package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import entidad.Cuota;
import entidad.DBException;
import entidad.GenericException;
import entidad.Prestamo;
import entidad.DBException;

public interface CuotaDaoInterface {
	public int Pagar(int IDCuota, int IDPrestamo, int IDUsuario, int IDCuenta) throws DBException, GenericException;
	public int Agregar(Cuota cuota) throws DBException, GenericException;
	public ArrayList<Cuota> obtenerCuotasPorPrestamo(int IDPrestamo) throws DBException, GenericException;
	public ArrayList<Cuota> obtenerCuotasPorCliente(int idCliente) throws DBException, GenericException;
	public Cuota ObtenerCuotaPorID(int iDCuota)throws DBException, GenericException;
}
