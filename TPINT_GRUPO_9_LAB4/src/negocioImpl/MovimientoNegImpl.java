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
import entidad.Movimiento;
import entidad.TipoMovimiento;
import negocio.MovimientoNeg;

public class MovimientoNegImpl implements MovimientoNeg {

	private MovimientoDaoInterface movDao = new MovimientoDAO();
	
	@Override
	public int insertMovimiento(int idTipo, int idCuenta) {
		return movDao.insertMovimiento(idTipo, idCuenta);
	}

	@Override
	public int insertMovimiento(int idCuenta,BigDecimal monto, TipoMovimiento tp) {
		return movDao.insertMovimiento(idCuenta, monto,tp);
	}

	@Override
	public int getUserFromCuenta(int idCuenta) {
		return movDao.getUserFromCuenta(idCuenta);
	}

	@Override
	public ArrayList<Movimiento> generarInformeIngresosEgresos(LocalDate fechaInicio, LocalDate fechaFin) {
		return movDao.generarInformeIngresosEgresos(fechaInicio, fechaFin);
	}

	@Override
	public ArrayList<Movimiento> obtenerInformePaginado(int pageNumber, int pageSize, LocalDate fechaInicio,
		LocalDate fechaFin, String orderBy) {
		return movDao.obtenerInformePaginado(pageNumber, pageSize, fechaInicio, fechaFin, orderBy);
	}

	@Override
	public int getCantPaginas() {
		return movDao.getCantPaginas();
	}

	@Override
	public int getCantMovimientos(LocalDate fechaInicio, LocalDate fechaFin) {
		return movDao.getCantMovimientos(fechaInicio, fechaFin);
	}

	@Override
	public BigDecimal getPromedioMonto(LocalDate fechaInicio, LocalDate fechaFin) {
		return movDao.getPromedioMonto(fechaInicio, fechaFin);
	}

	@Override
	public ArrayList<Movimiento> obtenerInformePaginado(int pageNumber, int pageSize, int idCuenta) {
		return movDao.obtenerInformePaginado(pageNumber, pageSize, idCuenta);
	}

	@Override
	public int getCantPaginas(int idCliente) {
		return movDao.getCantPaginas(idCliente);
	}

}
