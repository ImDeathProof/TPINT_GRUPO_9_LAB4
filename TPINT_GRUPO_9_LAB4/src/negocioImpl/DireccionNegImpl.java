package negocioImpl;

import java.util.ArrayList;

import dao.CuentaDaoInterface;
import dao.DireccionDaoInterface;
import daoImpl.CuentaDAO;
import daoImpl.DireccionDAO;
import entidad.Direccion;
import entidad.Localidad;
import entidad.Provincia;
import excepciones.DBException;
import excepciones.GenericException;
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

	@Override
	public ArrayList<Provincia> getAllProvincias() throws DBException, GenericException {
		return dDao.getAllProvincias();
	}

	@Override
	public ArrayList<Localidad> getAllLocalidades(int provId) throws DBException, GenericException {
		return dDao.getAllLocalidades(provId);
	}

	@Override
	public Direccion addDireccion(Direccion direc) throws GenericException, DBException {
		return dDao.addDireccion(direc);
	}

	@Override
	public boolean mismaDireccion(Direccion direc) {
		return dDao.mismaDireccion(direc);
	}

	@Override
	public Direccion obtenerDireccionSinID(Direccion direc) throws DBException, GenericException {
		return dDao.obtenerDireccionSinID(direc);
	}

	

}
