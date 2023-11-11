package daoImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import dao.CuentaDaoInterface;
import entidad.Cuenta;


public class CuentaDAO implements CuentaDaoInterface {
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
  	            insertMovimiento(1, id);
  	            
  	        } catch (SQLException e) {
  	            e.printStackTrace();
  	        }

  	        
  	        return filas;
  	 } 
    
    public int insertMovimiento(int idTipo, int idCuenta)
    {
    	String query = "INSERT INTO Movimiento (Monto, Fecha, Detalles, IDTipo, IDUsuario, IDCuenta) VALUES (10000, NOW(), 'Alta de cuenta', 1, ?, ?)";
  		 int filas = 0;

  	        try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
  	             PreparedStatement preparedStatement = cn.prepareStatement(query)) {
  	            preparedStatement.setInt(1, getUserFromCuenta(idCuenta));
  	            preparedStatement.setInt(2, idCuenta);
  	            
  	            filas = preparedStatement.executeUpdate();
  	        } catch (SQLException e) {
  	            e.printStackTrace();
  	        }
  	        
  	        return filas;
    }
    
    public int insertMovimiento(int idCuenta,BigDecimal monto)
    {
    	String query = "INSERT INTO Movimiento (Monto, Fecha, Detalles, IDTipo, IDUsuario, IDCuenta) VALUES (?, NOW(), 'Trasferencia', 4, ?, ?)";
  		 int filas = 0;

  	        try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
  	             PreparedStatement preparedStatement = cn.prepareStatement(query)) {
  	        	 preparedStatement.setBigDecimal(1, monto);
  	             preparedStatement.setInt(2, getUserFromCuenta(idCuenta));
  	             preparedStatement.setInt(3, idCuenta);
  	            
  	            filas = preparedStatement.executeUpdate();
  	        } catch (SQLException e) {
  	            e.printStackTrace();
  	        }
  	        
  	        return filas;
    }
    
    public int getUserFromCuenta(int idCuenta) {
        String query = "SELECT IDUsuario AS user FROM Cuenta WHERE IDCuenta = ?;";

        try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
             PreparedStatement preparedStatement = cn.prepareStatement(query)) {
            preparedStatement.setInt(1, idCuenta);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("user");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }
    
    public int getCuentaFromCBU(long CBU) {
        String query = "SELECT IDCuenta AS cuenta FROM Cuenta WHERE CBU = ?;";

        try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
             PreparedStatement preparedStatement = cn.prepareStatement(query)) {
            preparedStatement.setLong(1, CBU);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("cuenta");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }
    
    public int getCuentaFromUserID(int userID, String tipoCuenta) {
        String query = "SELECT IDCuenta AS cuenta FROM Cuenta WHERE IDUsuario = ? and TipoCuenta = ?;";

        try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
             PreparedStatement preparedStatement = cn.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            preparedStatement.setString(2, tipoCuenta);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("cuenta");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
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

     
	 public int CambiarSaldo(BigDecimal saldo, int id, String TipoCuenta )
	 {
		 String query = "UPDATE Cuenta SET Saldo = ? WHERE IDUsuario = ? AND TipoCuenta = ?";
		 int filas = 0;

	        try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
	             PreparedStatement preparedStatement = cn.prepareStatement(query)) {
	        	 preparedStatement.setBigDecimal(1, saldo);
	             preparedStatement.setInt(2, id);
	             preparedStatement.setString(3, TipoCuenta);
	            
	            filas = preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return filas;
	 }
	 
 	 public int CambiarSaldo(BigDecimal saldo, long CBU)
 	 {
 	    BigDecimal saldoActual = getDineroxCuenta(CBU);

 	    BigDecimal nuevoSaldo = saldoActual.add(saldo);
 		 
 		 String query = "UPDATE Cuenta SET Saldo = ? WHERE CBU = ?";
 		 
		 int filas = 0;

	        try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
	             PreparedStatement preparedStatement = cn.prepareStatement(query)) {
	        	 preparedStatement.setBigDecimal(1, nuevoSaldo);
	             preparedStatement.setLong(2,CBU);
	            
	            filas = preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return filas;
 	 }
	 

	 public ArrayList<Cuenta> obtenerCuentasPaginadas(int pageNumber, int pageSize) {
	 	    try {
	 	        Class.forName("com.mysql.cj.jdbc.Driver");
	 	    } catch (ClassNotFoundException e) {
	 	        e.printStackTrace();
	 	    }

	 	    ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
	 	    Connection cn = null;
	 	    try {
	 	        cn = DriverManager.getConnection(host + dbName, user, pass);
	 	        Statement st = cn.createStatement();
	 	        int offset = (pageNumber - 1) * pageSize; 

	 	        String query = "SELECT * FROM Cuenta" +
	 	                       " ORDER BY IDCuenta" +
	 	                       " LIMIT " + pageSize +
	 	                       " OFFSET " + offset;
	 	        ResultSet rs = st.executeQuery(query);

	 	        while (rs.next()) {
	 	        	Cuenta cuenta = new Cuenta();
	                 cuenta.setIdCuenta(rs.getInt("IDCuenta"));
	                 cuenta.setIdUsuario(rs.getInt("IDUsuario"));
	                 cuenta.setTipoCuenta(rs.getString("TipoCuenta"));
	                 cuenta.setNumeroCuenta(rs.getString("NumeroCuenta"));
	                 cuenta.setCBU(rs.getString("CBU"));
	                 cuenta.setSaldo(rs.getBigDecimal("Saldo"));
	                 cuenta.setFechaCreacion(rs.getTimestamp("Fecha_Creacion").toLocalDateTime().toLocalDate());
	                 cuenta.setEstado(rs.getBoolean("Estado"));
	 				 
	 				 lista.add(cuenta);
	 	        }

	 	        cn.close();
	 	    } catch (SQLException e) {
	 	        e.printStackTrace();
	 	    }
	 	    return lista;
	 	}
	  
	 	 public int getCantPaginas() {
	 		 
	 		    int cant = 0;

	 		    String query = "SELECT CEIL(COUNT(*) / 5) AS paginas FROM Cuenta;";

	 		    try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
	 		         Statement st = cn.createStatement();
	 		         ResultSet rs = st.executeQuery(query)) {
	 		        if (rs.next()) {
	 		            cant = rs.getInt("paginas");
	 		        }
	 		    } catch (SQLException e) {
	 		        e.printStackTrace();
	 		    }

	 		    return cant;
	 		}
	 	 
	 	
	 	public int transferirDinero(BigDecimal monto, int userID, long CBUCuentaDestinataria, String tipoCuenta) {	   

	 		int filasEmisora = -1;
	 		int filasDestinataria = -1;
	 		
	 		if (getDineroxCuenta(getCBU(userID,tipoCuenta)).compareTo(monto) >= 0)
	 		{	 			
	 			filasEmisora = CambiarSaldo(monto.negate(), getCBU(userID,tipoCuenta));
	 	        filasDestinataria = CambiarSaldo(monto, CBUCuentaDestinataria);
	 	        
	 	        insertMovimiento(getCuentaFromUserID(userID, tipoCuenta), monto.negate());
	 	        insertMovimiento(getCuentaFromCBU(CBUCuentaDestinataria),monto);
	 		}
	 		
	 	    if (filasEmisora > 0 && filasDestinataria > 0) {
	 	        return filasEmisora + filasDestinataria;
	 	    } else {
	 	        return -1;
	 	    }
	 	}
	 	
	 	public long getCBU(int userID, String tipoCuenta) {
	 	    Long saldo = 0L;

	 	    String query = "SELECT CBU FROM Cuenta WHERE IdUsuario = ? and TipoCuenta = ?;";

	 	    try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
	 	         PreparedStatement ps = cn.prepareStatement(query)) {
	 	         ps.setInt(1, userID);
	 	         ps.setString(2, tipoCuenta);

	 	        try (ResultSet rs = ps.executeQuery()) {
	 	            if (rs.next()) {
	 	                saldo = rs.getLong("CBU");
	 	            }
	 	        }
	 	    } catch (SQLException e) {
	 	        e.printStackTrace();
	 	    }

	 	    return saldo;
	 	}
	 	
	 	public BigDecimal getDineroxCuenta(long CBU) {
	 	    BigDecimal saldo = BigDecimal.ZERO;

	 	    String query = "SELECT saldo FROM Cuenta WHERE CBU = ?;";

	 	    try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
	 	         PreparedStatement ps = cn.prepareStatement(query)) {
	 	        ps.setLong(1, CBU);

	 	        try (ResultSet rs = ps.executeQuery()) {
	 	            if (rs.next()) {
	 	                saldo = rs.getBigDecimal("saldo");
	 	            }
	 	        }
	 	    } catch (SQLException e) {
	 	        e.printStackTrace();
	 	    }

	 	    return saldo;
	 	}

		@Override
		public Cuenta obtenerCuentaCorrientePorUsuario(int idUsuario) {
			// TODO Auto-generated method stub
			Cuenta cuenta = new Cuenta();
			
			String query = "SELECT * FROM Cuenta WHERE IdUsuario = ? and TipoCuenta = ?;";

	        try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
	             PreparedStatement preparedStatement = cn.prepareStatement(query)) {
	            preparedStatement.setInt(1, idUsuario);
	            preparedStatement.setString(2, "Corriente");
	            ResultSet resultSet = preparedStatement.executeQuery();

	            if (resultSet.next()) {
	            	cuenta.setIdCuenta(resultSet.getInt("IDCuenta"));
	                cuenta.setIdUsuario(resultSet.getInt("IDUsuario"));
	                cuenta.setTipoCuenta(resultSet.getString("TipoCuenta"));
	                cuenta.setNumeroCuenta(resultSet.getString("NumeroCuenta"));
	                cuenta.setCBU(resultSet.getString("CBU"));
	                cuenta.setSaldo(resultSet.getBigDecimal("Saldo"));
	                cuenta.setFechaCreacion(resultSet.getTimestamp("Fecha_Creacion").toLocalDateTime().toLocalDate());
	                cuenta.setEstado(resultSet.getBoolean("Estado"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
			return cuenta;
		}

		@Override
		public Cuenta obtenerCuentaAhorroPorUsuario(int idUsuario) {
			// TODO Auto-generated method stub
			Cuenta cuenta = new Cuenta();
			
			String query = "SELECT * FROM Cuenta WHERE IdUsuario = ? and TipoCuenta = ?;";

	        try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
	             PreparedStatement preparedStatement = cn.prepareStatement(query)) {
	            preparedStatement.setInt(1, idUsuario);
	            preparedStatement.setString(2, "Ahorros");
	            ResultSet resultSet = preparedStatement.executeQuery();

	            if (resultSet.next()) {
	            	cuenta.setIdCuenta(resultSet.getInt("IDCuenta"));
	                cuenta.setIdUsuario(resultSet.getInt("IDUsuario"));
	                cuenta.setTipoCuenta(resultSet.getString("TipoCuenta"));
	                cuenta.setNumeroCuenta(resultSet.getString("NumeroCuenta"));
	                cuenta.setCBU(resultSet.getString("CBU"));
	                cuenta.setSaldo(resultSet.getBigDecimal("Saldo"));
	                cuenta.setFechaCreacion(resultSet.getTimestamp("Fecha_Creacion").toLocalDateTime().toLocalDate());
	                cuenta.setEstado(resultSet.getBoolean("Estado"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
			return cuenta;
		}

	 	 
	

	 	 
	 	 
     
    
}
