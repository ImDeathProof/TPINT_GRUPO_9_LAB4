package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.CuotaDaoInterface;
import entidad.Cuota;
import entidad.DBException;
import entidad.Prestamo;

public class CuotaDAO implements CuotaDaoInterface {
	private String host = "jdbc:mysql://127.0.0.1:3306/";
    private String user = "root";
    private String pass = "root";
    private String dbName = "bancodb";
    
    public CuotaDAO() {
    	try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
	@Override
	public int Pagar(int idCuota) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int Agregar(Cuota cuota) throws SQLException {
		int filas = 0;
		String query = "INSERT INTO cuotas_x_clientes (Monto_a_Pagar, Estado, Fecha_Pago, IDPrestamo, IDUsuario, IDCuenta, Nro_Cuota, Cuotas_Totales) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
	            PreparedStatement preparedStatement = cn.prepareStatement(query)) {
	            
				preparedStatement.setBigDecimal(1, cuota.getMontoAPagar());
				preparedStatement.setString(2, cuota.getEstado());
				LocalDate fechaActual = LocalDate.now();
				java.sql.Date sqlFechaActual = java.sql.Date.valueOf(fechaActual);
				preparedStatement.setDate(3, sqlFechaActual);
				preparedStatement.setInt(4, cuota.getIDPrestamo());
				preparedStatement.setInt(5, cuota.getIDUsuario());
				preparedStatement.setInt(6, cuota.getIDCuenta());
				preparedStatement.setInt(7, cuota.getNro_Cuota());
				preparedStatement.setInt(8, cuota.getCuotas_Totales());
				
	            filas = preparedStatement.executeUpdate();
	       } catch (SQLException e) {
	            e.printStackTrace();
	       }

	        return filas;
	}

	@Override
	public ArrayList<Cuota> obtenerCuotasPorPrestamo(int idPrestamo) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Cuota> obtenerCuotasPorCliente(int idCliente) throws DBException {
		ArrayList<Cuota> lista = new ArrayList<Cuota>();
        String cuotaQuery = "SELECT * FROM cuotas_x_clientes WHERE IdUsuario = ?";

        try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
             PreparedStatement cuotaStatement = cn.prepareStatement(cuotaQuery)) {
        	cuotaStatement.setInt(1, idCliente);
            ResultSet cuotaResultSet = cuotaStatement.executeQuery();

            while (cuotaResultSet.next()) {
            	Cuota cuota = new Cuota();
            	cuota.setIDCuota(cuotaResultSet.getInt("IDCuota"));
            	cuota.setCuotas_Totales(cuotaResultSet.getInt("Cuotas_Totales"));
            	cuota.setEstado(cuotaResultSet.getString("Estado"));
                cuota.setFechaDePago(cuotaResultSet.getTimestamp("Fecha_Pago").toLocalDateTime().toLocalDate());
                cuota.setNro_Cuota(cuotaResultSet.getInt("Nro_Cuota"));
                cuota.setIDPrestamo(cuotaResultSet.getInt("IDPrestamo"));
                cuota.setIDUsuario(cuotaResultSet.getInt("IDUsuario"));
                cuota.setIDCuenta(cuotaResultSet.getInt("IDCuenta"));
                cuota.setMontoAPagar(cuotaResultSet.getBigDecimal("Monto_a_Pagar"));
                
                

                lista.add(cuota);
            }
            
        } catch (SQLException e) {
	        e.printStackTrace();
	        throw new DBException("Hubo un problema de conexión con la DB de Clientes");
	    }
        return lista;
	}


}
