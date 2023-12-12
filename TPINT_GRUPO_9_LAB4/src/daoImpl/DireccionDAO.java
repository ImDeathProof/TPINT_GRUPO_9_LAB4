 package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.DireccionDaoInterface;
import entidad.Cliente;
import entidad.Direccion;
import entidad.Localidad;
import entidad.Provincia;
import excepciones.DBException;
import excepciones.GenericException;

public class DireccionDAO implements DireccionDaoInterface{
	
	private String host = "jdbc:mysql://127.0.0.1:3306/";
	 private String user = "root";
	 private String pass = "root";
	 private String dbName = "bancodb";

	 public DireccionDAO() {
	     try {
	         Class.forName("com.mysql.cj.jdbc.Driver");
	     } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      }
	     	    
	 }
	 
	 public Direccion addDireccion(Direccion direc) throws GenericException, DBException {
		 if(mismaDireccion(direc))
		 {
			 return obtenerDireccionSinID(direc);
		 }
		 else if (!checkProvinciaLocalidad(direc.get_Provincia().getIdProvincia(), direc.get_Localidad().getIdLocalidad())) {
		        return null;
		    }
		 int filas = 0;
		    String query = "INSERT INTO Direccion (Calle, Numero, IDLocalidad, IDProvincia) VALUES (?, ?, ?, ?)";

		    try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
		         PreparedStatement preparedStatement = cn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
		        preparedStatement.setString(1, direc.getCalle());
		        preparedStatement.setInt(2, direc.getNumero());
		        preparedStatement.setInt(3, direc.get_Localidad().getIdLocalidad());
		        preparedStatement.setInt(4, direc.get_Provincia().getIdProvincia());

		        filas = preparedStatement.executeUpdate();

		        if (filas > 0) {
		            // Obtener las claves generadas (en este caso, debería ser solo una clave)
		            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
		                if (generatedKeys.next()) {
		                    // Establecer el ID generado en el objeto Direccion
		                    direc.setId(generatedKeys.getInt(1));
		                } else {
		                    // Manejar el caso en el que no se generó ninguna clave
		                    throw new DBException("No se generó ninguna clave para la dirección.");
		                }
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        throw new DBException("Hubo un problema de conexión con la DB de Clientes");
		    } catch (Exception e) {
		        e.printStackTrace();
		        throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
		    }

		    return direc;
		}
	 
	 
	 public boolean mismaDireccion(Direccion direc) {
		 int filas = 0;
		 
		 String query = "SELECT COUNT(*) FROM Direccion WHERE Calle = ? AND Numero = ? AND IDLocalidad = ? AND IDProvincia = ?";
		 
		 try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
				 PreparedStatement preparedStatement = cn.prepareStatement(query)) {
			 preparedStatement.setString(1, direc.getCalle());
			 preparedStatement.setInt(2, direc.getNumero());
			 preparedStatement.setInt(3, direc.get_Localidad().getIdLocalidad());
			 preparedStatement.setInt(4, direc.get_Provincia().getIdProvincia());
			 
			 try (ResultSet resultSet = preparedStatement.executeQuery()) {
				 if (resultSet.next()) {
					 filas = resultSet.getInt(1);
				 }
			 }
		 } catch (SQLException e) {
			 e.printStackTrace();
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 
		 return filas > 0;
	 }
	 
	 public Direccion obtenerDireccionSinID(Direccion direc) throws DBException, GenericException
	 {
		  String queryDireccion = "SELECT * FROM Direccion WHERE Calle = ? AND Numero = ? AND IDLocalidad = ? AND IDProvincia = ?";
		    try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
		         PreparedStatement preparedStatementDireccion = cn.prepareStatement(queryDireccion)) {
		        preparedStatementDireccion.setString(1, direc.getCalle() );
		        preparedStatementDireccion.setInt(2, direc.getNumero());
		        preparedStatementDireccion.setInt(3, direc.get_Localidad().getIdLocalidad());
		        preparedStatementDireccion.setInt(4, direc.get_Provincia().getIdProvincia());

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
	 
		@Override
		public int modificarDireccion(Direccion direc) {
			
			 if (!checkProvinciaLocalidad(direc.get_Provincia().getIdProvincia(), direc.get_Localidad().getIdLocalidad())) {
			        return 0;
			    }
			
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
		
		private boolean checkProvinciaLocalidad(int idProvincia, int idLocalidad) {
		    String query = "SELECT IDProvincia FROM Localidad WHERE IDLocalidad = ?";
		    
		    try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
		         PreparedStatement preparedStatement = cn.prepareStatement(query)) {
		        preparedStatement.setInt(1, idLocalidad);

		        try (ResultSet resultSet = preparedStatement.executeQuery()) {
		            if (resultSet.next()) {
		                int idProvinciaEnBaseDeDatos = resultSet.getInt("IDProvincia");
		                return idProvinciaEnBaseDeDatos == idProvincia;
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return false;
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

		public ArrayList<Provincia> getAllProvincias() throws DBException, GenericException {
			
			  ArrayList<Provincia> lista = new ArrayList<>();
			    
			    try {
			        Class.forName("com.mysql.cj.jdbc.Driver");
			    } catch (ClassNotFoundException e) {
			        e.printStackTrace();
			    }

			    try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
			         PreparedStatement preparedStatement = cn.prepareStatement(
			                 "SELECT IDProvincia, Descripcion from Provincia")) {

			        try (ResultSet rs = preparedStatement.executeQuery()) {
			            while (rs.next()) {
			                Provincia prov = new Provincia();
			                prov.setIdProvincia(rs.getInt("IDProvincia"));
			                prov.setDescripcion(rs.getString("Descripcion"));
			                lista.add(prov);
			            }
			        }

			    } catch (SQLException e) {
			        e.printStackTrace();
			        throw new DBException("Hubo un problema de conexión con la DB de Clientes");
			    }catch (Exception e){
			    	 e.printStackTrace();
			    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
			    }

			    return lista;
			
		}
		
		public ArrayList<Localidad> getAllLocalidades(int provId) throws DBException, GenericException {
		
			ArrayList<Localidad> lista = new ArrayList<>();
			
			 String queryLocalidad = "SELECT * FROM Localidad WHERE IDProvincia = ?";
			    try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
			         PreparedStatement preparedStatementLocalidad = cn.prepareStatement(queryLocalidad)) {
			        preparedStatementLocalidad.setInt(1, provId);

			        ResultSet resultSetLocalidad = preparedStatementLocalidad.executeQuery();

			        while (resultSetLocalidad.next()) {
			            Localidad lc = new Localidad();
			            lc.setIdLocalidad(resultSetLocalidad.getInt("IDLocalidad"));
			            lc.setDescripcion(resultSetLocalidad.getString("Descripcion"));
			            lc.setProvincia(obtenerProvinciaPorID(resultSetLocalidad.getInt("IDProvincia")));
			            lista.add(lc);
			        }
			    }catch (SQLException e) {
			        e.printStackTrace();
			        throw new DBException("Hubo un problema de conexión con la DB de Clientes");
			    }catch (Exception e){
			    	 e.printStackTrace();
			    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
			    }

			    return lista;
			
		}



}
