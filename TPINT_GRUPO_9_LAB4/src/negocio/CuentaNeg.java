package negocio;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import entidad.DBException;
import entidad.ValidateException;
import entidad.GenericException;

import entidad.Cuenta;

public interface CuentaNeg {
	
	public ArrayList<Cuenta> obtenerCuentasPorUsuario(int idUsuario) throws DBException, GenericException;
	public ArrayList<Cuenta> obtenerTodasLasCuentas() throws DBException, GenericException;
	public Cuenta obtenerCuentaCorrientePorUsuario(int idUsuario) throws DBException, GenericException;
	public Cuenta obtenerCuentaAhorroPorUsuario(int idUsuario) throws DBException, GenericException;
	public int pedirCuenta (String tipoCuenta, int idCliente) throws DBException, GenericException, ValidateException;
	public int ValidarCuenta(int id) throws ValidateException, DBException, GenericException;
    public int BloquearCuenta(int id) throws DBException, GenericException;
    public int CambiarSaldo(BigDecimal saldo, int id, String TipoCuenta) throws DBException, GenericException;
    public int CambiarSaldo(BigDecimal saldo, String CBU) throws DBException, GenericException;
    public BigDecimal obtenerSaldo(int IDCuenta) throws DBException, GenericException;
    public ArrayList<Cuenta> obtenerCuentasPaginadas(int pageNumber, int pageSize) throws DBException, GenericException;
    public int getCantPaginas() throws DBException, GenericException;
    public int transferirDinero(BigDecimal monto, int userID, String CBUCuentaDestinataria, String tipoCuenta) throws DBException, GenericException;
	int cantidadCuentasPorUsuario(int idUsuario) throws DBException, GenericException;
	public Cuenta obtenerCuentaPorID(int idCuenta) throws DBException, GenericException;
	public int Debitar(int IDCuenta, BigDecimal saldo) throws DBException, GenericException;
	public String generarCBU() throws ValidateException, GenericException;
}
