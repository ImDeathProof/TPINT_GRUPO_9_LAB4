package daoImpl;

import java.util.List;

import dao.PrestamoDaoInterface;
import entidad.Prestamo;

public class PrestamoDAO implements PrestamoDaoInterface{
private Conexion cn;
	
	public PrestamoDAO() {
		
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
	public boolean Insertar(Prestamo prestamo) {
		boolean estado=true;

		cn = new Conexion();
		cn.Open();	

		String query = "INSERT INTO prestamos (MontoTotal, Importe_x_Cuota, Plazo_Pago, MontoAprobado, TasaInteres, Fecha_Pedido, EstadoPrestamo, IDCuenta, IDUsuario)" +
                "VALUES (?, ?, ?, 0, ?, ?, ?, 0, ?, ?)";
		System.out.println(query);
		try
		 {
			estado=cn.execute(query);
		 }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		return estado;
	}

	@Override
	public boolean Editar(Prestamo prestamo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Borrar(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Aprobar(int id) {
		// TODO Auto-generated method stub
		return false;
	}
}