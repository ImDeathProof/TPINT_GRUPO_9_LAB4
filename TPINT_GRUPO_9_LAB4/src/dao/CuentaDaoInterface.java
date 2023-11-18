package dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

import entidad.Cuenta;
import entidad.DBException;

public interface CuentaDaoInterface {
	public ArrayList<Cuenta> obtenerCuentasPorUsuario(int idUsuario);
	public Cuenta obtenerCuentaPorID(int idCuenta);
	public Cuenta obtenerCuentaCorrientePorUsuario(int idUsuario);
	public Cuenta obtenerCuentaAhorroPorUsuario(int idUsuario);
	public ArrayList<Cuenta> obtenerTodasLasCuentas();
	public int cantidadCuentasPorUsuario (int idUsuario);
	public int pedirCuenta (String tipoCuenta, int idCliente);
	public int ValidarCuenta(int id);
    public int BloquearCuenta(int id);
    public int CambiarSaldo(BigDecimal saldo, int id, String TipoCuenta);
    public int CambiarSaldo(BigDecimal saldo, long CBU);
    public BigDecimal obtenerSaldo(int IDCuenta) throws SQLException;
    public ArrayList<Cuenta> obtenerCuentasPaginadas(int pageNumber, int pageSize);
    public int getCantPaginas();
    public int transferirDinero(BigDecimal monto, int userID, long CBUCuentaDestinataria, String tipoCuenta);
	public int Debitar(int IDCuenta, BigDecimal saldo);
}
