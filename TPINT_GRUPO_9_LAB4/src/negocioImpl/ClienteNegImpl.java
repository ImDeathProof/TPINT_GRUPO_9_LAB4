package negocioImpl;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.ClienteDaoInterface;
import negocio.ClienteNeg;
import daoImpl.ClienteDAO;
import entidad.Cliente;

public class ClienteNegImpl implements ClienteNeg {
	
	private ClienteDaoInterface clDao = new ClienteDAO();

	@Override
	public Cliente agregarUsuario(Cliente cliente) throws SQLException {
		return clDao.agregarUsuario(cliente);
	}

	@Override
	public Cliente BuscarUsuario(Cliente cliente) throws SQLException {
		return clDao.BuscarUsuario(cliente);
	}

	@Override
	public int modificarUsuario(Cliente cliente) throws SQLException {
		return clDao.modificarUsuario(cliente);
	}

	@Override
	public ArrayList<Cliente> obtenerUsuarios() {
		return clDao.obtenerUsuarios();
	}

	@Override
	public int BloquearCliente(int id) {
		return clDao.BloquearCliente(id);
	}

	@Override
	public int DesbloquearCliente(int id) {
		return clDao.DesbloquearCliente(id);
	}

	@Override
	public int CambiarPass(String password, int id) {
		return clDao.CambiarPass(password, id);
	}

	@Override
	public ArrayList<Cliente> obtenerUsuariosPaginados(int pageNumber, int pageSize) {
		return clDao.obtenerUsuariosPaginados(pageNumber, pageSize);
	}

	@Override
	public int getCantPaginas() {
		return clDao.getCantPaginas();
	}

	@Override
	public Cliente BuscarClientePorID(int idCliente) {
		// TODO Auto-generated method stub
		return clDao.BuscarClientePorID(idCliente);
	}
	
	

}
