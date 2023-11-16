package dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entidad.Movimiento;
import entidad.Prestamo;
import entidad.TipoMovimiento;

public interface MovimientoDaoInterface {
	
	 public int insertMovimiento(int idTipo, int idCuenta);
	 public int insertMovimiento(int idCuenta,BigDecimal monto, TipoMovimiento tp);
	 public int getUserFromCuenta(int idCuenta);
	 public ArrayList<Movimiento> generarInformeIngresosEgresos(LocalDate fechaInicio, LocalDate fechaFin);
	 public ArrayList<Movimiento> obtenerInformePaginado(int pageNumber, int pageSize, LocalDate fechaInicio, LocalDate fechaFin,String orderBy);
	 public ArrayList<Movimiento> obtenerInformePaginado(int pageNumber, int pageSize, int idCuenta);
	 public int getCantMovimientos(LocalDate fechaInicio, LocalDate fechaFin);
	 public BigDecimal getPromedioMonto(LocalDate fechaInicio, LocalDate fechaFin);
	 public int getCantPaginas();
	 public int getCantPaginas(int idCliente);

}
