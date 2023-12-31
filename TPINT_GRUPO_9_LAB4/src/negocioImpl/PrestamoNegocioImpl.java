package negocioImpl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daoImpl.CuotaDAO;
import daoImpl.PrestamoDAO;
import dao.CuotaDaoInterface;
import dao.PrestamoDaoInterface;
import entidad.Prestamo;
import excepciones.DBException;
import excepciones.GenericException;
import negocio.CuentaNeg;
import negocio.PrestamoNeg;

public class PrestamoNegocioImpl implements PrestamoNeg {
	private PrestamoDaoInterface pdao = new PrestamoDAO();
	private CuentaNeg ctNeg = new CuentaNegImpl();
	
	@Override
	public List<Prestamo> ObtenerTodos()  throws DBException, GenericException{
		// TODO Auto-generated method stub
		return pdao.ObtenerTodos();
	}

	@Override
	public Prestamo ObtenerUno(int id) throws DBException, GenericException{
		// TODO Auto-generated method stub
		return pdao.ObtenerUno(id);
	}

	@Override
	public int Insertar(Prestamo prestamo) throws DBException, GenericException{
		// TODO Auto-generated method stub
		return pdao.Insertar(prestamo);
	}

	@Override
	public int Editar(Prestamo prestamo) throws DBException, GenericException{
		// TODO Auto-generated method stub
		return pdao.Editar(prestamo);
	}

	@Override
	public int Borrar(int id) throws DBException, GenericException{
		// TODO Auto-generated method stub
		return pdao.Borrar(id);
	}

	@Override
	public int Aprobar(Prestamo pr) throws DBException, GenericException{
		ctNeg.CambiarSaldo(pr.getMonto(), pr.getCuenta().getCBU());
		return pdao.Aprobar(pr.getId_Prestamo());
	}

	@Override
	public ArrayList<Prestamo> obtenerPrestamosPaginados(int pageNumber, int pageSize) throws DBException, GenericException{
		// TODO Auto-generated method stub
		return pdao.obtenerPrestamosPaginados(pageNumber, pageSize);
	}

	@Override
	public int getCantPaginas() throws DBException, GenericException{
		// TODO Auto-generated method stub
		return pdao.getCantPaginas();
	}

	@Override
	public int Rechazar(int id) throws DBException, GenericException{
		// TODO Auto-generated method stub
		return pdao.Rechazar(id);
	}
	
	@Override
	public ArrayList<Prestamo> obtenerPrestamosPorUsuario(int IDCliente)throws DBException, GenericException{
		return pdao.obtenerPrestamosPorUsuario(IDCliente);
	}

	@Override
	public ArrayList<Prestamo> obtenerPrestamosAprobadosPorUsuario(int IDCliente) throws DBException, GenericException{
		// TODO Auto-generated method stub
		return pdao.obtenerPrestamosAprobadosPorUsuario(IDCliente);
	}

	@Override
	public int obtenerUltimoID() throws DBException, GenericException{
		return pdao.obtenerUltimoID();
	}

}

