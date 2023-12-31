package negocio;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

import excepciones.DBException;
import excepciones.GenericException;
import excepciones.ValidateException;
import entidad.Cuenta;

public interface CuentaNeg {
	
	public ArrayList<Cuenta> obtenerCuentasPorUsuario(int idUsuario) throws DBException, GenericException;
	public ArrayList<Cuenta> obtenerTodasLasCuentas() throws DBException, GenericException;
	public Cuenta obtenerCuentaCorrientePorUsuario(int idUsuario) throws DBException, GenericException;
	public Cuenta obtenerCuentaAhorroPorUsuario(int idUsuario) throws DBException, GenericException;
	public int pedirCuenta (String tipoCuenta, int idCliente) throws DBException, GenericException, ValidateException;
	public int ValidarCuenta(int id) throws ValidateException, DBException, GenericException;
    public int BloquearCuenta(int id) throws DBException, GenericException;
    public int CambiarSaldo(BigDecimal saldo, int id, int IDCuenta ) throws DBException, GenericException;
    public int CambiarSaldo(BigDecimal saldo, String CBU) throws DBException, GenericException;
    public BigDecimal obtenerSaldo(int IDCuenta) throws DBException, GenericException;
    public ArrayList<Cuenta> obtenerCuentasPaginadas(int pageNumber, int pageSize) throws DBException, GenericException;
    public int getCantPaginas() throws DBException, GenericException;
    //public int transferirDinero(BigDecimal monto, int userID, String CBUCuentaDestinataria, String tipoCuenta) throws DBException, GenericException;
    public int transferirDinero(BigDecimal monto, int userID, String CBUCuentaDestinataria, int IDCuenta) throws DBException, GenericException;
	int cantidadCuentasPorUsuario(int idUsuario) throws DBException, GenericException;
	public Cuenta obtenerCuentaPorID(int idCuenta) throws DBException, GenericException;
	public int Debitar(int IDCuenta, BigDecimal saldo) throws DBException, GenericException;
	public String generarCBU() throws ValidateException, GenericException;
	public boolean existeCBU(int numeroCBU) throws ValidateException, GenericException;
	public int getCuentaFromCBU(String CBU) throws DBException, GenericException;
	public int getCantPaginasXFiltro(String elementoBusqueda, String criterioBusqueda, String criterioBusqueda2) throws DBException, GenericException;
	public ArrayList<Cuenta> obtenerCuentasPaginadasFiltradas(int pageNumber, int pageSize, String elementoBusqueda, String criterioBusqueda, String criterioBusqueda2) throws DBException, GenericException;
	public int getCuentasCreadasSegunPeriodo(int mes, int ano) throws DBException, GenericException;
	public int getTotalCuentasPorTipo(String tipo)throws DBException, GenericException;
}
