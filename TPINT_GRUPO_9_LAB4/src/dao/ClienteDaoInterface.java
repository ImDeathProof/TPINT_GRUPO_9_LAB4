package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import entidad.Cliente;

public interface ClienteDaoInterface {
	
	public Cliente agregarUsuario(Cliente cliente) throws SQLException;
	public Cliente BuscarUsuario(Cliente cliente) throws SQLException;
	public int modificarUsuario(Cliente cliente) throws SQLException;
	public boolean usuarioExistente(String username, int idUsuario) throws SQLException;
	public ArrayList<Cliente> obtenerUsuarios();
	public int BloquearCliente(int id);
	public int DesbloquearCliente(int id);
	public int CambiarPass(String password, int id );
	public ArrayList<Cliente> obtenerUsuariosPaginados(int pageNumber, int pageSize);
	public int getCantPaginas();
	public Cliente BuscarClientePorID(int idCliente);

}
