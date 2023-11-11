package negocioImpl;

import java.math.BigDecimal;
import java.util.ArrayList;

import dao.ClienteDaoInterface;
import dao.CuentaDaoInterface;
import daoImpl.CuentaDAO;
import entidad.Cuenta;
import negocio.CuentaNeg;

public class CuentaNegImpl implements CuentaNeg {
	
	private CuentaDaoInterface clDao = new CuentaDAO();

	@Override
	public ArrayList<Cuenta> obtenerCuentasPorUsuario(int idUsuario) {
			return clDao.obtenerCuentasPorUsuario(idUsuario);
	}
	
	@Override
	public int cantidadCuentasPorUsuario(int idUsuario) {
			return clDao.cantidadCuentasPorUsuario(idUsuario);
	}

	@Override
	public ArrayList<Cuenta> obtenerTodasLasCuentas() {
		return clDao.obtenerTodasLasCuentas();
	}

	@Override
	public int pedirCuenta(String tipoCuenta, int idCliente) {
		return clDao.pedirCuenta(tipoCuenta, idCliente);
	}

	@Override
	public int ValidarCuenta(int id) {
		return clDao.ValidarCuenta(id);
	}

	@Override
	public int BloquearCuenta(int id) {
		return clDao.BloquearCuenta(id);
	}

	@Override
	public int CambiarSaldo(BigDecimal saldo, int id, String TipoCuenta) {
		return clDao.CambiarSaldo(saldo, id, TipoCuenta);
	}

	@Override
	public int CambiarSaldo(BigDecimal saldo, long CBU) {
		return clDao.CambiarSaldo(saldo, CBU);
	}

	@Override
	public ArrayList<Cuenta> obtenerCuentasPaginadas(int pageNumber, int pageSize) {
		return clDao.obtenerCuentasPaginadas(pageNumber, pageSize);
	}

	@Override
	public int getCantPaginas() {
		return clDao.getCantPaginas();
	}

	@Override
	public int transferirDinero(BigDecimal monto, int userID, long CBUCuentaDestinataria, String tipoCuenta) {
		return clDao.transferirDinero(monto, userID, CBUCuentaDestinataria, tipoCuenta);
	}
	
	

}
