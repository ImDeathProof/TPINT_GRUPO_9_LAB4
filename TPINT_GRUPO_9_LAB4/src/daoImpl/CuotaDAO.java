package daoImpl;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.CuotaDaoInterface;
import entidad.Cuota;

public class CuotaDAO implements CuotaDaoInterface {

	@Override
	public int Pagar(int idCuota) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int Agregar(Cuota cuota) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Cuota> obtenerCuotasPorPrestamo(int idPrestamo) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Cuota> obtenerCuotasPorCliente(int idCliente) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
