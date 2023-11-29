package negocioImpl;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.ClienteDaoInterface;
import negocio.ClienteNeg;
import daoImpl.ClienteDAO;
import entidad.Cliente;
import entidad.DBException;
import entidad.ValidateException;
import entidad.GenericException;
import entidad.Localidad;
import entidad.Provincia;

public class ClienteNegImpl implements ClienteNeg {
	
	private ClienteDaoInterface clDao = new ClienteDAO();

	@Override
	public Cliente agregarUsuario(Cliente cliente) throws DBException, GenericException {
		return clDao.agregarUsuario(cliente);
	}

	@Override
	public Cliente BuscarUsuario(Cliente cliente) throws DBException, GenericException {
		return clDao.BuscarUsuario(cliente);
	}

	@Override
	public int modificarUsuario(Cliente cliente) throws DBException, GenericException {
		return clDao.modificarUsuario(cliente);
	}

	@Override
	public ArrayList<Cliente> obtenerUsuarios() throws DBException, GenericException{
		return clDao.obtenerUsuarios();
	}

	@Override
	public int BloquearCliente(int id) throws DBException, GenericException{
		return clDao.BloquearCliente(id);
	}

	@Override
	public int DesbloquearCliente(int id) throws DBException, GenericException{
		return clDao.DesbloquearCliente(id);
	}

	@Override
	public int CambiarPass(String password, int id) throws DBException, GenericException{
		return clDao.CambiarPass(password, id);
	}

	@Override
	public ArrayList<Cliente> obtenerUsuariosPaginados(int pageNumber, int pageSize) throws DBException, GenericException{
		return clDao.obtenerUsuariosPaginados(pageNumber, pageSize);
	}

	@Override
	public int getCantPaginas() throws DBException, GenericException{
		return clDao.getCantPaginas();
	}

	@Override
	public Cliente BuscarClientePorID(int idCliente) throws DBException, GenericException{
		// TODO Auto-generated method stub
		return clDao.BuscarClientePorID(idCliente);
	}

	@Override
	public boolean usuarioExistente(String username, int idUsuario) throws ValidateException, GenericException {
		return clDao.usuarioExistente(username, idUsuario);
	}
	
	@Override
	public ArrayList<Localidad> obtenerLocalidades() throws DBException, GenericException{
		return clDao.obtenerLocalidades();
	}
	
	@Override
	public ArrayList<Provincia> obtenerProvincias() throws DBException, GenericException{
		return clDao.obtenerProvincias();
	}
	
	@Override
	public ArrayList<Cliente> obtenerUsuariosPaginadosFiltrados(int pageNumber, int pageSize, String elementoBusqueda, String criterioBusqueda) throws DBException, GenericException{
		return clDao.obtenerUsuariosPaginadosFiltrados(pageNumber, pageSize, elementoBusqueda, criterioBusqueda);
	}

	@Override
	public int getCantPaginasXFiltro(String elementoBusqueda, String criterioBusqueda) throws DBException, GenericException {
		return clDao.getCantPaginasXFiltro(elementoBusqueda, criterioBusqueda);
	}

}
