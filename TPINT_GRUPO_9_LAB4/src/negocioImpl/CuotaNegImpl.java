package negocioImpl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.CuotaDaoInterface;
import daoImpl.CuotaDAO;
import entidad.Cuota;
import entidad.DBException;
import entidad.Prestamo;
import negocio.CuentaNeg;
import negocio.CuotaNeg;

public class CuotaNegImpl implements CuotaNeg{
	private CuotaDaoInterface cuotaDao= new CuotaDAO();

	@Override
	public int Pagar(Cuota cuota, int IDPrestamo, int IDUsuario, int IDCuenta) throws SQLException{
		CuentaNeg negCt = new CuentaNegImpl();
		BigDecimal saldo = negCt.obtenerSaldo(IDCuenta);
		int rs = 0;
		
		if(saldo.compareTo(cuota.getMontoAPagar()) >= 0) { //>= 0 : El saldo es igual o mayor - puede pagar
			//Generar movimientos negativo PagoDePrestamo
			//Se descuenta la plata de la cuenta
			saldo = saldo.subtract(cuota.getMontoAPagar());
			negCt.Debitar(IDCuenta, saldo);
			rs = cuotaDao.Pagar(cuota.getIDCuota(), IDPrestamo, IDUsuario, IDCuenta);
			//movimiento pago de cuota
		}else if(saldo.compareTo(cuota.getMontoAPagar()) == 0) { //no puede pagar porque el saldo es menor
			rs = -1;
		}
		
		return rs;
	}

	@Override
	public int Agregar(Cuota cuota) throws SQLException{
		// TODO Auto-generated method stub
		return cuotaDao.Agregar(cuota);
	}

	@Override
	public ArrayList<Cuota> obtenerCuotasPorPrestamo(int IDPrestamo) throws DBException{
		// TODO Auto-generated method stub
		return cuotaDao.obtenerCuotasPorPrestamo(IDPrestamo);
	}

	@Override
	public ArrayList<Cuota> obtenerCuotasPorCliente(int idCliente)  throws DBException {
		// TODO Auto-generated method stub
		return cuotaDao.obtenerCuotasPorCliente(idCliente);
	}

	@Override
	public Cuota generarCuota(Prestamo prestamo, int idPr, int nroCt) {
		Cuota ct = new Cuota();
		ct.setCuotas_Totales(prestamo.getCantidadCuotas());
		ct.setEstado("No Pagado");
		ct.setFechaDePago(LocalDate.now());
		ct.setIDCuenta(prestamo.getCuenta().getIdCuenta());
		ct.setIDUsuario(prestamo.getCliente().get_IDCliente());
		ct.setIDPrestamo(idPr);
		ct.setMontoAPagar(prestamo.getImporteCuota());
		ct.setNro_Cuota(nroCt);
		return ct;
	}

	@Override
	public Cuota obtenerCuotaPorID(int IDCuota) throws DBException {
		// TODO Auto-generated method stub
		return cuotaDao.ObtenerCuotaPorID(IDCuota);
	}

}
