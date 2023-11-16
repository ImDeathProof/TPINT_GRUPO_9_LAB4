package daoImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.MovimientoDaoInterface;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.Movimiento;
import entidad.Prestamo;
import entidad.TipoMovimiento;
import negocio.ClienteNeg;
import negocio.CuentaNeg;
import negocioImpl.ClienteNegImpl;
import negocioImpl.CuentaNegImpl;

public class MovimientoDAO implements MovimientoDaoInterface {
	
	 private String host = "jdbc:mysql://127.0.0.1:3306/";
	 private String user = "root";
	 private String pass = "tobias01032004";
	 private String dbName = "bancodb";

	 public MovimientoDAO() {
	     try {
	         Class.forName("com.mysql.cj.jdbc.Driver");
	     } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      }
	 }
	
	 public int insertMovimiento(int idTipo, int idCuenta)
	    {
	    	String query = "INSERT INTO Movimiento (Monto, Fecha, Detalles, IDTipo, IDUsuario, IDCuenta) VALUES (10000, NOW(), 'Alta de cuenta', 1, ?, ?)";
	  		 int filas = 0;

	  	        try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
	  	             PreparedStatement preparedStatement = cn.prepareStatement(query)) {
	  	            preparedStatement.setInt(1, getUserFromCuenta(idCuenta));
	  	            preparedStatement.setInt(2, idCuenta);
	  	            
	  	            filas = preparedStatement.executeUpdate();
	  	        } catch (SQLException e) {
	  	            e.printStackTrace();
	  	        }
	  	        
	  	        return filas;
	    }
	    
	    public int insertMovimiento(int idCuenta,BigDecimal monto, TipoMovimiento tp)
	    {
	    	String query = "INSERT INTO Movimiento (Monto, Fecha, Detalles, IDTipo, IDUsuario, IDCuenta) VALUES (?, NOW(), ?, ?, ?, ?)";
	  		 int filas = 0;

	  	        try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
	  	             PreparedStatement preparedStatement = cn.prepareStatement(query)) {
	  	        	 preparedStatement.setBigDecimal(1, monto);
	  	        	 preparedStatement.setString(2, tp.getTipoMovimiento());
	  	        	 preparedStatement.setInt(3, tp.getId_TipoMovimiento());
	  	             preparedStatement.setInt(4, getUserFromCuenta(idCuenta));
	  	             preparedStatement.setInt(5, idCuenta);
	  	            
	  	            filas = preparedStatement.executeUpdate();
	  	        } catch (SQLException e) {
	  	            e.printStackTrace();
	  	        }
	  	        
	  	        return filas;
	    }
	    
	    public int getUserFromCuenta(int idCuenta) {
	        String query = "SELECT IDUsuario AS user FROM Cuenta WHERE IDCuenta = ?;";

	        try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
	             PreparedStatement preparedStatement = cn.prepareStatement(query)) {
	            preparedStatement.setInt(1, idCuenta);
	            ResultSet resultSet = preparedStatement.executeQuery();

	            if (resultSet.next()) {
	                return resultSet.getInt("user");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return -1;
	    }
	    
	    public ArrayList<Movimiento> generarInformeIngresosEgresos(LocalDate fechaInicio, LocalDate fechaFin) {
	        String query = "SELECT m.*, tm.Descripcion AS TipoMovimientoDescripcion " +
	                       "FROM Movimiento m " +
	                       "JOIN TiposMovimientos tm ON m.IDTipo = tm.IDTipo " +
	                       "WHERE m.Fecha BETWEEN ? AND ?";

	        ArrayList<Movimiento> movimientos = new ArrayList<>();

	        try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
	             PreparedStatement preparedStatement = cn.prepareStatement(query)) {

	        	java.sql.Date sqlFechaInicio = java.sql.Date.valueOf(fechaInicio);
	        	java.sql.Date sqlFechaFin = java.sql.Date.valueOf(fechaFin);

	            preparedStatement.setDate(1, sqlFechaInicio);
	            preparedStatement.setDate(2, sqlFechaFin);

	            ResultSet resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	                Movimiento movimiento = new Movimiento();
	                movimiento.setId_Movimiento(resultSet.getInt("IDMoviento"));
	                movimiento.setMonto(resultSet.getBigDecimal("Monto"));
	                movimiento.setFecha(resultSet.getDate("Fecha").toLocalDate());
	                movimiento.setDetalles(resultSet.getString("Detalles"));
	                movimiento.setIdCliente(resultSet.getInt("IDUsuario"));
	                movimiento.setIdCuenta(resultSet.getInt("IDCuenta"));

	                TipoMovimiento tipoMovimiento = new TipoMovimiento();
	                tipoMovimiento.setId_TipoMovimiento(resultSet.getInt("IDTipo"));
	                tipoMovimiento.setTipoMovimiento(resultSet.getString("TipoMovimientoDescripcion"));

	                movimiento.setTipoMov(tipoMovimiento);

	                movimientos.add(movimiento);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return movimientos;
	    }
	    
	    @Override
	    public ArrayList<Movimiento> obtenerInformePaginado(int pageNumber, int pageSize, LocalDate fechaInicio, LocalDate fechaFin, String orderBy) {
	        ArrayList<Movimiento> movimientos = new ArrayList<>();
	        Connection cn = null;

	        try {
	            cn = DriverManager.getConnection(host + dbName, user, pass);
	            Statement st = cn.createStatement();
	            int offset = (pageNumber - 1) * pageSize;

	            String query = "SELECT m.*, tm.Descripcion AS TipoMovimientoDescripcion " +
	                           "FROM Movimiento m " +
	                           "JOIN TiposMovimientos tm ON m.IDTipo = tm.IDTipo " +
	                           "WHERE m.Fecha BETWEEN ? AND ? " +
	                           "ORDER BY " + orderBy + " DESC " +
	                           "LIMIT " + pageSize +
	                           " OFFSET " + offset;

	            try (PreparedStatement preparedStatement = cn.prepareStatement(query)) {
	                java.sql.Date sqlFechaInicio = java.sql.Date.valueOf(fechaInicio);
	                java.sql.Date sqlFechaFin = java.sql.Date.valueOf(fechaFin);

	                preparedStatement.setDate(1, sqlFechaInicio);
	                preparedStatement.setDate(2, sqlFechaFin);

	                ResultSet resultSet = preparedStatement.executeQuery();

	                while (resultSet.next()) {
	                    Movimiento movimiento = new Movimiento();
	                    movimiento.setId_Movimiento(resultSet.getInt("IDMoviento"));
	                    movimiento.setMonto(resultSet.getBigDecimal("Monto"));
	                    movimiento.setFecha(resultSet.getDate("Fecha").toLocalDate());
	                    movimiento.setDetalles(resultSet.getString("Detalles"));
	                    movimiento.setIdCliente(resultSet.getInt("IDUsuario"));
	                    movimiento.setIdCuenta(resultSet.getInt("IDCuenta"));

	                    TipoMovimiento tipoMovimiento = new TipoMovimiento();
	                    tipoMovimiento.setId_TipoMovimiento(resultSet.getInt("IDTipo"));
	                    tipoMovimiento.setTipoMovimiento(resultSet.getString("TipoMovimientoDescripcion"));

	                    movimiento.setTipoMov(tipoMovimiento);

	                    movimientos.add(movimiento);
	                }
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (cn != null) {
	                    cn.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }

	        return movimientos;
	    }
	    
	    public ArrayList<Movimiento> obtenerInformePaginado(int pageNumber, int pageSize, int idCuenta) {
	        ArrayList<Movimiento> movimientos = new ArrayList<>();
	        Connection cn = null;

	        try {
	            cn = DriverManager.getConnection(host + dbName, user, pass);
	            Statement st = cn.createStatement();
	            int offset = (pageNumber - 1) * pageSize;

	            String query = "SELECT m.*, tm.Descripcion AS TipoMovimientoDescripcion " +
	                           "FROM Movimiento m " +
	                           "JOIN TiposMovimientos tm ON m.IDTipo = tm.IDTipo " +
	                           "WHERE IdCuenta = ? " +
	                           "LIMIT " + pageSize +
	                           " OFFSET " + offset;

	            try (PreparedStatement preparedStatement = cn.prepareStatement(query)) {

	                preparedStatement.setInt(1, idCuenta);

	                ResultSet resultSet = preparedStatement.executeQuery();

	                while (resultSet.next()) {
	                    Movimiento movimiento = new Movimiento();
	                    movimiento.setId_Movimiento(resultSet.getInt("IDMoviento"));
	                    movimiento.setMonto(resultSet.getBigDecimal("Monto"));
	                    movimiento.setFecha(resultSet.getDate("Fecha").toLocalDate());
	                    movimiento.setDetalles(resultSet.getString("Detalles"));
	                    movimiento.setIdCliente(resultSet.getInt("IDUsuario"));
	                    movimiento.setIdCuenta(resultSet.getInt("IDCuenta"));

	                    TipoMovimiento tipoMovimiento = new TipoMovimiento();
	                    tipoMovimiento.setId_TipoMovimiento(resultSet.getInt("IDTipo"));
	                    tipoMovimiento.setTipoMovimiento(resultSet.getString("TipoMovimientoDescripcion"));

	                    movimiento.setTipoMov(tipoMovimiento);

	                    movimientos.add(movimiento);
	                }
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (cn != null) {
	                    cn.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }

	        return movimientos;
	    }


	    public int getCantMovimientos(LocalDate fechaInicio, LocalDate fechaFin) {
	        String query = "SELECT COUNT(*) AS cantidadMovimientos " +
	                       "FROM Movimiento m " +
	                       "WHERE m.Fecha BETWEEN ? AND ?";

	        int cantidadMovimientos = 0;

	        try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
	             PreparedStatement preparedStatement = cn.prepareStatement(query)) {

	            java.sql.Date sqlFechaInicio = java.sql.Date.valueOf(fechaInicio);
	            java.sql.Date sqlFechaFin = java.sql.Date.valueOf(fechaFin);

	            preparedStatement.setDate(1, sqlFechaInicio);
	            preparedStatement.setDate(2, sqlFechaFin);

	            ResultSet resultSet = preparedStatement.executeQuery();

	            if (resultSet.next()) {
	                cantidadMovimientos = resultSet.getInt("cantidadMovimientos");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return cantidadMovimientos;
	    }
	    
	    public BigDecimal getPromedioMonto(LocalDate fechaInicio, LocalDate fechaFin) {
	        String query = "SELECT AVG(Monto) AS promedio " +
	                       "FROM Movimiento m " +
	                       "WHERE m.Fecha BETWEEN ? AND ?";

	        BigDecimal promedioMonto = BigDecimal.ZERO;

	        try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
	             PreparedStatement preparedStatement = cn.prepareStatement(query)) {

	            java.sql.Date sqlFechaInicio = java.sql.Date.valueOf(fechaInicio);
	            java.sql.Date sqlFechaFin = java.sql.Date.valueOf(fechaFin);

	            preparedStatement.setDate(1, sqlFechaInicio);
	            preparedStatement.setDate(2, sqlFechaFin);

	            ResultSet resultSet = preparedStatement.executeQuery();

	            if (resultSet.next()) {
	                promedioMonto = resultSet.getBigDecimal("promedio");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return promedioMonto;
	    }
	    
		

		@Override
		public int getCantPaginas() {
			// TODO Auto-generated method stub
			int cant = 0;

		    String query = "SELECT CEIL(COUNT(*) / 5) AS paginas FROM Movimiento;";

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
		
		public int getCantPaginas(int idCliente) {
		    int cant = 0;

		    String query = "SELECT CEIL(COUNT(*) / 5) AS paginas FROM Movimiento where IdCliente = ?;";

		    try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
		         PreparedStatement preparedStatement = cn.prepareStatement(query)) {
		        
		        preparedStatement.setInt(1, idCliente);

		        try (ResultSet rs = preparedStatement.executeQuery()) {
		            if (rs.next()) {
		                cant = rs.getInt("paginas");
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return cant;
		}

}
