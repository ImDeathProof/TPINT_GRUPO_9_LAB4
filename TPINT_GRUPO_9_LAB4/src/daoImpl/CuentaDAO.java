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
import entidad.DBException;
import entidad.TipoMovimiento;
import negocio.CuentaNeg;
import negocio.MovimientoNeg;
import negocio.TipoMovimientoNeg;
import negocioImpl.CuentaNegImpl;
import negocioImpl.MovimientoNegImpl;
import negocioImpl.TipoMovimientoNegImpl;
import entidad.ValidateException;
import entidad.GenericException;


public class CuentaDAO implements CuentaDaoInterface {
    private String host = "jdbc:mysql://127.0.0.1:3306/";
    private String user = "root";
    private String pass = "tobias01032004";
    private String dbName = "bancodb";
    
    MovimientoNeg cuNeg = new MovimientoNegImpl();
    TipoMovimientoNeg tMovNeg = new TipoMovimientoNegImpl();

    public CuentaDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Cuenta> obtenerCuentasPorUsuario(int idUsuario) throws DBException, GenericException{
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
            throw new DBException("Hubo un problema de conexión con la DB de Cuentas");
        }catch (Exception e){
	    	 e.printStackTrace();
	    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
	    }
        return lista;
    }
    
    public int cantidadCuentasPorUsuario(int idUsuario) throws DBException, GenericException{
    	String cuentasQuery = "SELECT COUNT(*) as cuenta_count from Cuenta where IdUsuario = ?";
        int cantidadDeCuentas = 0;

        try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
             PreparedStatement cuentasStatement = cn.prepareStatement(cuentasQuery)) {
            cuentasStatement.setInt(1, idUsuario);
            ResultSet cuentasResultSet = cuentasStatement.executeQuery();

            if (cuentasResultSet.next()) {
                cantidadDeCuentas = cuentasResultSet.getInt("cuenta_count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Hubo un problema de conexión con la DB de Cuentas");
        }catch (Exception e){
	    	 e.printStackTrace();
	    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
	    }
        return cantidadDeCuentas;
    }

    
    
    public ArrayList<Cuenta> obtenerTodasLasCuentas() throws DBException, GenericException{
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
            throw new DBException("Hubo un problema de conexión con la DB de Cuentas");
        }catch (Exception e){
	    	 e.printStackTrace();
	    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
	    }
        return lista;
    }
    
    public int pedirCuenta (String tipoCuenta, int idCliente) throws DBException, ValidateException, GenericException
    {		
//    		if ("Ahorros".equals(tipoCuenta) && tieneCuentaAhorros(idCliente))
//	        {
//	        	return 0;		
//	       	}
//    		else if ("Corriente".equals(tipoCuenta) && tieneCuentaCorriente(idCliente))
//	        {
//	        	return 0;
//	        }
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
	            throw new DBException("Hubo un problema de conexión con la DB de Cuentas");
	        }catch (Exception e){
		    	 e.printStackTrace();
		    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
		    }

	        return filas;
    }
    
    public int getNumCuenta() throws DBException, GenericException
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
    	        throw new DBException("Hubo un problema de conexión con la DB de Cuentas");
    	    }catch (Exception e){
		    	 e.printStackTrace();
		    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
		    }

    	    nuevoNumeroCuenta += 10;
    	    
    	    return nuevoNumeroCuenta;
    }
    @Override
    public String generarCBU() throws ValidateException, GenericException{
        int minCBU = 1000;
        int maxCBU = 9999;

        Random random = new Random();
        int numeroCBU;

       try { while (true) {
            numeroCBU = minCBU + random.nextInt(maxCBU - minCBU + 1);
            
            if (!existeCBU(numeroCBU)) {
                break;
            }
        }

        return String.valueOf(numeroCBU);
    }catch (Exception e){
   	 e.printStackTrace();
   	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
   }
    }
    @Override
    public boolean existeCBU(int numeroCBU) throws ValidateException, GenericException{
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
            throw new ValidateException("Hubo un problema al validar datos en la DB");
        }catch (Exception e){
	    	 e.printStackTrace();
	    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
	    }

        return false; 
    }
    
    public boolean tieneCuentaAhorros(int idCliente) throws ValidateException, GenericException{
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
            throw new ValidateException("Hubo un problema al validar datos en la DB");
        }catch (Exception e){
	    	 e.printStackTrace();
	    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
	    }

        if(cuentaAhorros == 0)return false;
        else return true;
        
    }

    public boolean tieneCuentaCorriente(int idCliente) throws ValidateException, GenericException{
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
            throw new ValidateException("Hubo un problema al validar datos en la DB");
        }catch (Exception e){
	    	 e.printStackTrace();
	    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
	    }

        if(cuentaCorriente == 0)return false;
        else return true;
    }
    
    public int ValidarCuenta(int id) throws ValidateException, DBException, GenericException
  	 {
      	 String query = "UPDATE Cuenta SET Estado = 1, saldo = CASE WHEN saldo = 0 THEN (saldo + 10000) ELSE saldo END WHERE IDCuenta = ?;";
  		 int filas = 0;

  	        try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
  	             PreparedStatement preparedStatement = cn.prepareStatement(query)) {
  	             preparedStatement.setInt(1, id);
  	            Cuenta ct = obtenerCuentaPorID(id);
  	            if(!ct.getEstado()) {
  	            	filas = preparedStatement.executeUpdate();
  	            	if(filas > 0) {
		            	cuNeg.insertMovimiento(ct.getIdCuenta(),new BigDecimal(10000), tMovNeg.getTipoxDescripcion("Alta de cuenta"));		            	
		            }
  	            }else {
  	            	filas = preparedStatement.executeUpdate();
  	            	cuNeg.insertMovimiento(tMovNeg.getTipoxDescripcion("Alta de cuenta").getId_TipoMovimiento(), id);
  	            }
  	                        
  	        } catch (SQLException e) {
  	            e.printStackTrace();
  	          throw new ValidateException("Hubo un problema al validar datos en la DB");
  	        }catch (Exception e){
		    	 e.printStackTrace();
		    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
		    }
  	        
  	        return filas;
  	 } 
    @Override
    public int getCuentaFromCBU(String CBU) throws DBException, GenericException{
        String query = "SELECT IDCuenta AS cuenta FROM Cuenta WHERE CBU = ?;";

        try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
             PreparedStatement preparedStatement = cn.prepareStatement(query)) {
            preparedStatement.setString(1, CBU);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("cuenta");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Hubo un problema de conexión con la DB de Cuentas");
        }catch (Exception e){
	    	 e.printStackTrace();
	    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
	    }

        return -1;
    }
    
    public int getCuentaFromUserID(int userID, String tipoCuenta) throws DBException, GenericException{
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
            throw new DBException("Hubo un problema de conexión con la DB de Cuentas");
        }catch (Exception e){
	    	 e.printStackTrace();
	    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
	    }

        return -1;
    }


     public int BloquearCuenta(int id) throws DBException, GenericException
	 {
		 String query = "UPDATE Cuenta SET Estado = 0 WHERE IDCuenta = ?;";
		 int filas = 0;

	        try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
	             PreparedStatement preparedStatement = cn.prepareStatement(query)) {
	            preparedStatement.setInt(1, id);
	            
	            filas = preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new DBException("Hubo un problema de conexión con la DB de Cuentas");
	        }catch (Exception e){
		    	 e.printStackTrace();
		    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
		    }

	        return filas;
	 }
     
     public String GetNombreCliente(int idUsuario) throws DBException, GenericException{
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
    	        throw new DBException("Hubo un problema de conexión con la DB de Cuentas");
    	    }catch (Exception e){
		    	 e.printStackTrace();
		    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
		    }

    	    return nombre;
    	}

     
	 public int CambiarSaldo(BigDecimal saldo, int id, int IDCuenta ) throws DBException, GenericException
	 {
		 String query = "UPDATE Cuenta SET Saldo = ? WHERE IDUsuario = ? AND IDCuenta = ?";
		 int filas = 0;

	        try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
	             PreparedStatement preparedStatement = cn.prepareStatement(query)) {
	        	 preparedStatement.setBigDecimal(1, saldo);
	             preparedStatement.setInt(2, id);
	             preparedStatement.setInt(3, IDCuenta);
	            
	            filas = preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new DBException("Hubo un problema de conexión con la DB de Cuentas");
	        }catch (Exception e){
		    	 e.printStackTrace();
		    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
		    }

	        return filas;
	 }
	 
 	 public int CambiarSaldo(BigDecimal saldo, String CBU) throws DBException, GenericException
 	 {
 	    BigDecimal saldoActual = getDineroxCuenta(CBU);

 	    BigDecimal nuevoSaldo = saldoActual.add(saldo);
 		 
 		 String query = "UPDATE Cuenta SET Saldo = ? WHERE CBU = ?";
 		 
		 int filas = 0;

	        try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
	             PreparedStatement preparedStatement = cn.prepareStatement(query)) {
	        	 preparedStatement.setBigDecimal(1, nuevoSaldo);
	             preparedStatement.setString(2,CBU);
	            
	            filas = preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new DBException("Hubo un problema de conexión con la DB de Cuentas");
	        }catch (Exception e){
		    	 e.printStackTrace();
		    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
		    }

	        return filas;
 	 }
	 

	 public ArrayList<Cuenta> obtenerCuentasPaginadas(int pageNumber, int pageSize) throws DBException, GenericException{
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
	 	       throw new DBException("Hubo un problema de conexión con la DB de Cuentas");
	 	    }catch (Exception e){
		    	 e.printStackTrace();
		    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
		    }
	 	    return lista;
	 	}
	  
	 public int getCantPaginas() throws DBException, GenericException{
	 		 
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
	 		       throw new DBException("Hubo un problema de conexión con la DB de Cuentas");
	 		    }catch (Exception e){
			    	 e.printStackTrace();
			    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
			    }

	 		    return cant;
	 }
	 	 
	 	
	//public int transferirDinero(BigDecimal monto, int userID, String CBUCuentaDestinataria, String tipoCuenta) throws DBException, GenericException{	   
	public int transferirDinero(BigDecimal monto, int userID, String CBUCuentaDestinataria, int IDCuenta) throws DBException, GenericException{	   

	 		try {
	 			int filasEmisora = -1;
		 		int filasDestinataria = -1;
		 		Cuenta cn = obtenerCuentaPorID(IDCuenta);
		 		if (getDineroxCuenta(cn.getCBU()).compareTo(monto) >= 0)
		 		{	 			
		 			filasEmisora = CambiarSaldo(monto.negate(), cn.getCBU());
		 	        filasDestinataria = CambiarSaldo(monto, CBUCuentaDestinataria);
		 	        
		 	       cuNeg.insertMovimiento(cn.getIdCuenta(), monto.negate(), tMovNeg.getTipoxDescripcion("Deposito"));
		 	       cuNeg.insertMovimiento(getCuentaFromCBU(CBUCuentaDestinataria),monto, tMovNeg.getTipoxDescripcion("Deposito"));
		 		}
		 		
		 	    if (filasEmisora > 0 && filasDestinataria > 0) {
		 	        return filasEmisora + filasDestinataria;
		 	    } else {
		 	        return -1;
		 	    }
	 		}catch (DBException e) {
 		        e.printStackTrace();
 		       throw new DBException("Hubo un problema de conexión con la DB de Cuentas");
 		    }catch (Exception e){
		    	 e.printStackTrace();
		    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
		    }
	 	}
	 	
	 public String getCBU(int userID, String tipoCuenta) throws DBException, GenericException{
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
	 	       throw new DBException("Hubo un problema de conexión con la DB de Cuentas"); 	        	 	        
	 	    }catch (Exception e){
		    	 e.printStackTrace();
		    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
		    }

	 	    return saldo.toString();
	 }
	 @Override
	 public BigDecimal getDineroxCuenta(String cBU) throws DBException, GenericException{
	 	    BigDecimal saldo = BigDecimal.ZERO;

	 	    String query = "SELECT saldo FROM Cuenta WHERE CBU = ?;";

	 	    try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
	 	         PreparedStatement ps = cn.prepareStatement(query)) {
	 	        ps.setString(1, cBU);

	 	        try (ResultSet rs = ps.executeQuery()) {
	 	            if (rs.next()) {
	 	                saldo = rs.getBigDecimal("saldo");
	 	            }
	 	        }
	 	    } catch (SQLException e) {
	 	        e.printStackTrace();
	 	       throw new DBException("Hubo un problema de conexión con la DB de Cuentas");
	 	    }catch (Exception e){
		    	 e.printStackTrace();
		    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
		    }

	 	    return saldo;
	 	}

	@Override
	public Cuenta obtenerCuentaCorrientePorUsuario(int idUsuario) throws DBException, GenericException{
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
	            throw new DBException("Hubo un problema de conexión con la DB de Cuentas");
	        }catch (Exception e){
		    	 e.printStackTrace();
		    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
		    }
	        
			return cuenta;
		}

	@Override
	public Cuenta obtenerCuentaAhorroPorUsuario(int idUsuario) throws DBException, GenericException{
			// TODO Auto-generated method stub
			Cuenta cuenta = new Cuenta();
			
			String query = "SELECT * FROM Cuenta WHERE IdUsuario = ? and TipoCuenta = ?;";

			try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
			         PreparedStatement preparedStatement = cn.prepareStatement(query)) {

			        preparedStatement.setInt(1, idUsuario);
			        preparedStatement.setString(2, "Ahorros");

			        try (ResultSet resultSet = preparedStatement.executeQuery()) {
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
			        }

			    } catch (SQLException e) {
	            e.printStackTrace();
	            throw new DBException("Hubo un problema de conexión con la DB de Cuentas");
	        }catch (Exception e){
		    	 e.printStackTrace();
		    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
		    }
	        
			return cuenta;
	}

	@Override
	public Cuenta obtenerCuentaPorID(int idCuenta) throws DBException, GenericException{
		// TODO Auto-generated method stub
		Cuenta cuenta = new Cuenta();
		
		String query = "SELECT * FROM Cuenta WHERE IdCuenta = ?;";

		try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
		         PreparedStatement preparedStatement = cn.prepareStatement(query)) {

		        preparedStatement.setInt(1, idCuenta);

		        try (ResultSet resultSet = preparedStatement.executeQuery()) {
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
		        }

		    } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Hubo un problema de conexión con la DB de Cuentas");
        }catch (Exception e){
	    	 e.printStackTrace();
	    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
	    }
        
		return cuenta;
	}

	@Override
	public BigDecimal obtenerSaldo(int IDCuenta) throws DBException, GenericException{
		BigDecimal saldo = new BigDecimal(0);

 	    String query = "SELECT Saldo FROM Cuenta WHERE IDCuenta = ?;";

 	    try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
 	         PreparedStatement ps = cn.prepareStatement(query)) {
 	         ps.setInt(1, IDCuenta);


 	        try (ResultSet rs = ps.executeQuery()) {
 	            if (rs.next()) {
 	                saldo = rs.getBigDecimal("Saldo");
 	            }
 	        }
 	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new DBException("Hubo un problema de conexión con la DB de Cuentas");
	    }catch (Exception e){
	    	 e.printStackTrace();
	    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
	    }

 	    return saldo;
	}

	@Override
	public int Debitar(int IDCuenta, BigDecimal saldo) throws DBException, GenericException{
		// TODO Auto-generated method stub
		int filas = 0;
		String query = "UPDATE cuenta SET Saldo = ? WHERE IDCuenta = ?";
		try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
	            PreparedStatement preparedStatement = cn.prepareStatement(query)) {
			preparedStatement.setBigDecimal(1, saldo);
			preparedStatement.setInt(2, IDCuenta);
			filas = preparedStatement.executeUpdate();
		}catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Hubo un problema de conexión con la DB de Cuentas");
       }catch (Exception e){
	    	 e.printStackTrace();
	    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
	    }

        return filas;
	}
}
