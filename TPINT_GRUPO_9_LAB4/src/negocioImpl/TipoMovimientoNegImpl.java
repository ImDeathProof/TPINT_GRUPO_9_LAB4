package negocioImpl;

import java.util.ArrayList;

import dao.MovimientoDaoInterface;
import dao.TipoMovimientoDaoInterface;
import daoImpl.MovimientoDAO;
import daoImpl.TipoMovimientoDAO;
import entidad.DBException;
import entidad.GenericException;
import entidad.TipoMovimiento;
import negocio.TipoMovimientoNeg;

public class TipoMovimientoNegImpl implements TipoMovimientoNeg {
	
	private TipoMovimientoDaoInterface tMovDao = new TipoMovimientoDAO();

	@Override
	public TipoMovimiento getTipoMovimiento(int idTipo, int idCuenta) throws DBException, GenericException {
		return tMovDao.getTipoMovimiento(idTipo, idCuenta);
	}

	@Override
	public TipoMovimiento getTipoxDescripcion(String tipo) throws DBException, GenericException {
		return tMovDao.getTipoxDescripcion(tipo);
	}

}
