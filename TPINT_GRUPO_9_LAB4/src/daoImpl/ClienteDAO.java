package daoImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.ClienteDaoInterface;
import entidad.Cliente;
import entidad.Direccion;
import entidad.GenericException;
import entidad.Localidad;
import entidad.Provincia;
import entidad.TipoMovimiento;
import entidad.DBException;
import entidad.ValidateException;
import negocio.DireccionNeg;
import negocioImpl.DireccionNegImpl;
import entidad.GenericException;


public class ClienteDAO implements ClienteDaoInterface {
	
	private String host = "jdbc:mysql://127.0.0.1:3306/";
	 private String user = "root";
	 private String pass = "root";
	 private String dbName = "bancodb";
	 
	 DireccionNeg dNeg = new DireccionNegImpl();

	 public ClienteDAO() {
	     try {
	         Class.forName("com.mysql.cj.jdbc.Driver");
	     } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      }
 }
	
	 public Cliente agregarUsuario(Cliente cliente) throws DBException, GenericException {
	        String query = "INSERT INTO Usuario (Username, Pass, Nombre, Apellido, DNI, CUIL, Sexo, Nacionalidad, FechaNacimiento, IDDireccion, Mail, Telefono, Admin,Bloqueado) " +
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
	            
	            preparedStatement.setInt(10, cliente.get_Direccion().getId());
	            preparedStatement.setString(11, cliente.get_Email());
	            preparedStatement.setLong(12, cliente.get_Telefono());
	            preparedStatement.setInt(13, cliente.is_Admin() ? 1 : 0);

	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
		        e.printStackTrace();
		        throw new DBException("Hubo un problema de conexión con la DB de Clientes");
		    }catch (Exception e){
		    	 e.printStackTrace();
		    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
		    }	 
	        return BuscarUsuario(cliente);
	    }
	 
	 public Cliente BuscarUsuario(Cliente cliente) throws DBException, GenericException {

		    String username = cliente.get_Usuario().trim();
		    String password = cliente.get_Contrasena();

		    String checkQuery = "SELECT * FROM Usuario WHERE BINARY Username = ? AND BINARY Pass = ?";

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

		            int idDireccion = resultSet.getInt("IDDireccion");
		            Direccion direc = dNeg.obtenerDireccionPorID(idDireccion); 
		            usuarioEncontrado.set_Direccion(direc);

		            usuarioEncontrado.set_Email(resultSet.getString("Mail"));
		            usuarioEncontrado.set_Telefono(resultSet.getLong("Telefono"));
		            usuarioEncontrado.set_Admin(resultSet.getBoolean("Admin")); // 1 indica administrador, cualquier otro valor indica cliente
		            usuarioEncontrado.setBloqueado(resultSet.getBoolean("Bloqueado"));

		            return usuarioEncontrado;
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
	 
		public int modificarUsuario(Cliente cliente) throws DBException, GenericException {
			
			
		    String query = "UPDATE Usuario SET Username = ?, Pass = ?, Nombre = ?, Apellido = ?, DNI = ?, CUIL = ?, Sexo = ?, Nacionalidad = ?, FechaNacimiento = ?, IDDireccion = ?, Mail = ?, Telefono = ?, Admin = ?, Bloqueado = ? WHERE IDUsuario = ?";
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

		        preparedStatement.setInt(10, cliente.get_Direccion().getId());
		        
		        preparedStatement.setString(11, cliente.get_Email());
		        preparedStatement.setLong(12, cliente.get_Telefono());
		        preparedStatement.setInt(13, cliente.is_Admin() ? 1 : 0);
		        preparedStatement.setInt(14, cliente.isBloqueado() ? 1 : 0);
		        preparedStatement.setInt(15, cliente.get_IDCliente());

		        filas = preparedStatement.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		        throw new DBException("Hubo un problema de conexión con la DB de Clientes");
		    }catch (Exception e){
		    	 e.printStackTrace();
		    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
		    }

		    return filas;
		}

	 
		public boolean usuarioExistente(String username, int idUsuario) throws ValidateException, GenericException {
		    String query = "SELECT COUNT(*) FROM Usuario WHERE Username = ? AND IDUsuario <> ?";
		    int count = 0;

		    try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
		         PreparedStatement preparedStatement = cn.prepareStatement(query)) {
		        preparedStatement.setString(1, username);
		        preparedStatement.setInt(2, idUsuario);
		        ResultSet resultSet = preparedStatement.executeQuery();
		        if (resultSet.next()) {
		            count = resultSet.getInt(1);
		        }
		    }catch (SQLException e) {
		        e.printStackTrace();
		        throw new ValidateException("Hubo un problema al validar datos de la DB");
		    } catch (Exception e) {
		        e.printStackTrace();
		        throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
		    }

		    return count > 0;
		}

	 
	 public ArrayList<Cliente> obtenerUsuarios() throws DBException, GenericException{
		    ArrayList<Cliente> lista = new ArrayList<>();
		    
		    try {
		        Class.forName("com.mysql.cj.jdbc.Driver");
		    } catch (ClassNotFoundException e) {
		        e.printStackTrace();
		    }

		    try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
		         PreparedStatement preparedStatement = cn.prepareStatement(
		                 "SELECT IDUsuario, Username, Pass, Nombre, Apellido, DNI, CUIL, Sexo, Nacionalidad, FechaNacimiento, IDDireccion, Mail, Telefono, Admin, Bloqueado FROM Usuario")) {

		        try (ResultSet rs = preparedStatement.executeQuery()) {
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
	              
		                int idDireccion = rs.getInt("IDDireccion");
		                Direccion direccion = dNeg.obtenerDireccionPorID(idDireccion);
		                clienteRs.set_Direccion(direccion);

		                clienteRs.set_Email(rs.getString("Mail"));
		                clienteRs.set_Telefono(rs.getLong("Telefono"));
		                clienteRs.set_Admin(rs.getInt("Admin") == 1); // 1 indica administrador, cualquier otro valor indica cliente
		                clienteRs.setBloqueado(rs.getInt("Bloqueado") == 1);

		                lista.add(clienteRs);
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

	 
	 public int BloquearCliente(int id) throws DBException, GenericException
	 {
		 String query = "UPDATE Usuario SET Bloqueado = 1 WHERE IDUsuario = ? AND ADMIN = 0;";
		 int filas = 0;

	        try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
	             PreparedStatement preparedStatement = cn.prepareStatement(query)) {
	            preparedStatement.setInt(1, id);
	            
	            filas = preparedStatement.executeUpdate();
	        } catch (SQLException e) {
		        e.printStackTrace();
		        throw new DBException("Hubo un problema de conexión con la DB de Clientes");
		    }catch (Exception e){
		    	 e.printStackTrace();
		    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
		    }


	        return filas;
	 }
	 
	 public int DesbloquearCliente(int id) throws DBException, GenericException
	 {
		 String query = "UPDATE Usuario SET Bloqueado = 0 WHERE IDUsuario = ? AND ADMIN = 0;";
		 int filas = 0;

	        try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
	             PreparedStatement preparedStatement = cn.prepareStatement(query)) {
	            preparedStatement.setInt(1, id);
	            
	            filas = preparedStatement.executeUpdate();
	        } catch (SQLException e) {
		        e.printStackTrace();
		        throw new DBException("Hubo un problema de conexión con la DB de Clientes");
		    }catch (Exception e){
		    	 e.printStackTrace();
		    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
		    }

	        return filas;
	 }
	 
	 public int CambiarPass(String password, int id ) throws DBException, GenericException
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
		        throw new DBException("Hubo un problema de conexión con la DB de Clientes");
		    }catch (Exception e){
		    	 e.printStackTrace();
		    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
		    }

	        return filas;
	 }
	 
	 public ArrayList<Cliente> obtenerUsuariosPaginados(int pageNumber, int pageSize) throws DBException, GenericException{
		    try {
		        Class.forName("com.mysql.cj.jdbc.Driver");
		    } catch (ClassNotFoundException e) {
		        e.printStackTrace();
		    }

		    ArrayList<Cliente> lista = new ArrayList<>();
		    try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
		         PreparedStatement preparedStatement = cn.prepareStatement(
		                 "SELECT IDUsuario, Username, Pass, Nombre, Apellido, DNI, CUIL, Sexo, Nacionalidad, FechaNacimiento, IDDireccion, Mail, Telefono, Admin, Bloqueado FROM Usuario" +
		                         " ORDER BY IDUsuario" +
		                         " LIMIT ?" +
		                         " OFFSET ?")) {

		        int offset = (pageNumber - 1) * pageSize;
		        preparedStatement.setInt(1, pageSize);
		        preparedStatement.setInt(2, offset);

		        try (ResultSet rs = preparedStatement.executeQuery()) {
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

		                int idDireccion = rs.getInt("IDDireccion");
		                Direccion direccion = dNeg.obtenerDireccionPorID(idDireccion);
		                clienteRs.set_Direccion(direccion);

		                clienteRs.set_Email(rs.getString("Mail"));
		                clienteRs.set_Telefono(rs.getLong("Telefono"));
		                clienteRs.set_Admin(rs.getInt("Admin") == 1); // 1 indica administrador, cualquier otro valor indica cliente
		                clienteRs.setBloqueado(rs.getInt("Bloqueado") == 1);

		                lista.add(clienteRs);
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


	 	public int getCantPaginas() throws DBException, GenericException{
		 
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
		        throw new DBException("Hubo un problema de conexión con la DB de Clientes");
		    }catch (Exception e){
		    	 e.printStackTrace();
		    	 throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
		    }

		    return cant;
		}

		@Override
		public Cliente BuscarClientePorID(int idCliente) throws DBException, GenericException{
		    String checkQuery = "SELECT IDUsuario, Username, Pass, Nombre, Apellido, DNI, CUIL, Sexo, Nacionalidad, FechaNacimiento, IDDireccion, Mail, Telefono, Admin, Bloqueado FROM Usuario WHERE IDUsuario = ?";

		    try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
		         PreparedStatement checkStatement = cn.prepareStatement(checkQuery)) {
		        checkStatement.setInt(1, idCliente);

		        try (ResultSet resultSet = checkStatement.executeQuery()) {
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

		                int idDireccion = resultSet.getInt("IDDireccion");
		                Direccion direccion = dNeg.obtenerDireccionPorID(idDireccion);
		                usuarioEncontrado.set_Direccion(direccion);

		                usuarioEncontrado.set_Email(resultSet.getString("Mail"));
		                usuarioEncontrado.set_Telefono(resultSet.getLong("Telefono"));
		                usuarioEncontrado.set_Admin(resultSet.getBoolean("Admin")); // 1 indica administrador, cualquier otro valor indica cliente
		                usuarioEncontrado.setBloqueado(resultSet.getBoolean("Bloqueado"));

		                return usuarioEncontrado;
		            }
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
		

		public ArrayList<Provincia> obtenerProvincias() throws DBException, GenericException {
		    ArrayList<Provincia> listaProvincias = new ArrayList<>();

		    String query = "SELECT * FROM Provincia";

		    try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
		         PreparedStatement preparedStatement = cn.prepareStatement(query);
		         ResultSet rs = preparedStatement.executeQuery()) {

		        while (rs.next()) {
		            Provincia provincia = new Provincia();
		            provincia.setIdProvincia(rs.getInt("IDProvincia"));
		            provincia.setDescripcion(rs.getString("Descripcion"));
		            listaProvincias.add(provincia);
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		        throw new DBException("Hubo un problema de conexión con la DB de Provincias");
		    } catch (Exception e) {
		        e.printStackTrace();
		        throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
		    }

		    return listaProvincias;
		}

		public ArrayList<Localidad> obtenerLocalidades() throws DBException, GenericException {
		    ArrayList<Localidad> listaLocalidades = new ArrayList<>();

		    String query = "SELECT * FROM Localidad";

		    try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
		         PreparedStatement preparedStatement = cn.prepareStatement(query);
		         ResultSet rs = preparedStatement.executeQuery()) {

		        while (rs.next()) {
		            Localidad localidad = new Localidad();
		            localidad.setIdLocalidad(rs.getInt("IDLocalidad"));
		            localidad.setDescripcion(rs.getString("Descripcion"));
		            listaLocalidades.add(localidad);
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		        throw new DBException("Hubo un problema de conexión con la DB de Localidades");
		    } catch (Exception e) {
		        e.printStackTrace();
		        throw new GenericException("Hubo un error inesperado. Intente nuevamente más tarde");
		    }

		    return listaLocalidades;
		}


	 
}