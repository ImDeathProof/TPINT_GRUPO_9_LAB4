package negocioImpl;

import java.util.List;

import daoImpl.PrestamoDAO;
import dao.PrestamoDaoInterface;
import entidad.Prestamo;
import negocio.PrestamoNeg;

public class PrestamoNegocioImpl implements PrestamoNeg {
	private PrestamoDaoInterface pdao = new PrestamoDAO();
	
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
		// TODO Auto-generated method stub
		return pdao.Insertar(prestamo);
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

