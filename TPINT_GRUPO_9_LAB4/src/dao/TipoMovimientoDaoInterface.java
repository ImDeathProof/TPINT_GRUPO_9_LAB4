package dao;

import java.util.ArrayList;

import entidad.TipoMovimiento;
import excepciones.DBException;
import excepciones.GenericException;

public interface TipoMovimientoDaoInterface {

	public TipoMovimiento getTipoMovimiento(int idTipo, int idCuenta) throws DBException, GenericException;
	public TipoMovimiento getTipoxDescripcion(String tipo) throws DBException, GenericException;
}
