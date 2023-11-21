package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import entidad.Cliente;
import entidad.DBException;
import entidad.ValidateException;
import entidad.GenericException;

public interface ClienteDaoInterface {
	
	public Cliente agregarUsuario(Cliente cliente) throws DBException, GenericException;
	public Cliente BuscarUsuario(Cliente cliente) throws DBException, GenericException;
	public int modificarUsuario(Cliente cliente) throws DBException, GenericException;
	public boolean usuarioExistente(String username, int idUsuario) throws ValidateException, GenericException;
	public ArrayList<Cliente> obtenerUsuarios() throws DBException, GenericException;
	public int BloquearCliente(int id) throws DBException, GenericException;
	public int DesbloquearCliente(int id) throws DBException, GenericException;
	public int CambiarPass(String password, int id ) throws DBException, GenericException;
	public ArrayList<Cliente> obtenerUsuariosPaginados(int pageNumber, int pageSize) throws DBException, GenericException;
	public int getCantPaginas() throws DBException, GenericException;
	public Cliente BuscarClientePorID(int idCliente) throws DBException, GenericException;

}
