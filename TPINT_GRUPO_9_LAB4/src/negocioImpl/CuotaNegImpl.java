package negocioImpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.CuotaDaoInterface;
import daoImpl.CuotaDAO;
import entidad.Cuota;
import entidad.DBException;
import entidad.Prestamo;
import negocio.CuotaNeg;

public class CuotaNegImpl implements CuotaNeg{
	private CuotaDaoInterface cuotaDao= new CuotaDAO();

	@Override
	public int Pagar(int idCuota) throws SQLException {
		return cuotaDao.Pagar(idCuota);
	}

	@Override
	public int Agregar(Cuota cuota) throws SQLException{
		// TODO Auto-generated method stub
		return cuotaDao.Agregar(cuota);
	}

	@Override
	public ArrayList<Cuota> obtenerCuotasPorPrestamo(int idPrestamo) throws SQLException{
		// TODO Auto-generated method stub
		return cuotaDao.obtenerCuotasPorPrestamo(idPrestamo);
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

}
