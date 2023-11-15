package negocioImpl;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.CuotaDaoInterface;
import daoImpl.CuotaDAO;
import entidad.Cuota;
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

}
