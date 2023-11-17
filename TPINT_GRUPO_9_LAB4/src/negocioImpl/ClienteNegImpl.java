package negocioImpl;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.ClienteDaoInterface;
import negocio.ClienteNeg;
import daoImpl.ClienteDAO;
import entidad.Cliente;
import entidad.DBException;

public class ClienteNegImpl implements ClienteNeg {
	
	private ClienteDaoInterface clDao = new ClienteDAO();

	@Override
	public Cliente agregarUsuario(Cliente cliente) throws DBException {
		return clDao.agregarUsuario(cliente);
	}

	@Override
	public Cliente BuscarUsuario(Cliente cliente) throws DBException {
		return clDao.BuscarUsuario(cliente);
	}

	@Override
	public int modificarUsuario(Cliente cliente) throws DBException {
		return clDao.modificarUsuario(cliente);
	}

	@Override
	public ArrayList<Cliente> obtenerUsuarios() throws DBException{
		return clDao.obtenerUsuarios();
	}

	@Override
	public int BloquearCliente(int id) throws DBException{
		return clDao.BloquearCliente(id);
	}

	@Override
	public int DesbloquearCliente(int id) throws DBException{
		return clDao.DesbloquearCliente(id);
	}

	@Override
	public int CambiarPass(String password, int id) throws DBException{
		return clDao.CambiarPass(password, id);
	}

	@Override
	public ArrayList<Cliente> obtenerUsuariosPaginados(int pageNumber, int pageSize) throws DBException{
		return clDao.obtenerUsuariosPaginados(pageNumber, pageSize);
	}

	@Override
	public int getCantPaginas() throws DBException{
		return clDao.getCantPaginas();
	}

	@Override
	public Cliente BuscarClientePorID(int idCliente) throws DBException{
		// TODO Auto-generated method stub
		return clDao.BuscarClientePorID(idCliente);
	}

	@Override
	public boolean usuarioExistente(String username, int idUsuario) throws DBException {
		return clDao.usuarioExistente(username, idUsuario);
	}
	
	

}
