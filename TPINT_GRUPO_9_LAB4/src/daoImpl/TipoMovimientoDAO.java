package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.TipoMovimientoDaoInterface;
import entidad.Cliente;
import entidad.DBException;
import entidad.Direccion;
import entidad.GenericException;
import entidad.TipoMovimiento;

public class TipoMovimientoDAO implements TipoMovimientoDaoInterface {
	
	private String host = "jdbc:mysql://127.0.0.1:3306/";
	 private String user = "root";
	 private String pass = "root";
	 private String dbName = "bancodb";

	 public TipoMovimientoDAO() {
	     try {
	         Class.forName("com.mysql.cj.jdbc.Driver");
	     } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      }
	 }
	
	 public TipoMovimiento getTipoMovimiento(int idTipo, int idCuenta) throws DBException, GenericException
	    {
		    String checkQuery = "SELECT * FROM tiposmovimientos";
		    TipoMovimiento tp = new TipoMovimiento();

		    try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
		            PreparedStatement checkStatement = cn.prepareStatement(checkQuery)) {
		            ResultSet resultSet = checkStatement.executeQuery();

		           if (resultSet.next()) {
		            tp.setId_TipoMovimiento(resultSet.getInt("IDTipo"));
		            tp.setTipoMovimiento(resultSet.getString("Descripcion"));
		           }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        throw new DBException("Hubo un problema de conexión con la DB de Clientes");
		    }catch (Exception e){
		    	 e.printStackTrace();
		    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
		    }

		    return tp;
	    }
	 
	 public TipoMovimiento getTipoxDescripcion(String tipo) throws DBException, GenericException {
		    String checkQuery = "SELECT * FROM tiposmovimientos WHERE UPPER(Descripcion) = UPPER(?)";


		    try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
		         PreparedStatement checkStatement = cn.prepareStatement(checkQuery)) {

		    	 checkStatement.setString(1, tipo);

		        ResultSet resultSet = checkStatement.executeQuery();

		        while (resultSet.next()) {
		            TipoMovimiento tp = new TipoMovimiento();
		            tp.setId_TipoMovimiento(resultSet.getInt("IDTipo"));
		            tp.setTipoMovimiento(resultSet.getString("Descripcion"));
		            return tp;
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        throw new DBException("Hubo un problema de conexión con la DB de Clientes");
		    } catch (Exception e) {
		        e.printStackTrace();
		        throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
		    }

		    return null;
		}

	 
	

}
