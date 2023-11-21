package dao;

import java.util.ArrayList;

import entidad.DBException;
import entidad.GenericException;
import entidad.TipoMovimiento;

public interface TipoMovimientoDaoInterface {

	public ArrayList<TipoMovimiento> getTipoMovimiento(int idTipo, int idCuenta) throws DBException, GenericException;
	public TipoMovimiento getTipoxDescripcion(String tipo) throws DBException, GenericException;
}
