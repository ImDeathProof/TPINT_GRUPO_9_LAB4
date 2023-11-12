package negocio;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entidad.Movimiento;
import entidad.TipoMovimiento;

public interface MovimientoNeg {

	public int insertMovimiento(int idTipo, int idCuenta);
	public int insertMovimiento(int idCuenta,BigDecimal monto, TipoMovimiento tp);
	public int getUserFromCuenta(int idCuenta);
	public ArrayList<Movimiento> generarInformeIngresosEgresos(LocalDate fechaInicio, LocalDate fechaFin);
	 public ArrayList<Movimiento> obtenerInformePaginado(int pageNumber, int pageSize, LocalDate fechaInicio, LocalDate fechaFin,String orderBy);
	 public int getCantPaginas();
}
