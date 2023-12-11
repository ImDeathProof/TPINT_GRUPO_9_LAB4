package dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entidad.Movimiento;
import entidad.Prestamo;
import entidad.TipoMovimiento;
import excepciones.DBException;
import excepciones.GenericException; 

public interface MovimientoDaoInterface {
	
	 public int insertMovimiento(int idTipo, int idCuenta) throws DBException, GenericException;
	 public int insertMovimiento(int idCuenta,BigDecimal monto, TipoMovimiento tp) throws DBException, GenericException;
	 public int getUserFromCuenta(int idCuenta) throws DBException, GenericException;
	 public ArrayList<Movimiento> generarInformeIngresosEgresos(LocalDate fechaInicio, LocalDate fechaFin) throws DBException, GenericException;
	 public ArrayList<Movimiento> obtenerInformePaginado(int pageNumber, int pageSize, LocalDate fechaInicio, LocalDate fechaFin,String orderBy) throws DBException, GenericException;
	 public ArrayList<Movimiento> obtenerInformePaginado(int pageNumber, int pageSize, int idCuenta) throws DBException, GenericException;
	 public int getCantMovimientos(LocalDate fechaInicio, LocalDate fechaFin) throws DBException, GenericException;
	 public BigDecimal getPromedioMonto(LocalDate fechaInicio, LocalDate fechaFin) throws DBException, GenericException;
	 public int getCantPaginas() throws DBException, GenericException;
	 public int getCantPaginas(int idCuenta) throws DBException, GenericException;

}
