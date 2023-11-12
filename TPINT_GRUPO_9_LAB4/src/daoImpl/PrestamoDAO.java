package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.PrestamoDaoInterface;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.Prestamo;
import negocio.ClienteNeg;
import negocio.CuentaNeg;
import negocioImpl.ClienteNegImpl;
import negocioImpl.CuentaNegImpl;

public class PrestamoDAO implements PrestamoDaoInterface{
	
	private String host = "jdbc:mysql://127.0.0.1:3306/";
    private String user = "root";
    private String pass = "root";
    private String dbName = "bancodb";
	
	public PrestamoDAO() {
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
	}
	
	@Override
	public List<Prestamo> ObtenerTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Prestamo ObtenerUno(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int Insertar(Prestamo prestamo) {
		int filas = 0;
		String query = "INSERT INTO prestamos (MontoTotal, Importe_x_Cuota, Plazo_Pago, MontoAprobado, TasaInteres, Fecha_Pedido, EstadoPrestamo, IDCuenta, IDUsuario) VALUES (?, ?, ?, 0, ?, ?, ?, ?, ?)";
		try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
	            PreparedStatement preparedStatement = cn.prepareStatement(query)) {
	            
				preparedStatement.setBigDecimal(1, prestamo.getMonto());
				preparedStatement.setBigDecimal(2, prestamo.getImporteCuota());
				preparedStatement.setInt(3, prestamo.getPlazoPago());
				preparedStatement.setFloat(4, prestamo.getTasaInteres());				
				LocalDate fechaActual = LocalDate.now();
				java.sql.Date sqlFechaActual = java.sql.Date.valueOf(fechaActual);
				preparedStatement.setDate(5, sqlFechaActual);								
				preparedStatement.setString(6, prestamo.getEstado());
				preparedStatement.setInt(7, prestamo.getCuenta().getIdCuenta());
				preparedStatement.setInt(8, prestamo.getCliente().get_IDCliente());
			

	            filas = preparedStatement.executeUpdate();
	       } catch (SQLException e) {
	            e.printStackTrace();
	       }

	        return filas;
	}

	@Override
	public int Editar(Prestamo prestamo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int Borrar(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int Aprobar(int id) {
		String query = "UPDATE Prestamos SET EstadoPrestamo = 'Aprobado', MontoAprobado = MontoTotal WHERE IDPrestamo = ?;";
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

	@Override
	public int Rechazar(int id) {
		String query = "UPDATE Prestamos SET EstadoPrestamo = 'Rechazado' WHERE IDPrestamo = ?;";
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

	@Override
	public ArrayList<Prestamo> obtenerPrestamosPaginados(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		ArrayList<Prestamo> lista = new ArrayList<Prestamo>();
 	    Connection cn = null;
 	    try {
 	        cn = DriverManager.getConnection(host + dbName, user, pass);
 	        Statement st = cn.createStatement();
 	        int offset = (pageNumber - 1) * pageSize; 

 	        String query = "SELECT * FROM Prestamos" +
 	                       " ORDER BY IDCuenta" +
 	                       " LIMIT " + pageSize +
 	                       " OFFSET " + offset;
 	        ResultSet rs = st.executeQuery(query);

 	        while (rs.next()) {
 	        	Prestamo prestamo = new Prestamo();
 	        	
 	        	CuentaNeg ctNeg = new CuentaNegImpl();
 	        	int idCuenta = rs.getInt("IDCuenta");
 	        	Cuenta ct = ctNeg.obtenerCuentaPorID(idCuenta);
 	        	prestamo.setCuenta(ct);
 	        	
 	        	ClienteNeg clNeg = new ClienteNegImpl();
 	        	Cliente cl = clNeg.BuscarClientePorID(ct.getIdUsuario());
 	        	prestamo.setCliente(cl);
 	        	prestamo.setFechaPedido(rs.getTimestamp("Fecha_Pedido").toLocalDateTime().toLocalDate());
 	        	
 	        	prestamo.setImporteCuota(rs.getBigDecimal("Importe_x_Cuota"));
 	        	prestamo.setPlazoPago(rs.getInt("Plazo_Pago"));
 	        	prestamo.setMontoAprobado(rs.getBigDecimal("MontoAprobado"));
 	        	prestamo.setMonto(rs.getBigDecimal("MontoTotal"));
 	        	prestamo.setTasaInteres(rs.getFloat("TasaInteres"));
 	        	prestamo.setEstado(rs.getString("EstadoPrestamo"));
 	        	prestamo.setId_Prestamo(rs.getInt("IDPrestamo"));
 	        	
 	        	
 				 lista.add(prestamo);
 	        }

 	        cn.close();
 	    } catch (SQLException e) {
 	        e.printStackTrace();
 	    }
 	    return lista;
 	}
	

	@Override
	public int getCantPaginas() {
		// TODO Auto-generated method stub
		int cant = 0;

	    String query = "SELECT CEIL(COUNT(*) / 5) AS paginas FROM Prestamos;";

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