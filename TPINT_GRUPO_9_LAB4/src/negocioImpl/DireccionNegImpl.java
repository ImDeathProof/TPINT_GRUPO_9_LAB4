package negocioImpl;

import dao.CuentaDaoInterface;
import dao.DireccionDaoInterface;
import daoImpl.CuentaDAO;
import daoImpl.DireccionDAO;
import entidad.DBException;
import entidad.Direccion;
import entidad.GenericException;
import entidad.Localidad;
import entidad.Provincia;
import negocio.DireccionNeg;

public class DireccionNegImpl implements DireccionNeg {
	
	private DireccionDaoInterface dDao = new DireccionDAO();

	@Override
	public int modificarDireccion(Direccion direc) {
		return dDao.modificarDireccion(direc);
	}

	@Override
	public Direccion obtenerDireccionPorID(int idDireccion) throws DBException, GenericException {
		return dDao.obtenerDireccionPorID(idDireccion);
	}

	@Override
	public Localidad obtenerLocalidadPorID(int idLocalidad) throws DBException, GenericException {
		return dDao.obtenerLocalidadPorID(idLocalidad);
	}

	@Override
	public Provincia obtenerProvinciaPorID(int idProvincia) throws DBException, GenericException {
		return dDao.obtenerProvinciaPorID(idProvincia);
	}

	@Override
	public Localidad obtenerLocalidadPorDesc(String desc) throws DBException, GenericException {
		return dDao.obtenerLocalidadPorDesc(desc);
	}

	@Override
	public Provincia obtenerProvinciaPorDesc(String desc) throws DBException, GenericException {
		return dDao.obtenerProvinciaPorDesc(desc);
	}
	

}
