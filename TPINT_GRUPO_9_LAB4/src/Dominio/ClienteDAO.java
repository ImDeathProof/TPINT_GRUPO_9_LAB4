package Dominio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO {
	
	 private String host = "jdbc:mysql://127.0.0.1:3306/";
	 private String user = "root";
	 private String pass = "root";
	 private String dbName = "bancodb";

	 public ClienteDAO() {
	     try {
	         Class.forName("com.mysql.cj.jdbc.Driver");
	     } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      }
  }
	
	 public int agregarUsuario(Cliente cliente) throws SQLException {
	        String query = "INSERT INTO Usuario (Username, Pass, Nombre, Apellido, DNI, CUIL, Sexo, Nacionalidad, FechaNacimiento, Direccion, Localidad, Provincia, Mail, Telefono, Admin) " +
	                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	        int filas = 0;

	        try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
	             PreparedStatement preparedStatement = cn.prepareStatement(query)) {
	            preparedStatement.setString(1, cliente.get_Usuario());
	            preparedStatement.setString(2, cliente.get_Contrasena());
	            preparedStatement.setString(3, cliente.get_Nombre());
	            preparedStatement.setString(4, cliente.get_Apellido());
	            preparedStatement.setLong(5, cliente.get_DNI());
	            preparedStatement.setLong(6, cliente.get_CUIL());
	            preparedStatement.setInt(7, cliente.is_Sexo() ? 1 : 0);
	            preparedStatement.setString(8, cliente.get_Nacionalidad());

	            java.sql.Date sqlFechaNacimiento = java.sql.Date.valueOf(cliente.get_FechaNacimiento());
	            preparedStatement.setDate(9, sqlFechaNacimiento);
	            
	            preparedStatement.setString(10, cliente.get_Direccion().toString());
	            preparedStatement.setString(11, cliente.get_Localidad());
	            preparedStatement.setString(12, cliente.get_Provincia());
	            preparedStatement.setString(13, cliente.get_Email());
	            preparedStatement.setLong(14, cliente.get_Telefono());
	            preparedStatement.setInt(15, cliente.is_Admin() ? 1 : 0);

	            filas = preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return filas;
	    }
	 
	 public Cliente BuscarUsuario(Cliente cliente) throws SQLException {
	
		 String username = cliente.get_Usuario();
		 String password = cliente.get_Contrasena();

		 String checkQuery = "SELECT * FROM Usuario WHERE Username = ? AND Pass = ?";

		 try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
		      PreparedStatement checkStatement = cn.prepareStatement(checkQuery)) {
		     checkStatement.setString(1, username);
		     checkStatement.setString(2, password);

		     ResultSet resultSet = checkStatement.executeQuery();

		     if (resultSet.next()) {
		         Cliente usuarioEncontrado = new Cliente();
		         usuarioEncontrado.set_Usuario(resultSet.getString("Username"));
		         usuarioEncontrado.set_Contrasena(resultSet.getString("Pass"));
		         usuarioEncontrado.set_Nombre(resultSet.getString("Nombre"));
		         usuarioEncontrado.set_Apellido(resultSet.getString("Apellido"));
		         usuarioEncontrado.set_DNI(resultSet.getLong("DNI"));
		         usuarioEncontrado.set_CUIL(resultSet.getLong("CUIL"));
		         usuarioEncontrado.set_Sexo(resultSet.getInt("Sexo") == 1); // 1 indica femenino, cualquier otro valor indica masculino
		         usuarioEncontrado.set_Nacionalidad(resultSet.getString("Nacionalidad"));
		         usuarioEncontrado.set_FechaNacimiento(resultSet.getDate("FechaNacimiento").toLocalDate());
		         usuarioEncontrado.set_Direccion(resultSet.getString("Direccion"));
		         usuarioEncontrado.set_Localidad(resultSet.getString("Localidad"));
		         usuarioEncontrado.set_Provincia(resultSet.getString("Provincia"));
		         usuarioEncontrado.set_Email(resultSet.getString("Mail"));
		         usuarioEncontrado.set_Telefono(resultSet.getLong("Telefono"));
		         usuarioEncontrado.set_Admin(resultSet.getInt("Admin") == 1); // 1 indica administrador, cualquier otro valor indica cliente

		         return usuarioEncontrado;
		     }
		 } catch (SQLException e) {
		     e.printStackTrace();
		 }

		 return null;
		 
	 }
	 public int modificarUsuario(Cliente cliente) throws SQLException {
		 String query = "UPDATE Usuario SET Username = ?,Pass = ?,Nombre = ?,Apellido = ?,DNI = ?,CUIL = ?,Sexo = ?,Nacionalidad = ?,FechaNacimiento = ?,Direccion = ?,Localidad = ?,Provincia = ?,Mail = ?,Telefono = ?, Admin = ? WHERE DNI = ?;";
		 int filas = 0;

	        try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
	             PreparedStatement preparedStatement = cn.prepareStatement(query)) {
	            preparedStatement.setString(1, cliente.get_Usuario());
	            preparedStatement.setString(2, cliente.get_Contrasena());
	            preparedStatement.setString(3, cliente.get_Nombre());
	            preparedStatement.setString(4, cliente.get_Apellido());
	            preparedStatement.setLong(5, cliente.get_DNI());
	            preparedStatement.setLong(6, cliente.get_CUIL());
	            preparedStatement.setInt(7, cliente.is_Sexo() ? 1 : 0);
	            preparedStatement.setString(8, cliente.get_Nacionalidad());

	            java.sql.Date sqlFechaNacimiento = java.sql.Date.valueOf(cliente.get_FechaNacimiento());
	            preparedStatement.setDate(9, sqlFechaNacimiento);
	            
	            preparedStatement.setString(10, cliente.get_Direccion().toString());
	            preparedStatement.setString(11, cliente.get_Localidad());
	            preparedStatement.setString(12, cliente.get_Provincia());
	            preparedStatement.setString(13, cliente.get_Email());
	            preparedStatement.setLong(14, cliente.get_Telefono());
	            preparedStatement.setInt(15, cliente.is_Admin() ? 1 : 0);
	            preparedStatement.setLong(16, cliente.get_DNI());
	            
	            filas = preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return filas;
	 
	 }
	 
}