package negocioImpl;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.CuentaDaoInterface;
import dao.MovimientoDaoInterface;
import daoImpl.CuentaDAO;
import daoImpl.MovimientoDAO;
import entidad.DBException;
import entidad.Movimiento;
import entidad.TipoMovimiento;
import negocio.MovimientoNeg;
import entidad.GenericException;

public class MovimientoNegImpl implements MovimientoNeg {

	private MovimientoDaoInterface movDao = new MovimientoDAO();
	
	@Override
	public int insertMovimiento(int idTipo, int idCuenta)  throws DBException, GenericException{
		return movDao.insertMovimiento(idTipo, idCuenta);
	}

	@Override
	public int insertMovimiento(int idCuenta,BigDecimal monto, TipoMovimiento tp)  throws DBException, GenericException{
		return movDao.insertMovimiento(idCuenta, monto,tp);
	}

	@Override
	public int getUserFromCuenta(int idCuenta)  throws DBException, GenericException{
		return movDao.getUserFromCuenta(idCuenta);
	}

	@Override
	public ArrayList<Movimiento> generarInformeIngresosEgresos(LocalDate fechaInicio, LocalDate fechaFin)  throws DBException, GenericException{
		return movDao.generarInformeIngresosEgresos(fechaInicio, fechaFin);
	}

	@Override
	public ArrayList<Movimiento> obtenerInformePaginado(int pageNumber, int pageSize, LocalDate fechaInicio,
		LocalDate fechaFin, String orderBy)  throws DBException, GenericException{
		return movDao.obtenerInformePaginado(pageNumber, pageSize, fechaInicio, fechaFin, orderBy);
	}

	@Override
	public int getCantPaginas()  throws DBException, GenericException{
		return movDao.getCantPaginas();
	}

	@Override
	public int getCantMovimientos(LocalDate fechaInicio, LocalDate fechaFin)  throws DBException, GenericException{
		return movDao.getCantMovimientos(fechaInicio, fechaFin);
	}

	@Override
	public BigDecimal getPromedioMonto(LocalDate fechaInicio, LocalDate fechaFin)  throws DBException, GenericException{
		return movDao.getPromedioMonto(fechaInicio, fechaFin);
	}

	@Override
	public ArrayList<Movimiento> obtenerInformePaginado(int pageNumber, int pageSize, int idCuenta)  throws DBException, GenericException{
		return movDao.obtenerInformePaginado(pageNumber, pageSize, idCuenta);
	}

	@Override
	public int getCantPaginas(int idCliente)  throws DBException, GenericException{
		return movDao.getCantPaginas(idCliente);
	}

}
