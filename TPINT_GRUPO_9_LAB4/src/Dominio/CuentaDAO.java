package Dominio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CuentaDAO {
    private String host = "jdbc:mysql://127.0.0.1:3306/";
    private String user = "root";
    private String pass = "root";
    private String dbName = "bancodb";

    public CuentaDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Cuenta> obtenerCuentasPorUsuario(int idUsuario) {
        ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
        String cuentasQuery = "SELECT * FROM Cuenta WHERE IdUsuario = ?";

        try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
             PreparedStatement cuentasStatement = cn.prepareStatement(cuentasQuery)) {
            cuentasStatement.setInt(1, idUsuario);
            ResultSet cuentasResultSet = cuentasStatement.executeQuery();

            while (cuentasResultSet.next()) {
                Cuenta cuenta = new Cuenta();
                cuenta.setIdCuenta(cuentasResultSet.getInt("IDCuenta"));
                cuenta.setIdUsuario(cuentasResultSet.getInt("IdUsuario"));
                cuenta.setTipoCuenta(cuentasResultSet.getString("TipoCuenta"));
                cuenta.setNumeroCuenta(cuentasResultSet.getString("NumeroCuenta"));
                cuenta.setCBU(cuentasResultSet.getString("CBU"));
                cuenta.setSaldo(cuentasResultSet.getBigDecimal("Saldo"));
                cuenta.setFechaCreacion(cuentasResultSet.getTimestamp("Fecha_Creacion").toLocalDateTime().toLocalDate());

                lista.add(cuenta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
}
