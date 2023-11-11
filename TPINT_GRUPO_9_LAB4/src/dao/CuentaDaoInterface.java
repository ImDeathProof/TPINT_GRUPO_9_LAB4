package dao;

import java.math.BigDecimal;
import java.util.ArrayList;

import entidad.Cuenta;

public interface CuentaDaoInterface {
	public ArrayList<Cuenta> obtenerCuentasPorUsuario(int idUsuario);
	public Cuenta obtenerCuentaCorrientePorUsuario(int idUsuario);
	public Cuenta obtenerCuentaAhorroPorUsuario(int idUsuario);
	public ArrayList<Cuenta> obtenerTodasLasCuentas();
	public int cantidadCuentasPorUsuario (int idUsuario);
	public int pedirCuenta (String tipoCuenta, int idCliente);
	public int ValidarCuenta(int id);
    public int BloquearCuenta(int id);
    public int CambiarSaldo(BigDecimal saldo, int id, String TipoCuenta);
    public int CambiarSaldo(BigDecimal saldo, long CBU);
    public ArrayList<Cuenta> obtenerCuentasPaginadas(int pageNumber, int pageSize);
    public int getCantPaginas();
    public int transferirDinero(BigDecimal monto, int userID, long CBUCuentaDestinataria, String tipoCuenta);
}
