package Dominio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;


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
                cuenta.setEstado(cuentasResultSet.getBoolean("Estado"));

                lista.add(cuenta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public ArrayList<Cuenta> obtenerTodasLasCuentas() {
        ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
        String cuentasQuery = "SELECT * FROM Cuenta";

        try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
             PreparedStatement cuentasStatement = cn.prepareStatement(cuentasQuery)) {
            ResultSet cuentasResultSet = cuentasStatement.executeQuery();

            while (cuentasResultSet.next()) {
                Cuenta cuenta = new Cuenta();
                cuenta.setIdCuenta(cuentasResultSet.getInt("IDCuenta"));
                cuenta.setIdUsuario(cuentasResultSet.getInt("IDUsuario"));
                cuenta.setTipoCuenta(cuentasResultSet.getString("TipoCuenta"));
                cuenta.setNumeroCuenta(cuentasResultSet.getString("NumeroCuenta"));
                cuenta.setCBU(cuentasResultSet.getString("CBU"));
                cuenta.setSaldo(cuentasResultSet.getBigDecimal("Saldo"));
                cuenta.setFechaCreacion(cuentasResultSet.getTimestamp("Fecha_Creacion").toLocalDateTime().toLocalDate());
                cuenta.setEstado(cuentasResultSet.getBoolean("Estado"));

                lista.add(cuenta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public int pedirCuenta (String tipoCuenta, int idCliente)
    {		
    		if ("Ahorros".equals(tipoCuenta) && tieneCuentaAhorros(idCliente))
	        {
	        	return 0;		
	       	}
    		else if ("Corriente".equals(tipoCuenta) && tieneCuentaCorriente(idCliente))
	        {
	        	return 0;
	        }
    	
    	   String query = "INSERT INTO Cuenta (TipoCuenta, NumeroCuenta, CBU, Saldo, Fecha_Creacion, IDUsuario, Estado)" +
	                "VALUES (?, ?, ?, 0, ?, ?,0)";

	        int filas = 0;
	        
	        try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
	             PreparedStatement preparedStatement = cn.prepareStatement(query)) {
	            preparedStatement.setString(1, tipoCuenta);
	            preparedStatement.setInt(2, getNumCuenta());
	            preparedStatement.setString(3, generarCBU());
	            
	            LocalDate fechaActual = LocalDate.now();
	            java.sql.Date sqlFechaActual = java.sql.Date.valueOf(fechaActual);
	            preparedStatement.setDate(4, sqlFechaActual);
           
	            preparedStatement.setLong(5, idCliente);

	            filas = preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return filas;
    }
    
    public int getNumCuenta()
    {
    	 String query = "SELECT MAX(NumeroCuenta) FROM Cuenta";

    	    int nuevoNumeroCuenta = 0;

    	    try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
    	         PreparedStatement preparedStatement = cn.prepareStatement(query);
    	         ResultSet resultSet = preparedStatement.executeQuery()) {

    	        if (resultSet.next()) {
    	            nuevoNumeroCuenta = resultSet.getInt(1);
    	        }
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }

    	    nuevoNumeroCuenta += 10;
    	    
    	    return nuevoNumeroCuenta;
    }
    
    public String generarCBU() {
        int minCBU = 1000;
        int maxCBU = 9999;

        Random random = new Random();
        int numeroCBU;

        while (true) {
            numeroCBU = minCBU + random.nextInt(maxCBU - minCBU + 1);
            
            if (!existeCBU(numeroCBU)) {
                break;
            }
        }

        return String.valueOf(numeroCBU);
    }

    public boolean existeCBU(int numeroCBU) {
        String query = "SELECT COUNT(*) FROM Cuenta WHERE CBU = ?";

        try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
             PreparedStatement preparedStatement = cn.prepareStatement(query)) {
            preparedStatement.setString(1, String.valueOf(numeroCBU));
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0; 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; 
    }
    
    public boolean tieneCuentaAhorros(int idCliente) {
        String query = "SELECT COUNT(*) FROM Cuenta WHERE IDUsuario = ? AND TipoCuenta = 'Ahorros'";
        int cuentaAhorros = 0;

        try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
             PreparedStatement preparedStatement = cn.prepareStatement(query)) {
            preparedStatement.setLong(1, idCliente);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                cuentaAhorros = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(cuentaAhorros == 0)return false;
        else return true;
        
    }

    public boolean tieneCuentaCorriente(int idCliente) {
        String query = "SELECT COUNT(*) FROM Cuenta WHERE IDUsuario = ? AND TipoCuenta = 'Corriente'";
        int cuentaCorriente = 0;

        try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
             PreparedStatement preparedStatement = cn.prepareStatement(query)) {
            preparedStatement.setLong(1, idCliente);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                cuentaCorriente = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(cuentaCorriente == 0)return false;
        else return true;
    }
    
     public int ValidarCuenta(int id)
	 {
    	 String query = "UPDATE Cuenta SET Estado = 1, saldo = CASE WHEN saldo = 0 THEN (saldo + 10000) ELSE saldo END WHERE IDCuenta = ?;";
		 int filas = 0;

	        try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
	             PreparedStatement preparedStatement = cn.prepareStatement(query)) {
	            preparedStatement.setInt(1, id);
	            
	            filas = preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return filas;
	 } 

     public int BloquearCuenta(int id)
	 {
		 String query = "UPDATE Cuenta SET Estado = 0 WHERE IDCuenta = ?;";
		 int filas = 0;

	        try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
	             PreparedStatement preparedStatement = cn.prepareStatement(query)) {
	            preparedStatement.setInt(1, id);
	            
	            filas = preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return filas;
	 }
     
     public String GetNombreCliente(int idUsuario) {
    	    String query = "SELECT Username FROM Usuario WHERE IDUsuario = ?;";
    	    String nombre = null;

    	    try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
    	         PreparedStatement preparedStatement = cn.prepareStatement(query)) {    	       
    	    	 preparedStatement.setInt(1, idUsuario);
    	        
    	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
    	            if (resultSet.next()) {
    	                nombre = resultSet.getString("Username");
    	            }
    	        }
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }

    	    return nombre;
    	}

     
	 public int CambiarSaldo(String saldo, int id, String TipoCuenta )
	 {
		 String query = "UPDATE Cuenta SET Saldo = ? WHERE IDUsuario = ? AND TipoCuenta = ?";
		 int filas = 0;

	        try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
	             PreparedStatement preparedStatement = cn.prepareStatement(query)) {
	        	 preparedStatement.setString(1, saldo);
	             preparedStatement.setInt(2, id);
	             preparedStatement.setString(3, TipoCuenta);
	            
	            filas = preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return filas;
	 }
     
    
}
