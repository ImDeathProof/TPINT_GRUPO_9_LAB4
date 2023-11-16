package negocioImpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.CuotaDaoInterface;
import daoImpl.CuotaDAO;
import entidad.Cuota;
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
	public ArrayList<Cuota> obtenerCuotasPorCliente(int idCliente) throws SQLException{
		// TODO Auto-generated method stub
		return cuotaDao.obtenerCuotasPorCliente(idCliente);
	}

	@Override
	public Cuota generarCuota(Prestamo prestamo) {
		Cuota ct = new Cuota();
		ct.setCuotas_Totales(prestamo.getCantidadCuotas());
		ct.setEstado("No Pagado");
		ct.setFechaDePago(LocalDate.now());
		ct.setIDCuenta(prestamo.getCuenta().getIdCuenta());
		ct.setIDUsuario(prestamo.getCliente().get_IDCliente());
		ct.setIDPrestamo(prestamo.getId_Prestamo());
		ct.setMontoAPagar(prestamo.getImporteCuota());
		return ct;
	}

}
