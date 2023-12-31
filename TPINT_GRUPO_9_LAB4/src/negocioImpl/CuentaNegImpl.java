package negocioImpl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ClienteDaoInterface;
import dao.CuentaDaoInterface;
import daoImpl.CuentaDAO;
import entidad.Cuenta;
import negocio.CuentaNeg;
import excepciones.DBException;
import excepciones.GenericException;
import excepciones.ValidateException;

public class CuentaNegImpl implements CuentaNeg {
	
	private CuentaDaoInterface clDao = new CuentaDAO();

	@Override
	public ArrayList<Cuenta> obtenerCuentasPorUsuario(int idUsuario) throws DBException, GenericException {
			return clDao.obtenerCuentasPorUsuario(idUsuario);
	}
	
	@Override
	public int cantidadCuentasPorUsuario(int idUsuario) throws DBException, GenericException {
			return clDao.cantidadCuentasPorUsuario(idUsuario);
	}

	@Override
	public ArrayList<Cuenta> obtenerTodasLasCuentas()  throws DBException, GenericException{
		return clDao.obtenerTodasLasCuentas();
	}

	@Override
	public int pedirCuenta(String tipoCuenta, int idCliente)  throws DBException, GenericException, ValidateException{
		return clDao.pedirCuenta(tipoCuenta, idCliente);
	}

	@Override
	public int ValidarCuenta(int id) throws ValidateException, DBException, GenericException {
		return clDao.ValidarCuenta(id);
	}

	@Override
	public int BloquearCuenta(int id)  throws DBException, GenericException{
		return clDao.BloquearCuenta(id);
	}

	@Override
	public int CambiarSaldo(BigDecimal saldo, int id, int IDCuenta )  throws DBException, GenericException{
		return clDao.CambiarSaldo(saldo, id, IDCuenta);
	}

	@Override
	public int CambiarSaldo(BigDecimal saldo, String CBU) throws DBException, GenericException {
		return clDao.CambiarSaldo(saldo, CBU);
	}

	@Override
	public ArrayList<Cuenta> obtenerCuentasPaginadas(int pageNumber, int pageSize)  throws DBException, GenericException{
		return clDao.obtenerCuentasPaginadas(pageNumber, pageSize);
	}

	@Override
	public int getCantPaginas()  throws DBException, GenericException{
		return clDao.getCantPaginas();
	}

	@Override
	//public int transferirDinero(BigDecimal monto, int userID, String CBUCuentaDestinataria, String tipoCuenta)  throws DBException, GenericException{
	public int transferirDinero(BigDecimal monto, int userID, String CBUCuentaDestinataria, int IDCuenta)  throws DBException, GenericException{
		return clDao.transferirDinero(monto, userID, CBUCuentaDestinataria, IDCuenta);
	}

	@Override
	public Cuenta obtenerCuentaCorrientePorUsuario(int idUsuario)  throws DBException, GenericException{
		// TODO Auto-generated method stub
		return clDao.obtenerCuentaCorrientePorUsuario(idUsuario);
	}

	@Override
	public Cuenta obtenerCuentaAhorroPorUsuario(int idUsuario)  throws DBException, GenericException{
		// TODO Auto-generated method stub
		return clDao.obtenerCuentaAhorroPorUsuario(idUsuario);
	}

	@Override
	public Cuenta obtenerCuentaPorID(int idCuenta)  throws DBException, GenericException{
		// TODO Auto-generated method stub
		return clDao.obtenerCuentaPorID(idCuenta);
	}

	@Override
	public BigDecimal obtenerSaldo(int IDCuenta) throws DBException, GenericException {
		// TODO Auto-generated method stub
		return clDao.obtenerSaldo(IDCuenta);
	}

	@Override
	public int Debitar(int IDCuenta, BigDecimal saldo)  throws DBException, GenericException{
		return clDao.Debitar(IDCuenta, saldo);
	}

	@Override
	public String generarCBU() throws ValidateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existeCBU(int numeroCBU) throws ValidateException, GenericException {
		// TODO Auto-generated method stub
		return clDao.existeCBU(numeroCBU);
	}

	@Override
	public int getCuentaFromCBU(String CBU) throws DBException, GenericException {
		// TODO Auto-generated method stub
		return clDao.getCuentaFromCBU(CBU);
	}

	@Override
	public int getCantPaginasXFiltro(String elementoBusqueda, String criterioBusqueda, String criterioBusqueda2)
			throws DBException, GenericException {
		return clDao.getCantPaginasXFiltro(elementoBusqueda, criterioBusqueda, criterioBusqueda2);
	}

	@Override
	public ArrayList<Cuenta> obtenerCuentasPaginadasFiltradas(int pageNumber, int pageSize, String elementoBusqueda,
			String criterioBusqueda, String criterioBusqueda2) throws DBException, GenericException {
		return clDao.obtenerCuentasPaginadasFiltradas(pageNumber, pageSize, elementoBusqueda, criterioBusqueda, criterioBusqueda2);
	}

	@Override
	public int getCuentasCreadasSegunPeriodo(int mes, int ano) throws DBException, GenericException {
		// TODO Auto-generated method stub
		return clDao.getCuentasCreadasSegunPeriodo(mes, ano);
	}

	@Override
	public int getTotalCuentasPorTipo(String tipo) throws DBException, GenericException {
		// TODO Auto-generated method stub
		return clDao.getTotalCuentasPorTipo(tipo);
	}
	
	
	
	

}
