package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DireccionDaoInterface;
import entidad.DBException;
import entidad.Direccion;
import entidad.GenericException;
import entidad.Localidad;
import entidad.Provincia;

public class DireccionDAO implements DireccionDaoInterface{
	
	private String host = "jdbc:mysql://127.0.0.1:3306/";
	 private String user = "root";
	 private String pass = "tobias01032004";
	 private String dbName = "bancodb";

	 public DireccionDAO() {
	     try {
	         Class.forName("com.mysql.cj.jdbc.Driver");
	     } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      }
	     	    
	 }
	 
		@Override
		public int modificarDireccion(Direccion direc) {
			
		    String query = "UPDATE direccion SET Calle = ?, Numero = ?, IDLocalidad = ?, IDProvincia = ? WHERE IDDireccion = ?";
		    int filas = 0;

		    try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
		         PreparedStatement preparedStatement = cn.prepareStatement(query)) {
		        preparedStatement.setString(1, direc.getCalle());
		        preparedStatement.setInt(2, direc.getNumero());
		        preparedStatement.setInt(3, direc.get_Localidad().getIdLocalidad());
		        preparedStatement.setInt(4, direc.get_Provincia().getIdProvincia());
		        preparedStatement.setInt(5, direc.getId());

		        filas = preparedStatement.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return filas;
		}
	
	 public Direccion obtenerDireccionPorID(int idDireccion) throws DBException, GenericException {
		    String queryDireccion = "SELECT * FROM Direccion WHERE IDDireccion = ?";
		    try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
		         PreparedStatement preparedStatementDireccion = cn.prepareStatement(queryDireccion)) {
		        preparedStatementDireccion.setInt(1, idDireccion);

		        ResultSet resultSetDireccion = preparedStatementDireccion.executeQuery();

		        if (resultSetDireccion.next()) {
		            Direccion direccion = new Direccion();
		            direccion.setId(resultSetDireccion.getInt("IDDireccion"));
		            direccion.setCalle(resultSetDireccion.getString("Calle"));
		            direccion.setNumero(resultSetDireccion.getInt("Numero"));

		            int idLocalidad = resultSetDireccion.getInt("IDLocalidad");
		            int idProvincia = resultSetDireccion.getInt("IDProvincia");

		            Localidad localidad = obtenerLocalidadPorID(idLocalidad); 
		            Provincia provincia = obtenerProvinciaPorID(idProvincia); 

		            direccion.set_Localidad(localidad);
		            direccion.set_Provincia(provincia);

		            return direccion;
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        throw new DBException("Hubo un problema de conexión con la DB de Clientes");
		    }catch (Exception e){
		    	 e.printStackTrace();
		    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
		    }
		    return null;
		}

		public Localidad obtenerLocalidadPorID(int idLocalidad) throws DBException, GenericException {
		    String queryLocalidad = "SELECT * FROM Localidad WHERE IDLocalidad = ?";
		    try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
		         PreparedStatement preparedStatementLocalidad = cn.prepareStatement(queryLocalidad)) {
		        preparedStatementLocalidad.setInt(1, idLocalidad);

		        ResultSet resultSetLocalidad = preparedStatementLocalidad.executeQuery();

		        if (resultSetLocalidad.next()) {
		            Localidad localidad = new Localidad();
		            localidad.setIdLocalidad(idLocalidad);
		            localidad.setDescripcion(resultSetLocalidad.getString("Descripcion"));
		            return localidad;
		        }
		    }catch (SQLException e) {
		        e.printStackTrace();
		        throw new DBException("Hubo un problema de conexión con la DB de Clientes");
		    }catch (Exception e){
		    	 e.printStackTrace();
		    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
		    }

		    return null;
		}
		
		public Localidad obtenerLocalidadPorDesc(String desc) throws DBException, GenericException {
		    String queryLocalidad = "SELECT * FROM Localidad WHERE Descripcion = ?";
		    try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
		         PreparedStatement preparedStatementLocalidad = cn.prepareStatement(queryLocalidad)) {
		        preparedStatementLocalidad.setString(1, desc);

		        ResultSet resultSetLocalidad = preparedStatementLocalidad.executeQuery();

		        if (resultSetLocalidad.next()) {
		            Localidad localidad = new Localidad();
		            localidad.setIdLocalidad(resultSetLocalidad.getInt("IDLocalidad"));
		            localidad.setDescripcion(resultSetLocalidad.getString("Descripcion"));
		            return localidad;
		        }
		    }catch (SQLException e) {
		        e.printStackTrace();
		        throw new DBException("Hubo un problema de conexión con la DB de Clientes");
		    }catch (Exception e){
		    	 e.printStackTrace();
		    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
		    }

		    return null;
		}

		public Provincia obtenerProvinciaPorID(int idProvincia) throws DBException, GenericException {
		    String queryProvincia = "SELECT * FROM Provincia WHERE IDProvincia = ?";
		    try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
		         PreparedStatement preparedStatementProvincia = cn.prepareStatement(queryProvincia)) {
		        preparedStatementProvincia.setInt(1, idProvincia);

		        ResultSet resultSetProvincia = preparedStatementProvincia.executeQuery();

		        if (resultSetProvincia.next()) {
		            Provincia provincia = new Provincia();
		            provincia.setIdProvincia(idProvincia);
		            provincia.setDescripcion(resultSetProvincia.getString("Descripcion"));
		            return provincia;
		        }
		    }catch (SQLException e) {
		        e.printStackTrace();
		        throw new DBException("Hubo un problema de conexión con la DB de Clientes");
		    }catch (Exception e){
		    	 e.printStackTrace();
		    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
		    }

		    return null;
		}
		
		public Provincia obtenerProvinciaPorDesc(String desc) throws DBException, GenericException {
		    String queryLocalidad = "SELECT * FROM Provincia WHERE Descripcion = ?";
		    try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
		         PreparedStatement preparedStatementLocalidad = cn.prepareStatement(queryLocalidad)) {
		        preparedStatementLocalidad.setString(1, desc);

		        ResultSet resultSetLocalidad = preparedStatementLocalidad.executeQuery();

		        if (resultSetLocalidad.next()) {
		            Provincia pro = new Provincia();
		            pro.setIdProvincia(resultSetLocalidad.getInt("IDProvincia"));
		            pro.setDescripcion(resultSetLocalidad.getString("Descripcion"));
		            return pro;
		        }
		    }catch (SQLException e) {
		        e.printStackTrace();
		        throw new DBException("Hubo un problema de conexión con la DB de Clientes");
		    }catch (Exception e){
		    	 e.printStackTrace();
		    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
		    }

		    return null;
		}



}
