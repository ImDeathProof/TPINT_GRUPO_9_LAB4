package negocio;

import java.sql.SQLException;
import java.util.ArrayList;
import entidad.DBException;

import entidad.Cliente;

public interface ClienteNeg {
	
	public Cliente agregarUsuario(Cliente cliente) throws DBException;
	public Cliente BuscarUsuario(Cliente cliente) throws DBException;
	public int modificarUsuario(Cliente cliente) throws DBException;
	public boolean usuarioExistente(String username, int idUsuario) throws DBException;
	public ArrayList<Cliente> obtenerUsuarios() throws DBException;
	public int BloquearCliente(int id)throws DBException;
	public int DesbloquearCliente(int id)throws DBException;
	public int CambiarPass(String password, int id )throws DBException;
	public ArrayList<Cliente> obtenerUsuariosPaginados(int pageNumber, int pageSize)throws DBException;
	public int getCantPaginas()throws DBException;
	public Cliente BuscarClientePorID(int idCliente)throws DBException;

}
