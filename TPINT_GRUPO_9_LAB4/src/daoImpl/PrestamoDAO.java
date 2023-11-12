package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import dao.PrestamoDaoInterface;
import entidad.Prestamo;

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
		// TODO Auto-generated method stub
		return 0;
	}
}