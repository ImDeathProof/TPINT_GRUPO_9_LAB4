package negocioImpl;

import java.util.ArrayList;
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
		return pdao.ObtenerTodos();
	}

	@Override
	public Prestamo ObtenerUno(int id) {
		// TODO Auto-generated method stub
		return pdao.ObtenerUno(id);
	}

	@Override
	public int Insertar(Prestamo prestamo) {
		// TODO Auto-generated method stub
		return pdao.Insertar(prestamo);
	}

	@Override
	public int Editar(Prestamo prestamo) {
		// TODO Auto-generated method stub
		return pdao.Editar(prestamo);
	}

	@Override
	public int Borrar(int id) {
		// TODO Auto-generated method stub
		return pdao.Borrar(id);
	}

	@Override
	public int Aprobar(int id) {
		// TODO Auto-generated method stub
		return pdao.Aprobar(id);
	}

	@Override
	public ArrayList<Prestamo> obtenerPrestamosPaginados(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return pdao.obtenerPrestamosPaginados(pageNumber, pageSize);
	}

	@Override
	public int getCantPaginas() {
		// TODO Auto-generated method stub
		return pdao.getCantPaginas();
	}

	@Override
	public int Rechazar(int id) {
		// TODO Auto-generated method stub
		return pdao.Rechazar(id);
	}

}

