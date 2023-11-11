package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.ClienteDaoInterface;
import entidad.Cliente;

public class ClienteDAO implements ClienteDaoInterface {
	
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
	
	 public Cliente agregarUsuario(Cliente cliente) throws SQLException {
	        String query = "INSERT INTO Usuario (Username, Pass, Nombre, Apellido, DNI, CUIL, Sexo, Nacionalidad, FechaNacimiento, Direccion, Localidad, Provincia, Mail, Telefono, Admin,Bloqueado) " +
	                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,0)";

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

	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	        return BuscarUsuario(cliente);
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
		         usuarioEncontrado.set_IDCliente(resultSet.getInt("IDUsuario"));
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
		         usuarioEncontrado.set_Admin(resultSet.getBoolean("Admin")); // 1 indica administrador, cualquier otro valor indica cliente
		         usuarioEncontrado.setBloqueado(resultSet.getBoolean("Bloqueado"));
		         
		         return usuarioEncontrado;
		     }
		 } catch (SQLException e) {
		     e.printStackTrace();
		 }

		 return null;
		 
	 }
	 
	 public int modificarUsuario(Cliente cliente) throws SQLException {
		 String query = "UPDATE Usuario SET Username = ?,Pass = ?,Nombre = ?,Apellido = ?,DNI = ?,CUIL = ?,Sexo = ?,Nacionalidad = ?,FechaNacimiento = ?,Direccion = ?,Localidad = ?,Provincia = ?,Mail = ?,Telefono = ?, Admin = ?, Bloqueado = ? WHERE IDUsuario = ?;";
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
	            preparedStatement.setInt(16, cliente.get_IDCliente());
	            preparedStatement.setInt(17, cliente.isBloqueado() ? 1 : 0);
	            
	            filas = preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return filas;
	 
	 }
	 public ArrayList<Cliente> obtenerUsuarios(){
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
		 }catch(ClassNotFoundException e) {
			 e.printStackTrace();
		 }
		 
		 ArrayList<Cliente> lista = new ArrayList<Cliente>();
		 Connection cn = null;
		 try {
			 cn = DriverManager.getConnection(host + dbName, user, pass);
			 Statement st = cn.createStatement();
			 
			 ResultSet rs = st.executeQuery("Select IDUsuario, Username, Pass, Nombre, Apellido, DNI, CUIL, Sexo, Nacionalidad, FechaNacimiento, Direccion, Localidad, Provincia, Mail, Telefono, Admin, Bloqueado from Usuario");
			 while(rs.next()) {
				 Cliente clienteRs = new Cliente();
				 clienteRs.set_IDCliente(rs.getInt("IDUsuario"));
				 clienteRs.set_Usuario(rs.getString("Username"));
				 clienteRs.set_Contrasena(rs.getString("Pass"));
				 clienteRs.set_Nombre(rs.getString("Nombre"));
				 clienteRs.set_Apellido(rs.getString("Apellido"));
				 clienteRs.set_DNI(rs.getLong("DNI"));
				 clienteRs.set_CUIL(rs.getLong("CUIL"));
				 clienteRs.set_Sexo(rs.getInt("Sexo") == 1); // 1 indica femenino, cualquier otro valor indica masculino
				 clienteRs.set_Nacionalidad(rs.getString("Nacionalidad"));
				 clienteRs.set_FechaNacimiento(rs.getDate("FechaNacimiento").toLocalDate());
				 clienteRs.set_Direccion(rs.getString("Direccion"));
				 clienteRs.set_Localidad(rs.getString("Localidad"));
				 clienteRs.set_Provincia(rs.getString("Provincia"));
				 clienteRs.set_Email(rs.getString("Mail"));
				 clienteRs.set_Telefono(rs.getLong("Telefono"));
				 clienteRs.set_Admin(rs.getInt("Admin") == 1); // 1 indica administrador, cualquier otro valor indica cliente
				 clienteRs.setBloqueado(rs.getInt("Bloqueado") == 1);
				 
				 lista.add(clienteRs);
			 }
			 cn.close();
		 } catch (SQLException e) {
		     e.printStackTrace();
		 }
		 return lista;
	 }
	 
	 public int BloquearCliente(int id)
	 {
		 String query = "UPDATE Usuario SET Bloqueado = 1 WHERE IDUsuario = ? AND ADMIN = 0;";
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
	 
	 public int DesbloquearCliente(int id)
	 {
		 String query = "UPDATE Usuario SET Bloqueado = 0 WHERE IDUsuario = ? AND ADMIN = 0;";
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
	 
	 public int CambiarPass(String password, int id )
	 {
		 String query = "UPDATE Usuario SET Pass = ? WHERE IDUsuario = ? AND ADMIN = 0;";
		 int filas = 0;

	        try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
	             PreparedStatement preparedStatement = cn.prepareStatement(query)) {
	        	 preparedStatement.setString(1, password);
	             preparedStatement.setInt(2, id);
	            
	            filas = preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return filas;
	 }
	 
	 public ArrayList<Cliente> obtenerUsuariosPaginados(int pageNumber, int pageSize) {
		    try {
		        Class.forName("com.mysql.cj.jdbc.Driver");
		    } catch (ClassNotFoundException e) {
		        e.printStackTrace();
		    }

		    ArrayList<Cliente> lista = new ArrayList<Cliente>();
		    Connection cn = null;
		    try {
		        cn = DriverManager.getConnection(host + dbName, user, pass);
		        Statement st = cn.createStatement();
		        int offset = (pageNumber - 1) * pageSize; 

		        String query = "SELECT IDUsuario, Username, Pass, Nombre, Apellido, DNI, CUIL, Sexo, Nacionalidad, FechaNacimiento, Direccion, Localidad, Provincia, Mail, Telefono, Admin, Bloqueado FROM Usuario" +
		                       " ORDER BY IDUsuario" +
		                       " LIMIT " + pageSize +
		                       " OFFSET " + offset;
		        ResultSet rs = st.executeQuery(query);

		        while (rs.next()) {
		            Cliente clienteRs = new Cliente();
					 clienteRs.set_IDCliente(rs.getInt("IDUsuario"));
					 clienteRs.set_Usuario(rs.getString("Username"));
					 clienteRs.set_Contrasena(rs.getString("Pass"));
					 clienteRs.set_Nombre(rs.getString("Nombre"));
					 clienteRs.set_Apellido(rs.getString("Apellido"));
					 clienteRs.set_DNI(rs.getLong("DNI"));
					 clienteRs.set_CUIL(rs.getLong("CUIL"));
					 clienteRs.set_Sexo(rs.getInt("Sexo") == 1); // 1 indica femenino, cualquier otro valor indica masculino
					 clienteRs.set_Nacionalidad(rs.getString("Nacionalidad"));
					 clienteRs.set_FechaNacimiento(rs.getDate("FechaNacimiento").toLocalDate());
					 clienteRs.set_Direccion(rs.getString("Direccion"));
					 clienteRs.set_Localidad(rs.getString("Localidad"));
					 clienteRs.set_Provincia(rs.getString("Provincia"));
					 clienteRs.set_Email(rs.getString("Mail"));
					 clienteRs.set_Telefono(rs.getLong("Telefono"));
					 clienteRs.set_Admin(rs.getInt("Admin") == 1); // 1 indica administrador, cualquier otro valor indica cliente
					 clienteRs.setBloqueado(rs.getInt("Bloqueado") == 1);
					 
					 lista.add(clienteRs);
		        }

		        cn.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return lista;
		}

	 	public int getCantPaginas() {
		 
		    int cant = 0;

		    String query = "SELECT CEIL(COUNT(*) / 5) AS paginas FROM Usuario;";

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
	 
}